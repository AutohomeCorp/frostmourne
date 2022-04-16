package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.ContextConstant;
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;
import org.joda.time.DateTime;

public abstract class AbstractRule implements IRule {

    private ITemplateService templateService;

    public AbstractRule(ITemplateService templateService) {
        this.templateService = templateService;
    }

    public Map<String, Object> context(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract,
        MetricContract metricContract, IMetric metric) {
        alarmProcessLogger.trace("start pull metric");
        Map<String, Object> metricData = metric.pullMetric(metricContract, ruleContract.getSettings());
        Map<String, Object> context = new HashMap<>(metricData);
        if (ruleContract.getSettings() != null) {
            context.putAll(ruleContract.getSettings());
        }
        // silence
        context.put(ContextConstant.ALERT_SILENCE, alarmProcessLogger.getAlarmContract().getAlertContract().getSilence());
        context.put(ContextConstant.CURRENT_TIME, DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        context.put(ContextConstant.ALARM_ID, alarmProcessLogger.getAlarmContract().getId());
        context.put(ContextConstant.ALARM_NAME, alarmProcessLogger.getAlarmContract().getAlarmName());

        alarmProcessLogger.trace("env = %s", JacksonUtil.serialize(context));
        alarmProcessLogger.setContext(context);
        return context;
    }

    public String alertMessage(String alertTemplate, Map<String, Object> context) {
        return this.templateService.format(alertTemplate, context);
    }

}
