package com.autohome.frostmourne.monitor.service.core.execute;

import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.contract.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;
import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlarmExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmExecutor.class);

    private AlarmContract alarmContract;

    private IRule rule;

    private IMetric metric;

    private IGenerateShortLinkService generateShortLinkService;

    private AlarmProcessLogger alarmProcessLogger;

    public AlarmExecutor(AlarmContract alarmContract, IRule rule, IMetric metric, IGenerateShortLinkService generateShortLinkService) {
        this.alarmContract = alarmContract;
        this.rule = rule;
        this.metric = metric;
        this.generateShortLinkService = generateShortLinkService;
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
            boolean isAlert = this.rule.verify(this.alarmProcessLogger, alarmContract.getRuleContract(), alarmContract.getMetricContract(), metric);
            this.alarmProcessLogger.setAlert(isAlert);
            if (isAlert) {
                String completeMessage = completeAlertMessage();
                alarmProcessLogger.setAlertMessage(completeMessage);
            }
            return ExecuteStatus.SUCCESS;
        } catch (Exception ex) {
            LOGGER.error("error when doRule", ex);
            return ExecuteStatus.ERROR;
        }
    }

    private String completeAlertMessage() {
        String alertMessage = this.rule.alertMessage(alarmContract.getRuleContract(), this.alarmProcessLogger.getContext());
        String timeString = DateTime.now().toString("yyyy-MM-dd hh:mm:ss");
        String shortLink = generateShortLinkService.generate(alarmProcessLogger);
        String completeMessage = null;
        if (Strings.isNullOrEmpty(shortLink)) {
            completeMessage = String.format("[%s]\n%s", timeString, alertMessage);
        } else {
            completeMessage = String.format("[%s]\n%s\n\n详细请看: %s", timeString, alertMessage, shortLink);
        }
        return completeMessage;
    }
}
