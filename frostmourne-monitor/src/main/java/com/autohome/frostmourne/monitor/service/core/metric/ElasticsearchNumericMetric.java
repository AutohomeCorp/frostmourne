package com.autohome.frostmourne.monitor.service.core.metric;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchInfo;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.dao.elasticsearch.EsRestClientContainer;
import com.autohome.frostmourne.monitor.service.core.domain.ElasticsearchMetric;
import com.autohome.frostmourne.monitor.service.core.query.IElasticsearchDataQuery;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchNumericMetric extends AbstractCountMetric {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchNumericMetric.class);

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

    @Resource
    private IElasticsearchDataQuery elasticsearchDataQuery;

    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(metricContract.getDataSourceContract());
        EsRestClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(findTimeWindowInMinutes(settings));
        Map<String, Object> result = new HashMap<>();
        try {
            ElasticsearchMetric elasticsearchMetric = elasticsearchDataQuery.queryElasticsearchMetricValue(start, end, metricContract);
            result.put("NUMBER", elasticsearchMetric.getMetricValue());
            if (elasticsearchMetric.getLatestDocument() != null) {
                result.putAll(elasticsearchMetric.getLatestDocument());
            }
        } catch (IOException ex) {
            throw new RuntimeException("error when queryElasticsearchMetricValue", ex);
        }
        Map<String, String> dataNameProperties = metricContract.getDataNameContract().getSettings();
        String indexPrefix = dataNameProperties.get("indexPrefix");
        String datePattern = dataNameProperties.get("timePattern");
        String[] indices = esRestClientContainer.buildIndices(start, end, indexPrefix, datePattern);
        result.put("startTime", start.toDateTimeISO().toString());
        result.put("endTime", end.toDateTimeISO().toString());
        result.put("indices", indices);

        return result;
    }
}
