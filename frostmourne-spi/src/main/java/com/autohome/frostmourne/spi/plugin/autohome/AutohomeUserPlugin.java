package com.autohome.frostmourne.spi.plugin.autohome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.spi.plugin.IUserPlugin;
import com.autohome.frostmourne.spi.starter.model.UserInfo;

public class AutohomeUserPlugin implements IUserPlugin {

    @Resource
    private Map<String, UserInfo> userInfoMap;

    public UserInfo findByAccount(String account) {
        return userInfoMap.get(account);
    }

    @Override
    public List<UserInfo> search(String keyword) {
        List<UserInfo> userInfos = new ArrayList<>();
        for (Map.Entry<String, UserInfo> entry : userInfoMap.entrySet()) {
            if (entry.getKey().startsWith(keyword)) {
                userInfos.add(entry.getValue());
            }
        }
        return userInfos;
    }
}
