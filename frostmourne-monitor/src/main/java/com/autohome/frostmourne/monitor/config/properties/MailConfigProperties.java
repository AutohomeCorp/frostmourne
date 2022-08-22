package com.autohome.frostmourne.monitor.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件配置类
 *
 * @author Aping
 * @since 2022/3/28 12:52
 */
@Component
@ConfigurationProperties(prefix = "email")
public class MailConfigProperties {

    private String smtpHost;

    private String smtpPort;

    private String smtpAuth;

    private String sender;

    private String senderPassword;

    private String tlsEnable;

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(String smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    public String getTlsEnable() {
        return tlsEnable;
    }

    public void setTlsEnable(String tlsEnable) {
        this.tlsEnable = tlsEnable;
    }
}
