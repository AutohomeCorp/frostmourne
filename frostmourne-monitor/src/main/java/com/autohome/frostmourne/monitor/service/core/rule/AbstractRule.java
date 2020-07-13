package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;

public abstract class AbstractRule implements IRule {

    private ITemplateService templateService;

    public AbstractRule(ITemplateService templateService) {
        this.templateService = templateService;
    }

    public Map<String, Object> context(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract,
                                       MetricContract metricContract, IMetric metric) {
        Map<String, Object> context = new HashMap<>();
        alarmProcessLogger.trace("start pull metric");
        Map<String, Object> metricData = metric.pullMetric(metricContract, ruleContract.getSettings());
        context.putAll(metricData);
        if (ruleContract.getSettings() != null) {
            context.putAll(ruleContract.getSettings());
        }
        alarmProcessLogger.trace("env = %s", JacksonUtil.serialize(context));
        alarmProcessLogger.setContext(context);
        return context;
    }

    public String alertMessage(RuleContract ruleContract, Map<String, Object> context) {
        return this.templateService.format(ruleContract.getAlert_template(), context);
    }

}
