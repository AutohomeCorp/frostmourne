package com.autohome.frostmourne.monitor.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.core.contract.Protocol;
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
        String dataSourceType;
        if (alarmContract.getMetricContract().getDataNameId() != null
            && alarmContract.getMetricContract().getDataNameId() > 0) {
            dataSourceType = alarmContract.getMetricContract().getDataSourceContract().getDatasourceType();
        } else {
            dataSourceType = alarmContract.getMetricContract().getDataName();
        }
        IMetric metric =
            this.metricService.findMetric(dataSourceType, alarmContract.getMetricContract().getMetricType());
        Map<String, Object> result =
            metric.pullMetric(alarmContract.getMetricContract(), alarmContract.getRuleContract().getSettings());
        if (alarmContract.getRuleContract().getSettings() != null) {
            result.putAll(alarmContract.getRuleContract().getSettings());
        }
        return new Protocol<>(result);
    }
}
