package com.autohome.frostmourne.core.contract;

public class MailConfig {

    /**
     * smtp服务地址
     */
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

    public MailConfig(String smtpHost, String smtpPort, String smtpAuth, String tlsEnable, String sender, String senderPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpAuth = smtpAuth;
        this.tlsEnable = tlsEnable;
        this.sender = sender;
        this.senderPassword = senderPassword;
    }
}
