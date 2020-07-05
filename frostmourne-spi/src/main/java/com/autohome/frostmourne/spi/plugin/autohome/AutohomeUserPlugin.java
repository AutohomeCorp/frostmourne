package com.autohome.frostmourne.spi.plugin.autohome;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.plugin.IUserPlugin;
import com.autohome.frostmourne.spi.service.impl.FileUserService;
import com.autohome.frostmourne.spi.starter.model.UserInfo;

public class AutohomeUserPlugin implements IUserPlugin {

    @Resource
    private FileUserService fileUserService;

    public UserInfo findByAccount(String account) {
        return fileUserService.findByAccount(account);
    }

    @Override
    public List<UserInfo> search(String keyword) {
        return fileUserService.search(keyword);
    }
}
