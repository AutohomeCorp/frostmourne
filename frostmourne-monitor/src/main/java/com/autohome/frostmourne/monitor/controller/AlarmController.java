package com.autohome.frostmourne.monitor.controller;

import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.common.exception.DataQueryException;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.model.request.TransferToTeamRequest;
import com.autohome.frostmourne.monitor.schedule.CronExpression;
import com.autohome.frostmourne.monitor.tool.LocalDateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.common.contract.Protocol;
import com.autohome.frostmourne.monitor.controller.annotation.PermissionLimit;
import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.execute.IAlarmService;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.metric.IMetricService;

@RestController
@RequestMapping(value = {"/alarm", "/api/monitor-api/alarm"})
public class AlarmController {

    @Resource
    private IAlarmService alarmService;

    @Resource
    private IMetricService metricService;

    @Resource
    private IAlarmAdminService alarmAdminService;

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    @PermissionLimit(limit = false)
    public Protocol<String> run(Long alarmId) {
        AlarmProcessLogger alarmProcessLogger = alarmService.run(alarmId, false);
        return new Protocol<>(alarmProcessLogger.traceInfo());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Protocol<String> test(@RequestBody AlarmContract alarmContract) {
        AlarmProcessLogger alarmProcessLogger = alarmService.test(alarmContract);
        return new Protocol<>(alarmProcessLogger.traceInfo());
    }

    @RequestMapping(value = "/previewData", method = RequestMethod.POST)
    public Protocol<Map<String, Object>> httpTest(@RequestBody AlarmContract alarmContract) {
        alarmAdminService.padAlarm(alarmContract);
        DataSourceType dataSourceType;
        if (alarmContract.getMetricContract().getDataNameId() != null && alarmContract.getMetricContract().getDataNameId() > 0) {
            dataSourceType = alarmContract.getMetricContract().getDataSourceContract().getDatasourceType();
        } else {
            dataSourceType = DataSourceType.valueOf(alarmContract.getMetricContract().getDataName());
        }
        IMetric metric = this.metricService.findMetric(dataSourceType, alarmContract.getMetricContract().getMetricType());
        Map<String, Object> result = null;
        try {
            result = metric.pullMetric(alarmContract.getMetricContract(), alarmContract.getRuleContract().getSettings());
        } catch (DataQueryException e) {
            return Protocol.fail(e.getMessage());
        }

        if (alarmContract.getRuleContract().getSettings() != null) {
            result.putAll(alarmContract.getRuleContract().getSettings());
        }
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/nextTriggerTime", method = RequestMethod.GET)
    public Protocol<List<String>> getNextTriggerTime(String cron) {
        List<String> result = new ArrayList<>();
        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date lastTime = new Date();
            for (int i = 0; i < 5; i++) {
                lastTime = cronExpression.getNextValidTimeAfter(lastTime);
                if (lastTime != null) {
                    result.add(LocalDateTimeUtils.formatNormal(lastTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
                } else {
                    break;
                }
            }
        } catch (ParseException e) {
            return new Protocol<>(HttpStatus.BAD_REQUEST.value(), "Cron expression parse exception.");
        }
        return new Protocol<>(result);
    }

    @PostMapping(value = "/transferToTeam")
    public Protocol<String> transferToTeam(@RequestBody TransferToTeamRequest transferToTeamRequest) {
        this.alarmAdminService.updateTeamNameByIdList(transferToTeamRequest.getAlarmIdList(), transferToTeamRequest.getNewTeamName());
        return new Protocol<>("ok");
    }
}
