package com.autohome.frostmourne.monitor.controller;

import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.execute.IAlarmService;
import com.autohome.frostmourne.monitor.service.core.metric.HttpMetric;
import com.autohome.frostmourne.monitor.service.core.metric.IMetric;
import com.autohome.frostmourne.monitor.service.core.metric.IMetricService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Protocol<String> run(String _appId, Long alarmId) {
        String account = AuthTool.currentUser().getAccount();
        AlarmProcessLogger alarmProcessLogger = alarmService.run(account, alarmId, false);
        return new Protocol<>(alarmProcessLogger.traceInfo());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Protocol<String> test(@RequestParam(value = "_appId", required = true) String _appId, @RequestBody AlarmContract alarmContract) {
        AlarmProcessLogger alarmProcessLogger = alarmService.test(alarmContract);
        return new Protocol<>(alarmProcessLogger.traceInfo());
    }

    @RequestMapping(value = "/previewData", method = RequestMethod.POST)
    public Protocol<Map<String, Object>> httpTest(@RequestParam(value = "_appId", required = true) String _appId,
                                                  @RequestBody AlarmContract alarmContract) {
        alarmAdminService.padAlarm(alarmContract);
        String dataSourceType = null;
        if (alarmContract.getMetricContract().getDataNameId() != null && alarmContract.getMetricContract().getDataNameId() > 0) {
            dataSourceType = alarmContract.getMetricContract().getDataSourceContract().getDatasourceType();
        } else {
            dataSourceType = alarmContract.getMetricContract().getDataName();
        }
        IMetric metric = this.metricService.findMetric(dataSourceType, alarmContract.getMetricContract().getMetricType());
        Map<String, Object> result = metric.pullMetric(alarmContract.getMetricContract(), alarmContract.getRuleContract().getSettings());
        if (alarmContract.getRuleContract().getSettings() != null) {
            result.putAll(alarmContract.getRuleContract().getSettings());
        }
        return new Protocol<>(result);
    }
}
