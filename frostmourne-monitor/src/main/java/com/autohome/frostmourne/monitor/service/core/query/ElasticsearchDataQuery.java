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
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.contract.StatItem;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchInfo;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.dao.elasticsearch.EsRestClientContainer;
import com.autohome.frostmourne.monitor.service.core.domain.ElasticsearchMetric;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
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
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
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
                                         DateTime start, DateTime end, String esQuery, String scrollId,
                                         String sortOrder, Integer intervalInSeconds) {
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(dataSourceContract);
        EsRestClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
        DateTime queryEnd = end;
        if (queryEnd.getMillis() > System.currentTimeMillis()) {
            queryEnd = DateTime.now();
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(esQuery))
                .must(QueryBuilders.rangeQuery(dataNameContract.getTimestamp_field())
                        .from(start.toDateTimeISO().toString())
                        .to(queryEnd.toDateTimeISO().toString())
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
                searchSourceBuilder.trackTotalHits(true);
                searchSourceBuilder.trackScores(false);
                SearchRequest searchRequest = new SearchRequest(indices);
                searchRequest.source(searchSourceBuilder);
                searchRequest.scroll(DEFAULT_TIME_VALUE);

                if (intervalInSeconds != null && intervalInSeconds > 0) {
                    DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
                            AggregationBuilders.dateHistogram("date_hist")
                                    .timeZone(DateTimeZone.getDefault())
                                    .extendedBounds(new ExtendedBounds(start.getMillis(), end.getMillis()))
                                    .field(dataNameContract.getTimestamp_field())
                                    .format("yyyy-MM-dd'T'HH:mm:ssZ")
                                    .dateHistogramInterval(DateHistogramInterval.seconds(intervalInSeconds));
                    searchSourceBuilder.aggregation(dateHistogramAggregationBuilder);
                }
                searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
            } else {
                SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
                searchScrollRequest.scroll(DEFAULT_TIME_VALUE);
                searchResponse = esRestClientContainer.fetchHighLevelClient().scroll(searchScrollRequest, RequestOptions.DEFAULT);
            }
        } catch (IOException ex) {
            throw new ProtocolException(520, "error when search elasticsearch data", ex);
        }
        ElasticsearchDataResult elasticsearchDataResult = parseResult(searchResponse, dataNameContract.getTimestamp_field());
        if (Strings.isNullOrEmpty(scrollId) && elasticsearchDataResult.getTotal() == 0) {
            try {
                long total = esRestClientContainer.totalCount(boolQueryBuilder, indices);
                elasticsearchDataResult.setTotal(total);
            } catch (Exception ex) {
                LOGGER.error("error when get count", ex);
            }
        }
        return elasticsearchDataResult;
    }

    public ElasticsearchMetric queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException {
        ElasticsearchMetric elasticsearchMetric = new ElasticsearchMetric();
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(metricContract.getDataSourceContract());
        EsRestClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
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
        Long count = null;
        try {
            count = esRestClientContainer.totalCount(boolQueryBuilder, indices);
        } catch (Exception ex) {
            throw new RuntimeException("error when totalCount", ex);
        }
        if (metricContract.getAggregation_type().equalsIgnoreCase("count")) {
            elasticsearchMetric.setMetricValue(count);
        }
        if (count == 0) {
            elasticsearchMetric.setMetricValue(0);
            return elasticsearchMetric;
        }
        SearchRequest searchRequest = new SearchRequest(indices);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.trackScores(false);
        searchSourceBuilder.trackTotalHits(true);
        searchSourceBuilder.query(boolQueryBuilder).size(1)
                .sort(metricContract.getDataNameContract().getTimestamp_field(), SortOrder.DESC);
        attachAggregation(metricContract, searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit latestDoc = searchResponse.getHits().getAt(0);
        elasticsearchMetric.setLatestDocument(latestDoc.getSourceAsMap());
        if (metricContract.getAggregation_type().equalsIgnoreCase("count")) {
            if (searchResponse.getHits().getTotalHits() > 0) {
                elasticsearchMetric.setMetricValue(searchResponse.getHits().getTotalHits());
            }
        } else {
            Double numericValue = findAggregationValue(metricContract, searchResponse);
            elasticsearchMetric.setMetricValue(numericValue);
        }
        return elasticsearchMetric;
    }

    private void attachAggregation(MetricContract metricContract, SearchSourceBuilder searchSourceBuilder) {
        String aggType = metricContract.getAggregation_type();
        String aggField = metricContract.getAggregation_field();
        if (aggType.equalsIgnoreCase("max")) {
            searchSourceBuilder.aggregation(AggregationBuilders.max("maxNumber").field(aggField));
        } else if (aggType.equalsIgnoreCase("min")) {
            searchSourceBuilder.aggregation(AggregationBuilders.min("minNumber").field(aggField));
        } else if (aggType.equalsIgnoreCase("avg")) {
            searchSourceBuilder.aggregation(AggregationBuilders.avg("avgNumber").field(aggField));
        } else if (aggType.equalsIgnoreCase("sum")) {
            searchSourceBuilder.aggregation(AggregationBuilders.sum("sumNumber").field(aggField));
        }
    }

    private Double findAggregationValue(MetricContract metricContract, SearchResponse searchResponse) {
        String aggType = metricContract.getAggregation_type();
        if (aggType.equalsIgnoreCase("max")) {
            Max max = searchResponse.getAggregations().get("maxNumber");
            return max.getValue();
        }
        if (aggType.equalsIgnoreCase("min")) {
            Min min = searchResponse.getAggregations().get("minNumber");
            return min.getValue();
        }
        if (aggType.equalsIgnoreCase("avg")) {
            Avg avg = searchResponse.getAggregations().get("avgNumber");
            return avg.getValue();
        }
        if (aggType.equalsIgnoreCase("sum")) {
            Sum sum = searchResponse.getAggregations().get("sumNumber");
            return sum.getValue();
        }

        throw new IllegalArgumentException("unsupported aggregation type: " + aggType);
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
