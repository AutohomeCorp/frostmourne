package com.autohome.frostmourne.monitor.service.core.execute;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
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
        String dataSourceType;
        if (alarmContract.getMetricContract().getDataNameId() != null && alarmContract.getMetricContract().getDataNameId() > 0) {
            dataSourceType = alarmContract.getMetricContract().getDataSourceContract().getDatasourceType();
        } else {
            dataSourceType = alarmContract.getMetricContract().getDataName();
        }
        IMetric metric = this.metricService.findMetric(dataSourceType, alarmContract.getMetricContract().getMetricType());
        AlarmExecutor alarmExecutor = new AlarmExecutor(alarmContract, rule, metric, generateShortLinkService);
        AlarmProcessLogger alarmProcessLogger = alarmExecutor.execute();
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

    private void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status) {
        alarmAdminService.updateAlarmLastExecuteInfo(alarmId, executeTime, status);
    }

    private void alarmLog(AlarmProcessLogger alarmProcessLogger) {
        alertService.alarmLog(alarmProcessLogger);
    }

}
