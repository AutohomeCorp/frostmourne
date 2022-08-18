package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;

public class PingRule extends AbstractRule {

    public PingRule(ITemplateService templateService) {
        super(templateService);
    }

    @Override
    public boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric) {
        Map<String, Object> context = context(alarmProcessLogger, ruleContract, metricContract, metric);
        checkMetricRunState(context);

        Integer failCount = (Integer)context.get("FAIL_COUNT");
        return failCount > 0;
    }
}
