package com.autohome.frostmourne.monitor.controller;

import java.util.Date;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import com.autohome.frostmourne.monitor.service.admin.ILogService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/log", "/api/monitor-api/log"})
public class LogController {

    @Resource
    private ILogService logService;

    @RequestMapping(value = "/findAlarmLog", method = RequestMethod.GET)
    public Protocol<PagerContract<AlarmLog>> findAlarmLog(@RequestParam(value = "_appId", required = true) String _appId,
                                                          @RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                          @RequestParam(value = "pageSize", required = true) int pageSize,
                                                          @RequestParam(value = "startTime", required = false) Date startTime,
                                                          @RequestParam(value = "endTime", required = false) Date endTime,
                                                          @RequestParam(value = "alarmId", required = false) Long alarmId,
                                                          @RequestParam(value = "verifyResult", required = false) String verifyResult,
                                                          @RequestParam(value = "executeResult", required = false) String executeResult) {
        PagerContract<AlarmLog> pagerContract = logService.findAlarmLog(pageIndex, pageSize, startTime, endTime, alarmId, verifyResult, executeResult);
        return new Protocol<>(pagerContract);
    }

    @RequestMapping(value = "/findAlertLog", method = RequestMethod.GET)
    public Protocol<PagerContract<AlertLog>> findAlertLog(@RequestParam(value = "_appId", required = true) String _appId,
                                                          @RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                          @RequestParam(value = "pageSize", required = true) int pageSize,
                                                          @RequestParam(value = "startTime", required = true) Date startTime,
                                                          @RequestParam(value = "endTime", required = true) Date endTime,
                                                          @RequestParam(value = "executeId", required = false) Long executeId,
                                                          @RequestParam(value = "alarmId", required = false) Long alarmId,
                                                          @RequestParam(value = "way", required = false) String way,
                                                          @RequestParam(value = "sendStatus", required = false) String sendStatus,
                                                          @RequestParam(value = "inSilence", required = false) String inSilence,
                                                          @RequestParam(value = "alertType", required = false) String alertType) {
        String account = AuthTool.currentUser().getAccount();
        PagerContract<AlertLog> pagerContract = logService.findAlertLog(pageIndex, pageSize, startTime, endTime,
                executeId, alarmId, account, way, sendStatus, inSilence, alertType);
        return new Protocol<>(pagerContract);
    }
}
