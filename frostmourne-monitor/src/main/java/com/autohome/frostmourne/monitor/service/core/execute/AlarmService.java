package com.autohome.frostmourne.monitor.service.core.execute;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import org.elasticsearch.common.Strings;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.AlertTemplateType;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.model.enums.VerifyResult;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.core.alert.IAlertService;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.metric.IMetricService;
import com.autohome.frostmourne.monitor.service.core.rule.IRule;
import com.autohome.frostmourne.monitor.service.core.rule.IRuleService;

@Service
public class AlarmService implements IAlarmService {

    @Resource
    private IMetricService metricService;

    @Resource
    private IRuleService ruleService;

    @Resource
    private IAlarmAdminService alarmAdminService;

    @Resource
    private IAlertService alertService;

    @Resource
    private IGenerateShortLinkService generateShortLinkService;

    @Resource
    private IAlarmLogRepository alarmLogRepository;

    @Override
    public AlarmProcessLogger run(Long alarmId, boolean test) {
        AlarmContract alarmContract = this.alarmAdminService.findById(alarmId);
        return run(alarmContract, test);
    }

    @Override
    public AlarmProcessLogger test(AlarmContract alarmContract) {
        alarmAdminService.padAlarm(alarmContract);
        return run(alarmContract, true);
    }

    @Override
    public AlarmProcessLogger run(AlarmContract alarmContract, boolean test) {
        IRule rule = this.ruleService.findRule(alarmContract.getRuleContract().getRuleType());
        DataSourceType dataSourceType;
        if (alarmContract.getMetricContract().getDataNameId() != null && alarmContract.getMetricContract().getDataNameId() > 0) {
            dataSourceType = alarmContract.getMetricContract().getDataSourceContract().getDatasourceType();
        } else {
            dataSourceType = DataSourceType.valueOf(alarmContract.getMetricContract().getDataName());
        }
        IMetric metric = this.metricService.findMetric(dataSourceType, alarmContract.getMetricContract().getMetricType());
        AlarmExecutor alarmExecutor = new AlarmExecutor(alarmContract, rule, metric);
        AlarmProcessLogger alarmProcessLogger = alarmExecutor.execute();

        // judge alert
        boolean isAlert = VerifyResult.TRUE.equals(alarmProcessLogger.getVerifyResult()) && judgeAlert(alarmProcessLogger);
        alarmProcessLogger.setAlert(isAlert);
        alarmProcessLogger.trace("isAlert: " + isAlert);
        if (isAlert) {
            String completeMessage = completeAlertMessage(alarmContract, alarmProcessLogger, rule);
            alarmProcessLogger.setAlertMessage(completeMessage);
            alarmProcessLogger.trace("alertMessage: \r\n" + completeMessage);
        }

        if (!test) {
            updateAlarmLastExecuteInfo(alarmContract.getId(), alarmProcessLogger.getStart().toDate(), alarmProcessLogger.getExecuteStatus());
            if (alarmProcessLogger.getExecuteStatus() == ExecuteStatus.ERROR) {
                alarmLog(alarmProcessLogger);
            } else {
                alertService.alert(alarmProcessLogger);
            }
        } else {
            if (alarmProcessLogger.getAlert() != null && alarmProcessLogger.getAlert()) {
                alarmProcessLogger.trace("test alarm, not send");
            }
        }
        return alarmProcessLogger;
    }

    private boolean judgeAlert(AlarmProcessLogger alarmProcessLogger) {
        String alertCondition = alarmProcessLogger.getAlarmContract().getRuleContract().getSettings().get("ALERT_CONDITION");
        if (alertCondition == null || Integer.parseInt(alertCondition) <= 1) {
            return true;
        }

        List<AlarmLog> alarmLogList = alarmLogRepository.selectRecently(Integer.parseInt(alertCondition) - 1);

        return alarmLogList.size() == Integer.parseInt(alertCondition) - 1
            && alarmLogList.stream().noneMatch(alarmLog -> VerifyResult.FALSE.equals(alarmLog.getVerifyResult()));
    }

    private String completeAlertMessage(AlarmContract alarmContract, AlarmProcessLogger alarmProcessLogger, IRule rule) {
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

        return rule.alertMessage(messageBuilder.toString(), alarmProcessLogger.getContext());
    }

    private void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status) {
        alarmAdminService.updateAlarmLastExecuteInfo(alarmId, executeTime, status);
    }

    private void alarmLog(AlarmProcessLogger alarmProcessLogger) {
        alertService.alarmLog(alarmProcessLogger);
    }

}
