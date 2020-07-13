package com.autohome.frostmourne.spi.controller;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.plugin.IUserPlugin;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
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
    public Protocol<AccountInfo> findByAccount(@RequestParam(name = "_appId", required = true) String _appId,
                                               @RequestParam(value = "account", required = true) String account) {
        AccountInfo accountInfo = userService.findByAccount(account);
        return new Protocol<>(accountInfo);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Protocol<List<AccountInfo>> search(@RequestParam(name = "_appId", required = true) String _appId,
                                              @RequestParam(name = "keyword", required = true) String keyword) {
        List<AccountInfo> accountInfos = userService.search(keyword);
        return new Protocol<>(accountInfos);
    }
}
