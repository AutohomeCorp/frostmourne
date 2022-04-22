package com.autohome.frostmourne.monitor.service.core.metric.influxdb;

import java.util.Map;

import org.joda.time.DateTime;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractNumericMetric;
import com.autohome.frostmourne.monitor.service.core.query.IInfluxdbDataQuery;

public class InfluxdbNumericMetric extends AbstractNumericMetric {

    private final IInfluxdbDataQuery influxdbDataQuery;

    public InfluxdbNumericMetric(IInfluxdbDataQuery influxdbDataQuery) {
        this.influxdbDataQuery = influxdbDataQuery;
    }

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract,
        Map<String, String> ruleSettings) {
        return influxdbDataQuery.queryMetricData(start, end, metricContract);
    }
}
