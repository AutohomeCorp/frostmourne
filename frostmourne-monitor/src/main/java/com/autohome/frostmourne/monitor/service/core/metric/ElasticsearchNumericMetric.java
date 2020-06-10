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
import org.elasticsearch.client.RequestOptions;
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
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(metricContract.getDataSourceContract());
        EsRestClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(findTimeWindowInMinutes(settings));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(new QueryStringQueryBuilder(metricContract.getQuery_string()))
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
        searchSourceBuilder.trackScores(false);
        searchSourceBuilder.trackTotalHits(true);
        searchSourceBuilder.query(boolQueryBuilder).size(1)
                .sort(metricContract.getDataNameContract().getTimestamp_field(), SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);

        Map<String, Object> result = new HashMap<>();

        result.put("startTime", start.toDateTimeISO().toString());
        result.put("endTime", end.toDateTimeISO().toString());
        result.put("indices", indices);
        try {
            long count = esRestClientContainer.totalCount(boolQueryBuilder, indices);
            result.put("NUMBER", count);
            if (count > 0) {
                SearchResponse searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
                SearchHit latestDoc = searchResponse.getHits().getAt(0);
                result.putAll(latestDoc.getSourceAsMap());
                if (searchResponse.getHits().getTotalHits() > 0) {
                    result.put("NUMBER", searchResponse.getHits().getTotalHits());
                }
            }

        } catch (Exception ex) {
            LOGGER.error("error when pullMetric", ex);
        }

        return result;
    }
}
