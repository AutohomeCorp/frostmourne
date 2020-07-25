package com.autohome.frostmourne.monitor.service.account;

public interface IAuthService {

    boolean validate(String account, String password);
}
