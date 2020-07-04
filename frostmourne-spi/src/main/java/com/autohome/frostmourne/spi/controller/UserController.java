package com.autohome.frostmourne.spi.controller;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.plugin.IUserPlugin;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private IUserPlugin userService;

    @RequestMapping(value = "/findByAccount", method = RequestMethod.GET)
    public Protocol<UserInfo> findByAccount(@RequestParam(name = "_appId", required = true) String _appId,
                                            @RequestParam(value = "account", required = true) String account) {
        UserInfo userInfo = userService.findByAccount(account);
        return new Protocol<>(userInfo);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Protocol<List<UserInfo>> search(@RequestParam(name = "_appId", required = true) String _appId,
                                           @RequestParam(name = "keyword", required = true) String keyword) {
        List<UserInfo> userInfos = userService.search(keyword);
        return new Protocol<>(userInfos);
    }
}
