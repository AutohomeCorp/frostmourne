package com.autohome.frostmourne.monitor.service.core.execute;

import com.autohome.frostmourne.monitor.model.enums.VerifyResult;
import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.AlertTemplateType;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;

public class AlarmExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmExecutor.class);

    private final AlarmContract alarmContract;

    private final IRule rule;

    private final IMetric metric;

    private final AlarmProcessLogger alarmProcessLogger;

    public AlarmExecutor(AlarmContract alarmContract, IRule rule, IMetric metric) {
        this.alarmContract = alarmContract;
        this.rule = rule;
        this.metric = metric;
        this.alarmProcessLogger = new AlarmProcessLogger();
    }

    public AlarmProcessLogger execute() {
        this.alarmProcessLogger.setAlarmContract(this.alarmContract);
        this.alarmProcessLogger.trace("execute start");
        this.alarmProcessLogger.setStart(DateTime.now());
        ExecuteStatus executeStatus = doRule();
        this.alarmProcessLogger.setExecuteStatus(executeStatus);
        this.alarmProcessLogger.trace("execute end");
        this.alarmProcessLogger.setEnd(DateTime.now());

        return this.alarmProcessLogger;
    }

    private ExecuteStatus doRule() {
        try {
            boolean verifyResult = this.rule.verify(this.alarmProcessLogger, alarmContract.getRuleContract(), alarmContract.getMetricContract(), metric);
            this.alarmProcessLogger.setVerifyResult(verifyResult ? VerifyResult.TRUE : VerifyResult.FALSE);
            return ExecuteStatus.SUCCESS;
        } catch (Exception ex) {
            LOGGER.error("error when doRule", ex);
            alarmProcessLogger.trace("error: " + ex.getMessage());
            return ExecuteStatus.ERROR;
        }
    }

}
