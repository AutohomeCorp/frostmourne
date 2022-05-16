package com.autohome.frostmourne.monitor.controller;

import java.util.Date;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.enums.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import com.autohome.frostmourne.monitor.service.admin.ILogService;
import com.autohome.frostmourne.monitor.tool.AuthTool;

@RestController
@RequestMapping(value = {"/log", "/api/monitor-api/log"})
public class LogController {

    @Resource
    private ILogService logService;

    @RequestMapping(value = "/findAlarmLog", method = RequestMethod.GET)
    public Protocol<PagerContract<AlarmLog>> findAlarmLog(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize,
        @RequestParam(value = "startTime", required = false) Date startTime, @RequestParam(value = "endTime", required = false) Date endTime,
        @RequestParam(value = "alarmId", required = false) Long alarmId, @RequestParam(value = "alert", required = false) VerifyResult verifyResult,
        @RequestParam(value = "executeResult", required = false) ExecuteStatus executeResult, @RequestParam(value = "alert", required = false) Boolean alert) {
        PagerContract<AlarmLog> pagerContract = logService.findAlarmLog(pageIndex, pageSize, startTime, endTime, alarmId, verifyResult, executeResult, alert);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "/findAlertLog", method = RequestMethod.GET)
    public Protocol<PagerContract<AlertLog>> findAlertLog(@RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize,
        @RequestParam(value = "startTime") Date startTime, @RequestParam(value = "endTime") Date endTime,
        @RequestParam(value = "executeId", required = false) Long executeId, @RequestParam(value = "alarmId", required = false) Long alarmId,
        @RequestParam(value = "way", required = false) String way, @RequestParam(value = "sendStatus", required = false) SendStatus sendStatus,
        @RequestParam(value = "inSilence", required = false) SilenceStatus inSilence,
        @RequestParam(value = "alertType", required = false) AlertType alertType) {
        String account = AuthTool.currentUser().getAccount();
        PagerContract<AlertLog> pagerContract =
            logService.findAlertLog(pageIndex, pageSize, startTime, endTime, executeId, alarmId, account, way, sendStatus, inSilence, alertType);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "log4j", method = RequestMethod.POST)
    public Protocol log4j(String content) {

        return new Protocol();
    }
}
