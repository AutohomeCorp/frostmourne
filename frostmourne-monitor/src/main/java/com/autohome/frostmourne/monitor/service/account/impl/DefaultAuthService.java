package com.autohome.frostmourne.monitor.service.account.impl;

import com.autohome.frostmourne.monitor.service.account.IAuthService;
import org.elasticsearch.common.Strings;

public class DefaultAuthService implements IAuthService {

    private String initialPassword;

    public DefaultAuthService(String initialPassword) {
        this.initialPassword = initialPassword;
    }

    @Override
    public boolean validate(String account, String password) {
        if (Strings.isNullOrEmpty(initialPassword)) {
            return true;
        }
        if(Strings.isNullOrEmpty(password)) {
            return false;
        }

        return account.equals(password);
    }
}
