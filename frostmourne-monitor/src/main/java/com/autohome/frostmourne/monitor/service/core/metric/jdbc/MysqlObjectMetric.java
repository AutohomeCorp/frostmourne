package com.autohome.frostmourne.monitor.service.core.metric.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.query.IMysqlDataQuery;

@Service
public class MysqlObjectMetric implements IMetric {

    @Autowired
    protected IMysqlDataQuery mysqlDataQuery;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        Map<String, Object> result = new HashMap<>();
        MetricData metricData = mysqlDataQuery.querySql(metricContract);
        result.put("NUMBER", metricData.getMetricValue());
        if (metricData.getLatestDocument() != null) {
            result.putAll(metricData.getLatestDocument());
        }
        Map<String, String> dataNameProperties = metricContract.getDataNameContract().getSettings();
        result.putAll(dataNameProperties);
        return result;
    }
}
