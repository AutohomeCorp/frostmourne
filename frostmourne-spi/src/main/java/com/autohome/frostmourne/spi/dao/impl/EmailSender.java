package com.autohome.frostmourne.spi.dao.impl;

import java.util.List;

import com.autohome.frostmourne.core.EmailHelper;
import com.autohome.frostmourne.spi.dao.IEmailSender;

public class EmailSender implements IEmailSender {

    private final String smtpHost;

    private final String smtpPort;

    private final String sender;

    private final String senderPassword;

    public EmailSender(String smtpHost, String smtpPort, String sender, String senderPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.sender = sender;
        this.senderPassword = senderPassword;
    }

    public boolean send(String title, String content, List<String> recipients) {
        return EmailHelper.sendText(this.smtpHost, this.smtpPort, sender, senderPassword, recipients, title, content);
    }
}
