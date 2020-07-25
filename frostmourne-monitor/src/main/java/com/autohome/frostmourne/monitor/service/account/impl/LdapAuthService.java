package com.autohome.frostmourne.monitor.service.account.impl;

import javax.naming.directory.DirContext;

import com.autohome.frostmourne.monitor.service.account.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;

public class LdapAuthService implements IAuthService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LdapAuthService.class);

    private LdapTemplate ldapTemplate;

    private String ldapDomainName;

    private String initailPassword;

    public LdapAuthService(String ldapDomainName, LdapTemplate ldapTemplate, String initialPassword) {
        this.ldapDomainName = ldapDomainName;
        this.ldapTemplate = ldapTemplate;
        this.initailPassword = initialPassword;
    }

    @Override
    public boolean validate(String account, String password) {
        if (account.equals("admin")) {
            return password.equals(initailPassword);
        }
        String userDn = account + ldapDomainName;
        DirContext dirContext = null;
        try {
            ContextSource contextSource = ldapTemplate.getContextSource();
            dirContext = contextSource.getContext(userDn, password);
            return dirContext != null;
        } catch (Exception ex) {
            LOGGER.error("error when ldap validate user: {}", userDn, ex);
            return false;
        } finally {
            LdapUtils.closeContext(dirContext);
        }
    }
}
