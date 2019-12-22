package com.autohome.frostmourne.monitor.service.core.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.contract.StatItem;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchInfo;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.dao.elasticsearch.EsRestClientContainer;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchDataQuery implements IElasticsearchDataQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchDataQuery.class);

    private static final TimeValue DEFAULT_TIME_VALUE = new TimeValue(10, TimeUnit.MINUTES);

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

    public ElasticsearchDataResult query(DataNameContract dataNameContract, DataSourceContract dataSourceContract,
                                         DateTime start, DateTime end, String esQuery,
                                         String scrollId, String sortOrder, Integer intervalInSeconds) {
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo();
        elasticsearchInfo.setName(dataSourceContract.getDatasource_name());
        elasticsearchInfo.setEsHostList(dataSourceContract.getService_address());
        elasticsearchInfo.setSniff(false);

        EsRestClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(esQuery))
                .must(QueryBuilders.rangeQuery(dataNameContract.getTimestamp_field())
                        .from(start.toDateTimeISO().toString())
                        .to(end.toDateTimeISO().toString())
                        .includeLower(true)
                        .includeUpper(false)
                        .format("date_optional_time"));

        Map<String, String> dataNameProperties = dataNameContract.getSettings();

        String indexPrefix = dataNameProperties.get("indexPrefix");
        String datePattern = dataNameProperties.get("timePattern");
        String[] indices = esRestClientContainer.buildIndices(start, end, indexPrefix, datePattern);

        SearchResponse searchResponse = null;
        try {
            if (Strings.isNullOrEmpty(scrollId)) {
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.query(boolQueryBuilder);
                searchSourceBuilder.sort(dataNameContract.getTimestamp_field(), SortOrder.fromString(sortOrder));
                searchSourceBuilder.size(50);
                SearchRequest searchRequest = new SearchRequest(indices);
                searchRequest.source(searchSourceBuilder);
                searchRequest.scroll(DEFAULT_TIME_VALUE);

                DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
                        AggregationBuilders.dateHistogram("date_hist")
                                .timeZone(DateTimeZone.getDefault())
                                .extendedBounds(new ExtendedBounds(start.getMillis(), end.getMillis()))
                                .field(dataNameContract.getTimestamp_field())
                                .format("yyyy-MM-dd'T'HH:mm:ssZ")
                                .dateHistogramInterval(DateHistogramInterval.seconds(intervalInSeconds));
                searchSourceBuilder.aggregation(dateHistogramAggregationBuilder);
                searchRequest.source(searchSourceBuilder);

                searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest);
            } else {
                SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
                searchScrollRequest.scroll(DEFAULT_TIME_VALUE);
                searchResponse = esRestClientContainer.fetchHighLevelClient().searchScroll(searchScrollRequest);
            }
        } catch (IOException ex) {
            throw new ProtocolException(520, "error when search elasticsearch data", ex);
        }
        return parseResult(searchResponse, dataNameContract.getTimestamp_field());
    }

    private ElasticsearchDataResult parseResult(SearchResponse searchResponse, String timestampField) {
        ElasticsearchDataResult dataResult = new ElasticsearchDataResult();
        dataResult.setTimestampField(timestampField);
        dataResult.setScrollId(searchResponse.getScrollId());
        dataResult.setTotal(searchResponse.getHits().getTotalHits());
        List<Map<String, Object>> logs = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits()) {
            logs.add(hit.getSourceAsMap());
            if (dataResult.getFields() == null) {
                Set<String> keys = hit.getSourceAsMap().keySet();
                List<String> fields = new ArrayList<>(keys);
                dataResult.setFields(fields);
                if (fields.size() > 7) {
                    dataResult.setHeadFields(fields.subList(0, 6));
                } else {
                    dataResult.setHeadFields(fields);
                }
            }
        }
        dataResult.setLogs(logs);
        if (searchResponse.getAggregations() == null) {
            return dataResult;
        }
        Histogram dateHistogram = searchResponse.getAggregations().get("date_hist");
        if (dateHistogram != null) {
            StatItem statItem = new StatItem();
            for (Histogram.Bucket bucket : dateHistogram.getBuckets()) {
                statItem.getKeys().add(bucket.getKeyAsString());
                statItem.getValues().add((double) bucket.getDocCount());
            }
            dataResult.setStatItem(statItem);
        }

        return dataResult;
    }
}
