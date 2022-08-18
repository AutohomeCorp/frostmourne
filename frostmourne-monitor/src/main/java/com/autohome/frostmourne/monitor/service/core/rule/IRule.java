package com.autohome.frostmourne.monitor.service.core.rule;

import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.contract.RuleContract;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;

public interface IRule {

    boolean verify(AlarmProcessLogger alarmProcessLogger, RuleContract ruleContract, MetricContract metricContract, IMetric metric);

    String alertMessage(String alertTemplate, Map<String, Object> context);

    /**
     * 检查指标运行状态
     *
     * 如果执行异常则上下文包含异常字段，抛出异常
     *
     * @param context 上下文
     */
    default void checkMetricRunState(Map<String, Object> context) {
        if (context.containsKey("EXEC_STATUS") && context.containsKey("EXEC_ERR")) {
            throw new RuntimeException("EXEC_STATUS=" + context.get("EXEC_STATUS").toString()
                    + " | EXEC_ERR=" + context.get("EXEC_ERR").toString());
        }
    }
}
