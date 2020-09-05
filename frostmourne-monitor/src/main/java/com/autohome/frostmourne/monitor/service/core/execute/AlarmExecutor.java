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
        String alertMessage = this.rule.alertMessage(alarmContract.getRuleContract(), this.alarmProcessLogger.getContext());
        StringBuilder stringBuilder = new StringBuilder(alertMessage.length() * 2);
        String timeString = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        String shortLink = generateShortLinkService.generate(alarmProcessLogger);
        stringBuilder.append("[").append(timeString).append("]");
        if (!Strings.isNullOrEmpty(this.alarmProcessLogger.getAlarmContract().getRisk_level())) {
            String risk = riskTranslation(this.alarmProcessLogger.getAlarmContract().getRisk_level());
            stringBuilder.append("[").append(risk).append("]").append("\n");
        }
        stringBuilder.append(alertMessage);
        if (!Strings.isNullOrEmpty(shortLink)) {
            stringBuilder.append("\n\n").append("详细请看: ").append(shortLink);
        }
        return stringBuilder.toString();
    }

    private String riskTranslation(String riskLevel) {
        if (Strings.isNullOrEmpty(riskLevel)) {
            return null;
        }
        if (riskLevel.equalsIgnoreCase("info")) {
            return "通知";
        }
        if (riskLevel.equalsIgnoreCase("important")) {
            return "重要";
        }
        if (riskLevel.equalsIgnoreCase("emergency")) {
            return "紧急";
        }
        if (riskLevel.equalsIgnoreCase("crash")) {
            return "我崩了";
        }
        throw new IllegalArgumentException("unknown risk level: " + riskLevel);
    }
}
