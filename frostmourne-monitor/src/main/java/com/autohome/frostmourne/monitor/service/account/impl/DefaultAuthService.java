package com.autohome.frostmourne.monitor.service.account.impl;

import org.elasticsearch.common.Strings;

import com.autohome.frostmourne.monitor.service.account.IAuthService;

public class DefaultAuthService implements IAuthService {

    private final String initialPassword;

    public DefaultAuthService(String initialPassword) {
        this.initialPassword = initialPassword;
    }

    @Override
    public boolean validate(String account, String password) {
        if (Strings.isNullOrEmpty(initialPassword)) {
            return true;
        }
        if (Strings.isNullOrEmpty(password)) {
            return false;
        }

        return password.equals(initialPassword);
    }
}
