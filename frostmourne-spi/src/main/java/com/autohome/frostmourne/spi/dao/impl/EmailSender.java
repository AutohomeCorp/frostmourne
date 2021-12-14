package com.autohome.frostmourne.spi.dao.impl;

import java.util.List;

import com.autohome.frostmourne.core.EmailHelper;
import com.autohome.frostmourne.core.contract.MailConfig;
import com.autohome.frostmourne.spi.dao.IEmailSender;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSender implements IEmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);

    private MailConfig mailConfig;

    public EmailSender(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    public boolean send(String title, String content, List<String> recipients) {
        if (Strings.isNullOrEmpty(this.mailConfig.getSmtpHost()) || Strings.isNullOrEmpty(this.mailConfig.getSmtpPort())
                || Strings.isNullOrEmpty(this.mailConfig.getSender()) || Strings.isNullOrEmpty(this.mailConfig.getSenderPassword())) {
            LOGGER.error("email sender could not be null");
            return false;
        }
        return EmailHelper.sendText(mailConfig.getSmtpHost(), mailConfig.getSmtpPort(), mailConfig.getSmtpAuth(), mailConfig.getSender(), mailConfig.getSenderPassword(), recipients, title, content);
    }
}
