package com.autohome.frostmourne.monitor.controller.account;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import javafx.scene.chart.ValueAxis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/userinfo", "/api/monitor-api/userinfo"})
public class UserInfoController {

    @Resource
    private IUserInfoService userInfoService;

    @PostMapping(value = "/create")
    public Protocol<Boolean> create(@RequestBody UserInfo userInfo) {
        return new Protocol<>(userInfoService.insert(userInfo, AuthTool.currentUser().getAccount()));
    }

    @PostMapping(value = "/update")
    public Protocol<Boolean> update(@RequestBody UserInfo userInfo) {
        return new Protocol<>(userInfoService.update(userInfo, AuthTool.currentUser().getAccount()));
    }

    @PostMapping(value = "/delete")
    public Protocol<Boolean> delete(@RequestParam(value = "id", required = true) Long id) {
        return new Protocol<>(userInfoService.delete(id));
    }

    @GetMapping(value = "/findPage")
    public Protocol<PagerContract<UserInfo>> findPage(@RequestParam(value = "pageIndex", required = true) int pageIndex,
                                                      @RequestParam(value = "pageSize", required = true) int pageSize,
                                                      @RequestParam(value = "id", required = false) Long id,
                                                      @RequestParam(value = "account", required = false) String account,
                                                      @RequestParam(value = "teamId", required = false) Long teamId) {
        return new Protocol<>(userInfoService.findPage(pageIndex, pageSize, id, account, teamId));
    }
}
