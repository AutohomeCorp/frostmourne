package com.autohome.frostmourne.monitor.service.core.metric.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.query.IClickhouseDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/6 18:36
 */
@Service
public class ClickhouseObjectMetric implements IMetric {

    @Autowired
    private IClickhouseDataQuery dataQuery;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        Map<String, Object> result = new HashMap<>();
        MetricData metricData = dataQuery.querySql(metricContract);
        result.put("NUMBER", metricData.getMetricValue());
        if (metricData.getLatestDocument() != null) {
            result.putAll(metricData.getLatestDocument());
        }
        if (metricData.getTopNDocuments() != null) {
            result.put("TOP_N_DOCUMENTS", metricData.getTopNDocuments());
        }
        Map<String, String> dataNameProperties = metricContract.getDataNameContract().getSettings();
        result.putAll(dataNameProperties);
        return result;
    }
}
