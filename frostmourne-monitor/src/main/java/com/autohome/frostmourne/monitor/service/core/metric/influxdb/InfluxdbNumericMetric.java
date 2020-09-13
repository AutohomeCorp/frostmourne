package com.autohome.frostmourne.monitor.service.core.metric.influxdb;

import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractNumericMetric;
import org.joda.time.DateTime;

public class InfluxdbNumericMetric extends AbstractNumericMetric {

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> settings) {
        return null;
    }
}
