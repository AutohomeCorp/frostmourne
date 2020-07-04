package com.autohome.frostmourne.spi.dao.impl;

import java.util.List;

import com.autohome.frostmourne.core.EmailHelper;
import com.autohome.frostmourne.spi.dao.IEmailSender;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender implements IEmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);

    private final String smtpHost;

    private final String smtpPort;

    private final String smtpAuth;

    private final String sender;

    private final String senderPassword;

    public EmailSender(String smtpHost, String smtpPort, String smtpAuth, String sender, String senderPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.sender = sender;
        this.senderPassword = senderPassword;
        this.smtpAuth = smtpAuth;
    }

    public boolean send(String title, String content, List<String> recipients) {
        if (Strings.isNullOrEmpty(this.smtpHost) || Strings.isNullOrEmpty(this.smtpPort) || Strings.isNullOrEmpty(this.sender) || Strings.isNullOrEmpty(this.senderPassword)) {
            LOGGER.error("email sender could not be null");
            return false;
        }
        return EmailHelper.sendText(this.smtpHost, this.smtpPort, this.smtpAuth, sender, senderPassword, recipients, title, content);
    }
}
