package com.autohome.frostmourne.monitor.service.core.metric;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;

public interface IMetricService {

    IMetric findMetric(DataSourceType dataSourceType, String metricType);
}
