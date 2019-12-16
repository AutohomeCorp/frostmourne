package com.autohome.frostmourne.monitor.service.core.metric;

public interface IMetricService {

    IMetric findMetric(String dataSourceType, String metricType);
}
