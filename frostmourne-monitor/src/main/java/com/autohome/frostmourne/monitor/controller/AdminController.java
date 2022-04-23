package com.autohome.frostmourne.monitor.controller;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Alarm;
import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.tool.AuthTool;

@RestController
@RequestMapping(value = {"/admin", "/api/monitor-api/admin"})
public class AdminController {

    @Resource
    private IAlarmAdminService alarmAdminService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Protocol<Boolean> save(@RequestBody AlarmContract alarmContract) {
        alarmContract.setOperator(AuthTool.currentUser().getAccount());
        boolean result = alarmAdminService.atomicSave(alarmContract);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/saveAnother", method = RequestMethod.POST)
    public Protocol<Boolean> saveAnother(@RequestBody AlarmContract alarmContract) {
        alarmContract.setOperator(AuthTool.currentUser().getAccount());
        alarmContract.setId(null);
        boolean result = alarmAdminService.atomicSave(alarmContract);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Protocol<Boolean> delete(@RequestParam(value = "alarmId") Long alarmId) {
        boolean result = alarmAdminService.delete(alarmId);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/open", method = RequestMethod.POST)
    public Protocol<Boolean> open(@RequestParam(value = "alarmId") Long alarmId) {
        boolean result = alarmAdminService.open(alarmId);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public Protocol<Boolean> close(@RequestParam(value = "alarmId") Long alarmId) {
        boolean result = alarmAdminService.close(alarmId);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Protocol<AlarmContract> findById(@RequestParam(value = "alarmId") Long alarmId) {
        AlarmContract alarmContract = this.alarmAdminService.findById(alarmId);
        if (alarmContract == null) {
            return new Protocol<>(404, "监控不存在");
        }
        return new Protocol<>(alarmContract);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Protocol<PagerContract<Alarm>> list(int pageIndex, int pageSize, Long alarmId, String name, String teamName, String status, Long serviceId) {
        PagerContract<Alarm> pagerContract = this.alarmAdminService.find(pageIndex, pageSize, alarmId, name, teamName, status, serviceId);
        return new Protocol<>(pagerContract);
    }
}
