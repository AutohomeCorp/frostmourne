package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.constant.ContextConstant;
import com.autohome.frostmourne.monitor.model.constant.GlobalConstant;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;
import com.autohome.frostmourne.monitor.tool.MD5Utils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

public abstract class AbstractRule implements IRule {

    private final ITemplateService templateService;

    public final Configuration jsonPathConfiguration =
        Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL).addOptions(Option.SUPPRESS_EXCEPTIONS);

    public AbstractRule(ITemplateService templateService) {
        this.templateService = templateService;
    }

    public Map<String, Object> context(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric) {
        alarmProcessLogger.trace("start pull metric");
        Map<String, Object> metricData = metric.pullMetric(metricContract, ruleContract.getSettings());

        alertEventMd5(alarmProcessLogger, metricData);

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

    private void alertEventMd5(AlarmProcessLogger alarmProcessLogger, Map<String, Object> metricData) {
        String silenceExpression = alarmProcessLogger.getAlarmContract().getAlertContract().getSilenceExpression();
        if (StringUtils.isBlank(silenceExpression)) {
            return;
        }
        String expressionKeys =
            silenceExpression.replaceAll("&&", "").replaceAll("\\|\\|", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(" +", " ").trim();
        String[] keys = expressionKeys.split(" ");

        Map<String, String> eventMd5 = new HashMap<>(keys.length);
        Arrays.stream(keys).forEach(key -> {
            // json-path
            Object result = JsonPath.using(jsonPathConfiguration).parse(metricData).read(key);

            String value = result == null ? "" : JacksonUtil.serialize(result);
            eventMd5.put(key, MD5Utils.md5Hex(value, GlobalConstant.ENCODE));
        });
        // set event_md5 for saving AlertEvent
        alarmProcessLogger.setEventMd5(eventMd5);
    }

    @Override
    public String alertMessage(String alertTemplate, Map<String, Object> context) {
        return this.templateService.format(alertTemplate, context);
    }

}
