package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;

public interface IRule {

    boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric);

    String alertMessage(RuleContract ruleContract, Map<String, Object> context);
}
