package com.autohome.frostmourne.monitor.controller;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import com.autohome.frostmourne.monitor.service.admin.IAlarmAdminService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/admin", "/api/monitor-api/admin"})
public class AdminController {

    @Resource
    private IAlarmAdminService alarmAdminService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Protocol<Boolean> save(@RequestParam(value = "_appId", required = true) String _appId,
                                  @RequestBody AlarmContract alarmContract) {
        alarmContract.setOperator(AuthTool.currentUser().getAccount());
        alarmContract.setTeam_name(AuthTool.currentUser().getTeamName());
        boolean result = alarmAdminService.atomicSave(alarmContract);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/saveAnother", method = RequestMethod.POST)
    public Protocol<Boolean> saveAnother(@RequestParam(value = "_appId", required = true) String _appId,
                                         @RequestBody AlarmContract alarmContract) {
        alarmContract.setOperator(AuthTool.currentUser().getAccount());
        alarmContract.setTeam_name(AuthTool.currentUser().getTeamName());
        alarmContract.setId(null);
        boolean result = alarmAdminService.atomicSave(alarmContract);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Protocol<Boolean> delete(@RequestParam(value = "_appId", required = true) String _appId,
                                    @RequestParam(value = "alarmId", required = true) Long alarmId) {
        boolean result = alarmAdminService.delete(alarmId);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/open", method = RequestMethod.POST)
    public Protocol<Boolean> open(@RequestParam(value = "_appId", required = true) String _appId,
                                  @RequestParam(value = "alarmId", required = true) Long alarmId) {
        boolean result = alarmAdminService.open(alarmId);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public Protocol<Boolean> close(@RequestParam(value = "_appId", required = true) String _appId,
                                   @RequestParam(value = "alarmId", required = true) Long alarmId) {
        boolean result = alarmAdminService.close(alarmId);
        return new Protocol<>(result);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Protocol<AlarmContract> findById(@RequestParam(value = "_appId", required = true) String _appId,
                                            @RequestParam(value = "alarmId", required = true) Long alarmId) {
        AlarmContract alarmContract = this.alarmAdminService.findById(alarmId);
        if (alarmContract == null) {
            return new Protocol<>(404, "监控不存在");
        }
        return new Protocol<>(alarmContract);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Protocol<PagerContract<Alarm>> list(String _appId, int pageIndex, int pageSize,
                                               Long alarmId, String name, String teamName, String status) {
        PagerContract<Alarm> pagerContract = this.alarmAdminService.find(pageIndex, pageSize, alarmId, name, teamName, status);
        return new Protocol<>(pagerContract);
    }
}
