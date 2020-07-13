package com.autohome.frostmourne.spi.plugin.defaultimpl;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.plugin.IUserPlugin;
import com.autohome.frostmourne.spi.service.impl.FileUserService;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;

public class DefaultUserPlugin implements IUserPlugin {

    @Resource
    private FileUserService fileUserService;

    public AccountInfo findByAccount(String account) {
        return fileUserService.findByAccount(account);
    }

    @Override
    public List<AccountInfo> search(String keyword) {
        return fileUserService.search(keyword);
    }
}
