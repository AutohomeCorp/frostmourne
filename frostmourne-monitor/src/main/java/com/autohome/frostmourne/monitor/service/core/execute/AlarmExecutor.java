package com.autohome.frostmourne.monitor.service.core.execute;

import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.contract.enums.AlertTemplateType;
import com.autohome.frostmourne.monitor.contract.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;

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
            this.alarmProcessLogger.trace("isAlert: " + isAlert);
            if (isAlert) {
                String completeMessage = completeAlertMessage();
                alarmProcessLogger.setAlertMessage(completeMessage);
                alarmProcessLogger.trace("alertMessage: \r\n" + completeMessage);
            }
            return ExecuteStatus.SUCCESS;
        } catch (Exception ex) {
            LOGGER.error("error when doRule", ex);
            return ExecuteStatus.ERROR;
        }
    }

    private String completeAlertMessage() {
        StringBuilder messageBuilder = new StringBuilder(alarmContract.getRuleContract().getAlertTemplate());
        // 链接前置，支持自定义链接占位符替换
        String shortLink = generateShortLinkService.generate(alarmProcessLogger);
        if (!Strings.isNullOrEmpty(shortLink)) {
            if (AlertTemplateType.MARKDOWN.equals(alarmContract.getRuleContract().getAlertTemplateType())) {
                messageBuilder.append("\n\n").append("[查看全部](").append(shortLink).append(")");
            } else {
                messageBuilder.append("\n\n").append("详细请看: ").append(shortLink);
            }
        }

        return this.rule.alertMessage(messageBuilder.toString(), this.alarmProcessLogger.getContext());
    }

}
