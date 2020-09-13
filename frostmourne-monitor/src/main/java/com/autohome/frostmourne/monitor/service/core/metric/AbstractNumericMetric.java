package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;

public abstract class AbstractNumericMetric implements IMetric {

    public abstract MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> settings);

    public Integer findTimeWindowInMinutes(Map<String, String> settings) {
        return Integer.parseInt(settings.get("TIME_WINDOW"));
    }

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {
        DateTime end = DateTime.now();
        DateTime start = end.minusMinutes(findTimeWindowInMinutes(settings));
        Map<String, Object> result = new HashMap<>();
        MetricData elasticsearchMetric = pullMetricData(start, end, metricContract, settings);
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
