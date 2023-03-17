package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import co.elastic.clients.elasticsearch._types.ExpandWildcard;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.Time;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AverageAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.CardinalityAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.DateHistogramAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.DateHistogramBucket;
import co.elastic.clients.elasticsearch._types.aggregations.ExtendedStatsAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.FieldDateMath;
import co.elastic.clients.elasticsearch._types.aggregations.MaxAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.MinAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.PercentilesAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.SumAggregation;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeRelation;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.autohome.frostmourne.common.contract.ProtocolException;
import com.autohome.frostmourne.monitor.model.contract.StatItem;
import com.autohome.frostmourne.monitor.service.core.domain.BucketInfo;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.elasticsearch.client.RestClientBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.HealthStatus;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.elasticsearch.core.InfoResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/4/30 15:51
 */
public class Elasticsearch8ClientContainer extends AbstractElasticClientContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Elasticsearch8ClientContainer.class);

    private ElasticsearchTransport transport;

    private ElasticsearchClient client;

    public ElasticsearchClient getClient() {
        return client;
    }

    public Elasticsearch8ClientContainer(String esHostList, boolean sniff, Map<String, String> settings) {
        super(esHostList, settings);
    }

    @Override
    public void close() {
        if (this.transport != null) {
            try {
                this.transport.close();
            } catch (Exception ex) {
                LOGGER.error("error when close es8 client", ex);
            }
        }
    }

    @Override
    public void init() {
        RestClientBuilder restClientBuilder = initRestClientBuilder();
        restLowLevelClient = restClientBuilder.build();
        // Create the transport with a Jackson mapper
        this.transport = new RestClientTransport(restLowLevelClient, new JacksonJsonpMapper());

        // And create the API client
        this.client = new ElasticsearchClient(transport);
        this.initTimestamp = System.currentTimeMillis();
    }

    @Override
    public boolean health() {
        try {
            HealthResponse healthResponse = this.client.cluster().health();
            return healthResponse.status() == HealthStatus.Green;
        } catch (Exception ex) {
            LOGGER.error("error when check health", ex);
            return false;
        }
    }

    @Override
    public String number() {
        try {
            InfoResponse infoResponse = this.client.info();
            return infoResponse.version().number();
        } catch (Exception ex) {
            LOGGER.error("error when get number", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Long getInitTimestamp() {
        return this.initTimestamp;
    }

    @Override
    public ElasticsearchDataResult query(DataNameContract dataNameContract, DateTime start, DateTime end, String esQuery, String scrollId, String sortOrder,
                                         Integer intervalInSeconds) {
        final DateTime queryEnd = end.getMillis() > System.currentTimeMillis() ? DateTime.now(): end;
        String[] indices = findIndices(dataNameContract, start, end);
        Query queryStringQuery = QueryStringQuery.of(s -> s.query(esQuery))._toQuery();
        Query timeRangeQuery = RangeQuery.of(r -> r
                .field(dataNameContract.getTimestampField())
                .from(start.toDateTimeISO().toString())
                .to(queryEnd.toDateTimeISO().toString())
                .format("date_optional_time")
                .relation(RangeRelation.Contains))._toQuery();
        Query query = BoolQuery.of(q -> q.must(queryStringQuery, timeRangeQuery))._toQuery();
        SearchResponse<Object> searchResponse = null;
        try {
            if (Strings.isNullOrEmpty(scrollId)) {
                searchResponse = client.search(q -> {
                    q.index(Arrays.asList(indices))
                            .trackTotalHits(t -> t.enabled(true))
                            .ignoreUnavailable(true)
                            .trackScores(false)
                            .allowNoIndices(false)
                            .expandWildcards(ExpandWildcard.Open)
                            .size(50)
                            .query(query)
                            .scroll(Time.of(t -> t.time("10m")));
                    if(sortOrder.equalsIgnoreCase("asc")) {
                        q.sort(SortOptions.of(
                                s ->
                                        s.field(b ->
                                                b.field(dataNameContract.getTimestampField())
                                                        .order(SortOrder.Asc)
                                        )
                        ));
                    } else {
                        q.sort(SortOptions.of(
                                s ->
                                        s.field(b ->
                                                b.field(dataNameContract.getTimestampField())
                                                        .order(SortOrder.Desc)
                                        )
                        ));
                    }
                    if(intervalInSeconds != null && intervalInSeconds > 0) {
                        q.aggregations("dateHistogram", a -> a
                                .dateHistogram(d -> d
                                        .field(dataNameContract.getTimestampField())
                                        .timeZone(DateTimeZone.getDefault().getID())
                                        .format("yyyy-MM-dd'T'HH:mm:ssZ")
                                        .fixedInterval(f -> f.time(intervalInSeconds + "s"))
                                        .extendedBounds(e -> e.min(FieldDateMath.of(f -> f.value((double)start.getMillis())))
                                                .max(FieldDateMath.of(f -> f.value((double)end.getMillis()))))
                                )
                        );
                    }
                    return q;
                }, Object.class);
            } else {
                searchResponse = client.scroll(r -> r
                        .scrollId(scrollId)
                        .scroll(t -> t.time("10m")),
                        Object.class);
            }
        } catch (Exception ex) {
            LOGGER.error("error when search", ex);
            throw new ProtocolException(520, "error when search elasticsearch data", ex);
        }

        List<String> headFieldList = null;
        if (dataNameContract.getSettings() != null && dataNameContract.getSettings().containsKey("headFields")) {
            String headFieldStr = dataNameContract.getSettings().get("headFields");
            if(!Strings.isNullOrEmpty(headFieldStr)) {
                headFieldList = Splitter.on(",").splitToList(headFieldStr);
            }
        }

        ElasticsearchDataResult dataResult = new ElasticsearchDataResult();
        dataResult.setScrollId(searchResponse.scrollId());
        dataResult.setTimestampField(dataNameContract.getTimestampField());
        if(searchResponse.hits().total() != null) {
            dataResult.setTotal(searchResponse.hits().total().value());
        } else {
            dataResult.setTotal(0);
        }
        List<Map<String, Object>> logs = new ArrayList<>();
        for(Hit<Object> hit: searchResponse.hits().hits()) {
            Map<String, Object> source = (Map<String, Object>)hit.source();
            logs.add(source);
            int size = source.keySet().size();
            if(dataResult.getFields() == null || size > dataResult.getFields().size()) {
                dataResult.setFields(new ArrayList<>(source.keySet()));
                List<String> flatFields = findFields(source, null);
                AbstractElasticClientContainer.fillFields(headFieldList, dataResult, flatFields);
            }
        }
        dataResult.setLogs(logs);

        if(searchResponse.aggregations().containsKey("dateHistogram")) {
            StatItem statItem = new StatItem();
            for(DateHistogramBucket bucket : searchResponse.aggregations().get("dateHistogram")
                         .dateHistogram()
                         .buckets().array()) {
                statItem.getKeys().add(bucket.keyAsString());
                statItem.getValues().add((double)bucket.docCount());
            }
            dataResult.setStatItem(statItem);
        }
        return dataResult;
    }

    @Override
    public MetricData queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException {
        Query queryStringQuery = QueryStringQuery.of(s -> s.query(metricContract.getQueryString()))._toQuery();
        Query timeRangeQuery = RangeQuery.of(r -> r
                .field(metricContract.getDataNameContract().getTimestampField())
                .from(start.toDateTimeISO().toString())
                .to(end.toDateTimeISO().toString())
                .format("date_optional_time")
                .relation(RangeRelation.Contains))._toQuery();
        Query query = BoolQuery.of(q -> q.must(queryStringQuery, timeRangeQuery))._toQuery();
        String[] indices = findIndices(metricContract.getDataNameContract(), start, end);
        Aggregation aggregation = attachAggregation(metricContract);
        Aggregation bucket = attachBucket(start, end, metricContract);
        SearchResponse<Map> response = client.search(b -> {
            b.trackTotalHits(x -> x.enabled(true))
                    .trackScores(false)
                    .ignoreUnavailable(true)
                    .allowNoIndices(false)
                    .expandWildcards(ExpandWildcard.Open)
                    .index(Arrays.asList(indices))
                    .size(1)
                    .query(query);
            if(aggregation != null) {
                b.aggregations("metric", aggregation);
            }
            if(bucket != null) {
                b.aggregations("bucket", bucket);
            }
            return b;
        }, Map.class);
        long count = 0;
        if(response.hits().total() != null) {
            count = response.hits().total().value();
        }
        MetricData metricData = new MetricData();
        if(count == 0) {
            metricData.setMetricValue(0);
            return metricData;
        }
        Map<String, Object> doc = response.hits().hits().get(0).source();
        metricData.setLatestDocument(doc);
        if(metricContract.getAggregationType().equalsIgnoreCase("count")) {
            metricData.setMetricValue(count);
        } else {
            Aggregate aggregate = response.aggregations().get("metric");
            double value = findAggregationValue(metricContract, aggregate);
            metricData.setMetricValue(value);
        }
        Aggregate bucketAggregate = response.aggregations().get("bucket");
        metricData.setBuckets(findBucketValue(metricContract, bucketAggregate));
        return metricData;
    }

    private Aggregation attachBucket(DateTime start, DateTime end, MetricContract metricContract) {
        String bucketType = metricContract.getBucketType();
        String bucketField = metricContract.getBucketField();
        if(Strings.isNullOrEmpty(bucketType) || Strings.isNullOrEmpty(bucketField)) {
            return null;
        }
        if(bucketType.equalsIgnoreCase("terms")) {
            return TermsAggregation.of(t -> t.field(bucketField).size(50))._toAggregation();
        }
        if(bucketType.equalsIgnoreCase("date_histogram")) {
            Long intervalInMillis = Long.parseLong(metricContract.getProperties().getOrDefault("dateHistogramInterval", "3600000").toString());
            Long intervalInSeconds = intervalInMillis / 1000;
            return DateHistogramAggregation.of(d -> d
                    .field(bucketField)
                    .timeZone(DateTimeZone.getDefault().getID())
                    .format("yyyy-MM-dd'T'HH:mm:ssZ")
                    .fixedInterval(f -> f.time(intervalInSeconds + "s"))
                    .extendedBounds(e -> e.min(FieldDateMath.of(f -> f.value((double)start.getMillis())))
                            .max(FieldDateMath.of(f -> f.value((double)end.getMillis())))))._toAggregation();
        }
        return null;
    }

    private List<BucketInfo> findBucketValue(MetricContract metricContract, Aggregate aggregate) {
        String bucketType = metricContract.getBucketType();
        if(Strings.isNullOrEmpty(bucketType) || "none".equalsIgnoreCase(bucketType)) {
            return new ArrayList<>();
        }
        List<BucketInfo> bucketInfos = new ArrayList<>();
        if(bucketType.equalsIgnoreCase("terms")) {
            if(aggregate.isSterms()) {
                for (StringTermsBucket stringTermsBucket : aggregate.sterms().buckets().array()) {
                    BucketInfo bucketInfo = new BucketInfo();
                    bucketInfo.setKey(stringTermsBucket.key());
                    bucketInfo.setValue(stringTermsBucket.docCount());
                    bucketInfos.add(bucketInfo);
                }
            }
        } else if(bucketType.equalsIgnoreCase("date_histogram")) {
            for(DateHistogramBucket bucket : aggregate.dateHistogram().buckets().array()) {
                BucketInfo bucketInfo = new BucketInfo();
                bucketInfo.setKey(bucket.keyAsString());
                bucketInfo.setValue(bucket.docCount());
                bucketInfos.add(bucketInfo);
            }
        }
        return bucketInfos;
    }

    private Aggregation attachAggregation(MetricContract metricContract) {
        String aggType = metricContract.getAggregationType();
        String aggField = metricContract.getAggregationField();
        if("count".equalsIgnoreCase(aggType)) {
            return null;
        }
        if ("max".equalsIgnoreCase(aggType)) {
            return MaxAggregation.of(x -> x.field(aggField))._toAggregation();
        } else if ("min".equalsIgnoreCase(aggType)) {
            return MinAggregation.of(x -> x.field(aggField))._toAggregation();
        } else if ("avg".equalsIgnoreCase(aggType)) {
            return AverageAggregation.of(x -> x.field(aggField))._toAggregation();
        } else if ("sum".equalsIgnoreCase(aggType)) {
            return SumAggregation.of(x -> x.field(aggField))._toAggregation();
        } else if ("cardinality".equalsIgnoreCase(aggType)) {
            return CardinalityAggregation.of(x -> x.field(aggField))._toAggregation();
        } else if ("standard_deviation".equalsIgnoreCase(aggType)) {
            return ExtendedStatsAggregation.of(x -> x.field(aggField))._toAggregation();
        } else if ("percentiles".equalsIgnoreCase(aggType)) {
            return PercentilesAggregation.of(x -> x.percents(Double.parseDouble(metricContract.getProperties()
                    .get("percent").toString())).field(aggField))._toAggregation();
        }
        throw new RuntimeException("unknown aggregation type: " + aggType);
    }

    private Double findAggregationValue(MetricContract metricContract, Aggregate aggregate) {
        String aggType = metricContract.getAggregationType();
        if ("max".equalsIgnoreCase(aggType)) {
            return aggregate.max().value();
        }
        if ("min".equalsIgnoreCase(aggType)) {
            return aggregate.min().value();
        }
        if ("avg".equalsIgnoreCase(aggType)) {
            return aggregate.avg().value();
        }
        if ("sum".equalsIgnoreCase(aggType)) {
            return aggregate.sum().value();
        }
        if ("cardinality".equalsIgnoreCase(aggType)) {
            return (double)aggregate.cardinality().value();
        }
        if ("standard_deviation".equalsIgnoreCase(aggType)) {
            return aggregate.extendedStats().stdDeviation();
        }
        if ("percentiles".equalsIgnoreCase(aggType)) {
            return aggregate.tdigestPercentiles()
                    .values()
                    .keyed().values()
                    .stream().mapToDouble(Double::parseDouble).findFirst().orElse(0);
        }

        throw new IllegalArgumentException("unsupported aggregation type: " + aggType);
    }
}
