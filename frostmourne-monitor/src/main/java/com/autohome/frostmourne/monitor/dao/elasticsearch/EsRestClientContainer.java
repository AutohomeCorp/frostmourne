package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.StatItem;
import com.autohome.frostmourne.monitor.service.core.domain.BucketInfo;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.google.common.base.Splitter;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
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
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentiles;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * elasticsearch 6, 7 client container
 */
public class EsRestClientContainer extends AbstractElasticClientContainer {

    public static final IndicesOptions DEFAULT_INDICE_OPTIONS = IndicesOptions.fromOptions(true, false, true, false);

    private static final Logger LOGGER = LoggerFactory.getLogger(EsRestClientContainer.class);

    private RestHighLevelClient restHighLevelClient;

    public EsRestClientContainer(String esHostList, boolean sniff, Map<String, String> settings) {
        super(esHostList, settings);
    }

    @Override
    public void init() {
        RestClientBuilder restClientBuilder = initRestClientBuilder();
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        this.restLowLevelClient = restHighLevelClient.getLowLevelClient();
        this.initTimestamp = System.currentTimeMillis();
    }

    @Override
    public boolean health() {
        ClusterHealthRequest request = new ClusterHealthRequest();
        request.timeout(TimeValue.timeValueSeconds(5));
        ClusterHealthResponse response;
        try {
            response = restHighLevelClient.cluster().health(request, RequestOptions.DEFAULT);
        } catch (IOException ex) {
            LOGGER.error("error when check cluster health", ex);
            return false;
        }
        return response.getStatus() == ClusterHealthStatus.GREEN;
    }

    @Override
    public String number() {
        try {
            MainResponse mainResponse = restHighLevelClient.info(RequestOptions.DEFAULT);
            return mainResponse.getVersion().toString();
        } catch (IOException ex) {
            LOGGER.error("error when check cluster version", ex);
            throw new RuntimeException(ex);
        }
    }

    public void close() {
        if (this.restHighLevelClient != null) {
            try {
                this.restHighLevelClient.close();
            } catch (IOException ex) {
                LOGGER.error("error when close elasticsearch rest client", ex);
            }
        }
    }

    public RestHighLevelClient fetchHighLevelClient() {
        return this.restHighLevelClient;
    }

    public long totalCount(BoolQueryBuilder boolQueryBuilder, String[] indices) throws IOException {
        CountRequest countRequest = new CountRequest(indices);
        countRequest.indicesOptions(EsRestClientContainer.DEFAULT_INDICE_OPTIONS);
        SearchSourceBuilder countSourceBuilder = new SearchSourceBuilder();
        countSourceBuilder.query(boolQueryBuilder);
        countRequest.source(countSourceBuilder);

        CountResponse countResponse = this.fetchHighLevelClient().count(countRequest, RequestOptions.DEFAULT);
        return countResponse.getCount();
    }

    @Override
    public Long getInitTimestamp() {
        return initTimestamp;
    }

    @Override
    public ElasticsearchDataResult query(DataNameContract dataNameContract, DateTime start, DateTime end, String esQuery,
                                         String scrollId, String sortOrder, Integer intervalInSeconds) {
        EsRestClientContainer esRestClientContainer = this;
        DateTime queryEnd = end;
        if (queryEnd.getMillis() > System.currentTimeMillis()) {
            queryEnd = DateTime.now();
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(esQuery))
                .must(QueryBuilders.rangeQuery(dataNameContract.getTimestampField()).from(start.toDateTimeISO().toString()).to(queryEnd.toDateTimeISO().toString())
                        .includeLower(true).includeUpper(false).format("date_optional_time"));

        Map<String, String> dataNameProperties = dataNameContract.getSettings();

        String indexPrefix = dataNameProperties.get("indexPrefix");
        String datePattern = dataNameProperties.get("timePattern");
        String[] indices = esRestClientContainer.buildIndices(start, end, indexPrefix, datePattern);

        SearchResponse searchResponse;
        try {
            if (Strings.isNullOrEmpty(scrollId)) {
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.query(boolQueryBuilder);
                searchSourceBuilder.sort(dataNameContract.getTimestampField(), SortOrder.fromString(sortOrder));
                searchSourceBuilder.size(50);
                searchSourceBuilder.trackTotalHits(true);
                searchSourceBuilder.trackScores(false);
                SearchRequest searchRequest = new SearchRequest(indices);
                searchRequest.source(searchSourceBuilder);
                searchRequest.scroll(DEFAULT_TIME_VALUE);
                searchRequest.indicesOptions(EsRestClientContainer.DEFAULT_INDICE_OPTIONS);

                if (intervalInSeconds != null && intervalInSeconds > 0) {
                    DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
                            AggregationBuilders.dateHistogram("date_hist").timeZone(DateTimeZone.getDefault())
                                    .extendedBounds(new ExtendedBounds(start.getMillis(), end.getMillis())).field(dataNameContract.getTimestampField())
                                    .format("yyyy-MM-dd'T'HH:mm:ssZ").dateHistogramInterval(DateHistogramInterval.seconds(intervalInSeconds));
                    searchSourceBuilder.aggregation(dateHistogramAggregationBuilder);
                }
                searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
            } else {
                SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
                searchScrollRequest.scroll(DEFAULT_TIME_VALUE);
                searchResponse = esRestClientContainer.fetchHighLevelClient().scroll(searchScrollRequest, RequestOptions.DEFAULT);
            }
        } catch (IOException ex) {
            LOGGER.error("error when search elasticsearch data", ex);
            throw new ProtocolException(520, "error when search elasticsearch data", ex);
        }
        List<String> headFieldList = null;
        if (dataNameContract.getSettings() != null && dataNameContract.getSettings().containsKey("headFields")) {
            String headFieldStr = dataNameContract.getSettings().get("headFields");
            headFieldList = Splitter.on(",").splitToList(headFieldStr);
        }
        ElasticsearchDataResult elasticsearchDataResult = parseResult(searchResponse, dataNameContract.getTimestampField(), headFieldList);
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

    @Override
    public MetricData queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException {
        MetricData elasticsearchMetric = new MetricData();
        EsRestClientContainer esRestClientContainer = this;
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(metricContract.getQueryString()))
                .must(QueryBuilders.rangeQuery(metricContract.getDataNameContract().getTimestampField()).from(start.toDateTimeISO().toString())
                        .to(end.toDateTimeISO().toString()).includeLower(true).includeUpper(false).format("date_optional_time"));
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
        if ("count".equalsIgnoreCase(metricContract.getAggregationType())) {
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
        searchSourceBuilder.query(boolQueryBuilder).from(0).size(1).sort(metricContract.getDataNameContract().getTimestampField(), SortOrder.DESC);
        attachAggregation(metricContract, searchSourceBuilder);
        attachBucket(start, end, metricContract, searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        searchRequest.indicesOptions(DEFAULT_INDICE_OPTIONS);
        SearchResponse searchResponse = null;
        int tryCount = 3;
        while (tryCount > 0) {
            searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
            int hits = searchResponse.getHits().getHits().length;
            if (hits == 0 && tryCount == 1) {
                LOGGER.error("totalCount {}, but hits length is 0, query: {}, start: {}, end: {}", count, metricContract.getQueryString(), start.toString(),
                        end.toString());
                return elasticsearchMetric;
            }
            if (hits > 0) {
                break;
            }
            tryCount--;
        }
        SearchHit latestDoc = searchResponse.getHits().getAt(0);
        elasticsearchMetric.setLatestDocument(latestDoc.getSourceAsMap());
        if ("count".equalsIgnoreCase(metricContract.getAggregationType())) {
            if (searchResponse.getHits().getTotalHits() > 0) {
                elasticsearchMetric.setMetricValue(searchResponse.getHits().getTotalHits());
            }
        } else {
            Double numericValue = findAggregationValue(metricContract, searchResponse);
            elasticsearchMetric.setMetricValue(numericValue);
        }
        List<BucketInfo> bucketInfos = findBucketValue(metricContract, searchResponse);
        elasticsearchMetric.setBuckets(bucketInfos);
        return elasticsearchMetric;
    }

    private ElasticsearchDataResult parseResult(SearchResponse searchResponse, String timestampField, List<String> headFields) {
        ElasticsearchDataResult dataResult = new ElasticsearchDataResult();
        dataResult.setTimestampField(timestampField);
        dataResult.setScrollId(searchResponse.getScrollId());
        dataResult.setTotal(searchResponse.getHits().getTotalHits());
        List<Map<String, Object>> logs = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits()) {
            logs.add(hit.getSourceAsMap());
            int size = hit.getSourceAsMap().keySet().size();
            if (dataResult.getFields() == null || size > dataResult.getFields().size()) {
                dataResult.setFields(new ArrayList<>(hit.getSourceAsMap().keySet()));
                if (headFields == null || headFields.size() == 0) {
                    List<String> flatFields = findFields(hit.getSourceAsMap(), null);
                    dataResult.setFlatFields(flatFields);
                    if (flatFields.size() > 7) {
                        dataResult.setHeadFields(flatFields.subList(0, 6));
                    } else {
                        dataResult.setHeadFields(flatFields);
                    }
                } else {
                    dataResult.setHeadFields(headFields);
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
                statItem.getValues().add((double)bucket.getDocCount());
            }
            dataResult.setStatItem(statItem);
        }

        return dataResult;
    }

    private void attachBucket(DateTime start, DateTime end, MetricContract metricContract, SearchSourceBuilder searchSourceBuilder) {
        String bucketType = metricContract.getBucketType();
        String bucketField = metricContract.getBucketField();
        if(Strings.isNullOrEmpty(bucketType) || Strings.isNullOrEmpty(bucketField)) {
            return;
        }

        if("none".equalsIgnoreCase(bucketType)) {
            return;
        }
        if(bucketType.equalsIgnoreCase("terms")) {
            searchSourceBuilder.aggregation(AggregationBuilders
                    .terms("terms")
                    .size(50)
                    .field(bucketField));
        } else if (bucketType.equalsIgnoreCase("date_histogram")) {
            searchSourceBuilder.aggregation(AggregationBuilders
                    .dateHistogram("date_histogram")
                    .timeZone(DateTimeZone.getDefault())
                    .extendedBounds(new ExtendedBounds(start.getMillis(), end.getMillis()))
                    .field(bucketField)
                    .interval(Long.parseLong(metricContract.getProperties().getOrDefault("dateHistogramInterval", "3600000").toString()))
                    .format("yyyy-MM-dd'T'HH:mm:ssZ"));
        } else {
            throw new RuntimeException("unknown bucket type: " + bucketType);
        }
    }

    private List<BucketInfo> findBucketValue(MetricContract metricContract, SearchResponse searchResponse) {
        String bucketType = metricContract.getBucketType();
        if(Strings.isNullOrEmpty(bucketType) || "none".equalsIgnoreCase(bucketType)) {
            return new ArrayList<>();
        }
        List<BucketInfo> bucketInfos = new ArrayList<>();
        if(bucketType.equalsIgnoreCase("terms")) {
            Terms terms = searchResponse.getAggregations().get("terms");
            for (Terms.Bucket bucket : terms.getBuckets()) {
                BucketInfo bucketInfo = new BucketInfo();
                bucketInfo.setKey(bucket.getKeyAsString());
                bucketInfo.setValue(bucket.getDocCount());
                bucketInfos.add(bucketInfo);
            }
        } else if(bucketType.equalsIgnoreCase("date_histogram")) {
            Histogram dateHistogram = searchResponse.getAggregations().get("date_histogram");
            for (Histogram.Bucket bucket : dateHistogram.getBuckets()) {
                BucketInfo bucketInfo = new BucketInfo();
                bucketInfo.setKey(bucket.getKeyAsString());
                bucketInfo.setValue(bucket.getDocCount());
                bucketInfos.add(bucketInfo);
            }
        }

        return bucketInfos;
    }

    private void attachAggregation(MetricContract metricContract, SearchSourceBuilder searchSourceBuilder) {
        String aggType = metricContract.getAggregationType();
        String aggField = metricContract.getAggregationField();
        if ("max".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.max("maxNumber").field(aggField));
        } else if ("min".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.min("minNumber").field(aggField));
        } else if ("avg".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.avg("avgNumber").field(aggField));
        } else if ("sum".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.sum("sumNumber").field(aggField));
        } else if ("cardinality".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.cardinality("cardinality").field(aggField));
        } else if ("standard_deviation".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.extendedStats("extend").field(aggField));
        } else if ("percentiles".equalsIgnoreCase(aggType)) {
            searchSourceBuilder.aggregation(AggregationBuilders.percentiles("percentiles")
                    .percentiles(Double.parseDouble(metricContract.getProperties().get("percent").toString())).field(aggField));
        }
    }

    private Double findAggregationValue(MetricContract metricContract, SearchResponse searchResponse) {
        String aggType = metricContract.getAggregationType();
        if ("max".equalsIgnoreCase(aggType)) {
            Max max = searchResponse.getAggregations().get("maxNumber");
            return max.getValue();
        }
        if ("min".equalsIgnoreCase(aggType)) {
            Min min = searchResponse.getAggregations().get("minNumber");
            return min.getValue();
        }
        if ("avg".equalsIgnoreCase(aggType)) {
            Avg avg = searchResponse.getAggregations().get("avgNumber");
            return avg.getValue();
        }
        if ("sum".equalsIgnoreCase(aggType)) {
            Sum sum = searchResponse.getAggregations().get("sumNumber");
            return sum.getValue();
        }
        if ("cardinality".equalsIgnoreCase(aggType)) {
            Cardinality cardinality = searchResponse.getAggregations().get("cardinality");
            return (double)cardinality.getValue();
        }
        if ("standard_deviation".equalsIgnoreCase(aggType)) {
            ExtendedStats extendedStats = searchResponse.getAggregations().get("extend");
            return extendedStats.getStdDeviation();
        }
        if ("percentiles".equalsIgnoreCase(aggType)) {
            Percentiles percentiles = searchResponse.getAggregations().get("percentiles");
            return percentiles.percentile(Double.parseDouble(metricContract.getProperties().get("percent").toString()));
        }

        throw new IllegalArgumentException("unsupported aggregation type: " + aggType);
    }
}
