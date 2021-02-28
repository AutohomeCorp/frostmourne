package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;

public abstract class AbstractNumericMetric implements IMetric {

    public abstract MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings);

    public Integer findTimeWindowInMinutes(Map<String, String> ruleSettings) {
        return Integer.parseInt(ruleSettings.get("TIME_WINDOW"));
    }

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(findTimeWindowInMinutes(ruleSettings));
        Map<String, Object> result = new HashMap<>();
        MetricData elasticsearchMetric = pullMetricData(start, end, metricContract, ruleSettings);
        result.put("NUMBER", elasticsearchMetric.getMetricValue());
        if (elasticsearchMetric.getLatestDocument() != null) {
            result.putAll(elasticsearchMetric.getLatestDocument());
        }
        Map<String, String> dataNameProperties = metricContract.getDataNameContract().getSettings();
        result.putAll(dataNameProperties);
        result.put("startTime", start.toDateTimeISO().toString());
        result.put("endTime", end.toDateTimeISO().toString());
        return result;
    }
}
