package com.autohome.frostmourne.monitor.service.account.impl;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.HardcodedFilter;

import com.autohome.frostmourne.monitor.service.account.IAuthService;

public class LdapAuthService implements IAuthService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LdapAuthService.class);

    private final LdapTemplate ldapTemplate;

    private final String searchFilter;

    private final String initialPassword;

    public LdapAuthService(String searchFilter, LdapTemplate ldapTemplate, String initialPassword) {
        this.searchFilter = searchFilter;
        this.ldapTemplate = ldapTemplate;
        this.initialPassword = initialPassword;
    }

    @Override
    public boolean validate(String account, String password) {
        if ("admin".equals(account)) {
            return password.equals(initialPassword);
        }
        try {
            String filterString = MessageFormat.format(searchFilter, account);
            HardcodedFilter filter = new HardcodedFilter(filterString);
            return ldapTemplate.authenticate("", filter.encode(), password);
        } catch (Exception ex) {
            LOGGER.error("error when ldap authenticate user: {}", account, ex);
            return false;
        }
    }
}
