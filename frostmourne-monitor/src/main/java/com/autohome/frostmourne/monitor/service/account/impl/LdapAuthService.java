package com.autohome.frostmourne.monitor.service.account.impl;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import javax.naming.directory.DirContext;

import com.autohome.frostmourne.monitor.service.account.IAuthService;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;
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
            dirContext = ldapTemplate.getContextSource().getContext(userDn, password);
            return dirContext != null;
        } catch (Exception ex) {
            LOGGER.error("error when ldap validate user", ex);
            return false;
        } finally {
            LdapUtils.closeContext(dirContext);
        }
    }
}
