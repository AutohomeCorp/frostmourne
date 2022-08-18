package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.template.ITemplateService;

public class ExpressionRule extends AbstractRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionRule.class);

    private final ScriptEngine scriptEngine;

    public ExpressionRule(ITemplateService templateService) {
        super(templateService);
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
    }

    @Override
    public boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric) {
        Map<String, Object> context = context(alarmProcessLogger, ruleContract, metricContract, metric);
        checkMetricRunState(context);

        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.putAll(context);
        Object result;
        String expression = ruleContract.getSettings().get("EXPRESSION");
        try {
            result = scriptEngine.eval(expression, simpleBindings);
        } catch (ScriptException ex) {
            LOGGER.error("error when execute js expression： " + expression, ex);
            throw new RuntimeException("error when execute js expression： " + expression, ex);
        }
        return (Boolean)result;
    }
}
