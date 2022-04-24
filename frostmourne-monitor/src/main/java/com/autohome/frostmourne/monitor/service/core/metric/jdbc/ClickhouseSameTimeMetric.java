package com.autohome.frostmourne.monitor.service.core.metric.jdbc;

import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.query.IClickhouseDataQuery;

@Service
public class ClickhouseSameTimeMetric extends AbstractSameTimeMetric {

    @Autowired
    private IClickhouseDataQuery dataQuery;

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return dataQuery.queryMetricValue(start, end, metricContract);
    }

}
