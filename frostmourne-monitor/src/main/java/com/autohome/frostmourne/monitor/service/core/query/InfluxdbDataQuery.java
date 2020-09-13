package com.autohome.frostmourne.monitor.service.core.query;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;
import org.springframework.web.client.RestTemplate;

public class InfluxdbDataQuery {

    @Resource
    private RestTemplate restTemplate;

    public MetricData queryMetricData(DateTime start, DateTime end, MetricContract metricContract) {
        return new MetricData();
    }
}
