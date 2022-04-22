package com.autohome.frostmourne.monitor.service.account.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;
import com.autohome.frostmourne.monitor.service.account.IAuthService;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;

public class UserAuthService implements IAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthService.class);

    private final IUserInfoService userInfoService;

    private final String initialPassword;

    public UserAuthService(IUserInfoService userInfoService, String initialPassword) {
        this.userInfoService = userInfoService;
        this.initialPassword = initialPassword;
    }

    @Override
    public boolean validate(String account, String password) {
        if (Strings.isNullOrEmpty(password)) {
            return false;
        }

        UserInfo user = userInfoService.findInfoByAccount(account);
        if (user == null) {
            return false;
        }
        if (!Strings.isNullOrEmpty(user.getPassword())) {
            return user.getPassword().equals(DigestUtils.md5Hex(password));
        }

        if (!Strings.isNullOrEmpty(initialPassword)) {
            return initialPassword.equals(password);
        }

        return true;
    }
}
