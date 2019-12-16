package com.autohome.frostmourne.monitor.controller;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;
import com.autohome.frostmourne.monitor.service.core.execute.IAlarmService;
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
}
