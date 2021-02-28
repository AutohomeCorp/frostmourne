package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;

public interface IMetric {

    Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings);
}
