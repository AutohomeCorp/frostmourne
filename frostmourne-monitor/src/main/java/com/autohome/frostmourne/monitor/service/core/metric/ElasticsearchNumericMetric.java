package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchInfo;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.dao.elasticsearch.EsRestClientContainer;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchNumericMetric extends AbstractCountMetric {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchNumericMetric.class);

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {

        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo();
        elasticsearchInfo.setName(metricContract.getDataSourceContract().getDatasource_name());
        elasticsearchInfo.setEsHostList(metricContract.getDataSourceContract().getService_address());
        elasticsearchInfo.setSniff(false);

        EsRestClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(findTimeWindowInMinutes(settings));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(metricContract.getQuery_string()))
                .must(QueryBuilders.rangeQuery(metricContract.getDataNameContract().getTimestamp_field())
                        .from(start.toDateTimeISO().toString())
                        .to(end.toDateTimeISO().toString())
                        .includeLower(true)
                        .includeUpper(false)
                        .format("date_optional_time"));

        Map<String, String> dataNameProperties = metricContract.getDataNameContract().getSettings();

        String indexPrefix = dataNameProperties.get("indexPrefix");
        String datePattern = dataNameProperties.get("timePattern");

        String[] indices = esRestClientContainer.buildIndices(start, end, indexPrefix, datePattern);

        SearchRequest searchRequest = new SearchRequest(indices);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(1)
                .sort(metricContract.getDataNameContract().getTimestamp_field(), SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);

        Map<String, Object> result = new HashMap<>();

        result.put("startTime", start.toDateTimeISO().toString());
        result.put("endTime",end.toDateTimeISO().toString());
        try {
            SearchResponse searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest);
            result.put("NUMBER", searchResponse.getHits().totalHits);

            if (searchResponse.getHits().totalHits > 0) {
                SearchHit latestDoc = searchResponse.getHits().getAt(0);
                result.putAll(latestDoc.getSourceAsMap());
            }
        } catch (Exception ex) {
            LOGGER.error("error when pullMetric", ex);
        }

        return result;
    }
}
