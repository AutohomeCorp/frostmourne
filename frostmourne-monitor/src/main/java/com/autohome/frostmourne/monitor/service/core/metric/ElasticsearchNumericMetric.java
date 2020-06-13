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
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
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
        Map<String, Object> result = new HashMap<>();
        result.put("startTime", start.toDateTimeISO().toString());
        result.put("endTime", end.toDateTimeISO().toString());
        result.put("indices", indices);
        Long count = null;
        try {
            count = esRestClientContainer.totalCount(boolQueryBuilder, indices);
            result.put("COUNT", count);
        } catch (Exception ex) {
            throw new RuntimeException("error when totalCount");
        }
        if (metricContract.getAggregation_type().equalsIgnoreCase("count")) {
            result.put("NUMBER", count);
        }
        if (count == 0) {
            return result;
        }
        SearchRequest searchRequest = new SearchRequest(indices);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.trackScores(false);
        searchSourceBuilder.trackTotalHits(true);
        searchSourceBuilder.query(boolQueryBuilder).size(1)
                .sort(metricContract.getDataNameContract().getTimestamp_field(), SortOrder.DESC);
        attachAggregation(metricContract, searchSourceBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = esRestClientContainer.fetchHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
            SearchHit latestDoc = searchResponse.getHits().getAt(0);
            result.putAll(latestDoc.getSourceAsMap());
            if (metricContract.getAggregation_type().equalsIgnoreCase("count")) {
                if (searchResponse.getHits().getTotalHits() > 0) {
                    result.put("NUMBER", searchResponse.getHits().getTotalHits());
                    result.put("COUNT", searchResponse.getHits().getTotalHits());
                }
            } else {
                Double numericValue = findAggregationValue(metricContract, searchResponse);
                result.put("NUMBER", numericValue);
            }
        } catch (Exception ex) {
            LOGGER.error("error when pullMetric", ex);
        }

        return result;
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
}
