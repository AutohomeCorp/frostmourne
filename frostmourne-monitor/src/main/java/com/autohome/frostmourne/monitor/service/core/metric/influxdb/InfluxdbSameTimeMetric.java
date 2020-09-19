package com.autohome.frostmourne.monitor.service.core.metric.influxdb;

import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.query.IInfluxdbDataQuery;
import org.joda.time.DateTime;

public class InfluxdbSameTimeMetric extends AbstractSameTimeMetric {

    private IInfluxdbDataQuery influxdbDataQuery;

    public InfluxdbSameTimeMetric(IInfluxdbDataQuery influxdbDataQuery) {
        this.influxdbDataQuery = influxdbDataQuery;
    }

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return influxdbDataQuery.queryMetricData(start, end, metricContract);
    }
}
