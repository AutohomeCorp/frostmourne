package com.autohome.frostmourne.monitor.service.account.impl;

import java.text.MessageFormat;

import com.autohome.frostmourne.monitor.service.account.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.HardcodedFilter;

public class LdapAuthService implements IAuthService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LdapAuthService.class);

    private LdapTemplate ldapTemplate;

    private String searchFilter;

    private String initailPassword;

    public LdapAuthService(String searchFilter, LdapTemplate ldapTemplate, String initialPassword) {
        this.searchFilter = searchFilter;
        this.ldapTemplate = ldapTemplate;
        this.initailPassword = initialPassword;
    }

    @Override
    public boolean validate(String account, String password) {
        if (account.equals("admin")) {
            return password.equals(initailPassword);
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
