package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.common.exception.DataQueryException;
import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;
import org.joda.time.DateTime;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;

public abstract class AbstractNumericMetric extends AbstractBaseMetric {

    public abstract MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) throws DataQueryException;

    public Integer findTimeWindowInMinutes(Map<String, String> ruleSettings) {
        if (!ruleSettings.containsKey("TIME_WINDOW")) {
            return null;
        }
        return Integer.parseInt(ruleSettings.get("TIME_WINDOW"));
    }

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) throws DataQueryException {
        DateTime end = DateTime.now();
        DateTime start = null;
        Integer minutes = findTimeWindowInMinutes(ruleSettings);
        if (minutes != null) {
            start = end.minusMinutes(findTimeWindowInMinutes(ruleSettings));
        }
        Map<String, Object> result = new HashMap<>();
        MetricData metricData = pullMetricData(start, end, metricContract, ruleSettings);
        result.put("NUMBER", metricData.getMetricValue());
        result.put("BUCKETS", metricData.getBuckets());
        if (metricData.getLatestDocument() != null) {
            result.putAll(metricData.getLatestDocument());
        }
        Map<String, String> dataNameProperties = metricContract.getDataNameContract().getSettings();
        result.putAll(dataNameProperties);
        if (start != null) {
            result.put("startTime", start.toDateTimeISO().toString());
        }
        result.put("endTime", end.toDateTimeISO().toString());
        result.put("QUERY_STRING", metricContract.getQueryString());
        return result;
    }

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.numeric;
    }
}
