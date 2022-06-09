package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;

public interface IMetric {

    Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings);

    MetricEnumType metricType();

    boolean matchMetricType(String metricType);

    boolean matchDataSourceType(String dataSourceType);
}
