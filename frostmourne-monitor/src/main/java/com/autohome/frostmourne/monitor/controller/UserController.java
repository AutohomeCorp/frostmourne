package com.autohome.frostmourne.monitor.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.account.IUserInfoService;
import org.springframework.web.bind.annotation.*;

import com.autohome.frostmourne.common.contract.Protocol;
import com.autohome.frostmourne.common.contract.ProtocolException;
import com.autohome.frostmourne.monitor.controller.annotation.PermissionLimit;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.account.Team;
import com.autohome.frostmourne.monitor.model.contract.LoginInfo;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.account.IAuthService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import com.autohome.frostmourne.monitor.tool.JwtToken;

@RestController
@RequestMapping(value = {"/user", "/api/monitor-api/user"})
public class UserController {

    @Resource
    private JwtToken jwtToken;

    @Resource
    private IAccountService accountService;

    @Resource
    private IAuthService authService;

    @Resource
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Protocol<AccountInfo> info() {
        AccountInfo account = AuthTool.currentUser();
        return new Protocol<>(account);
    }

    @PermissionLimit(limit = false)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Protocol<String> login(@RequestBody LoginInfo loginInfo) {
        boolean valid = authService.validate(loginInfo.getUsername(), loginInfo.getPassword());
        if (!valid) {
            throw new ProtocolException(580, "用户名或密码错误");
        }
        Optional<AccountInfo> optionalAccountInfo = accountService.findByAccount(loginInfo.getUsername());
        if (!optionalAccountInfo.isPresent()) {
            //默认自动添加用户基本信息
            userInfoService.addByLoginInfo(loginInfo);
        }
        String token = jwtToken.generateToken(optionalAccountInfo.get());
        return new Protocol<>(token);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Protocol logout() {
        return new Protocol();
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public Protocol<List<Team>> teams() {
        List<String> roles = AuthTool.currentUser().getRoles();
        List<Team> teamList;
        if (roles != null && roles.contains("admin")) {
            teamList = accountService.teams(null);
        } else {
            teamList = accountService.teams(AuthTool.currentUser().getDepartmentId());
        }
        return new Protocol<>(teamList);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Protocol<List<AccountInfo>> search(@RequestParam(value = "keyword", required = false) String keyword) {
        List<AccountInfo> accountInfoList = accountService.search(keyword);
        return new Protocol<>(accountInfoList);
    }
}
