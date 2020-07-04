package com.autohome.frostmourne.monitor.controller;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.LoginInfo;
import com.autohome.frostmourne.monitor.controller.annotation.PermissionLimit;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import com.autohome.frostmourne.monitor.tool.JwtToken;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/user", "/api/monitor-api/user"})
public class UserController {

    @Resource
    private JwtToken jwtToken;

    @Resource
    private IFrostmourneSpiApi frostmourneSpiApi;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Protocol<UserInfo> info() {
        return new Protocol<>(AuthTool.currentUser());
    }

    @PermissionLimit(limit = false)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Protocol<String> login(@RequestBody LoginInfo loginInfo) {
        Protocol<UserInfo> protocol = frostmourneSpiApi.findByAccount("frostmourne-monitor", loginInfo.getUsername());
        String token = jwtToken.generateToken(protocol.getResult());
        return new Protocol<>(token);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Protocol logout() {
        return new Protocol();
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public Protocol<List<Team>> teams() {
        return frostmourneSpiApi.teams("frostmourne-monitor", null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Protocol<List<UserInfo>> search(@RequestParam(value = "keyword", required = false) String keyword) {
        return this.frostmourneSpiApi.search("frostmourne-monitor", keyword);
    }
}
