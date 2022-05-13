package com.autohome.frostmourne.monitor.service.core.metric.prometheus;

import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractNumericMetric;
import com.autohome.frostmourne.monitor.service.core.query.IPrometheusDataQuery;
import org.joda.time.DateTime;

public class PrometheusObjectMetric extends AbstractNumericMetric {

    @Resource
    private IPrometheusDataQuery prometheusDataQuery;

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return prometheusDataQuery.queryMetricData(start, end, metricContract);
    }
}
