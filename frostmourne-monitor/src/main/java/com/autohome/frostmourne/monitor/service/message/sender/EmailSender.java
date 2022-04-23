package com.autohome.frostmourne.monitor.service.message.sender;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.autohome.frostmourne.core.EmailHelper;
import com.autohome.frostmourne.monitor.config.properties.MailConfigProperties;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;

/**
 * 邮件消息发送器
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Component
public class EmailSender extends MessageSenderChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);

    @Resource
    private MailConfigProperties mailConfig;

    @Override
    public void send(AlarmMessageBO alarmMessageBO) {

        MessageResult messageResult = new MessageResult(myWay(), false);

        if (StringUtils.isAnyBlank(mailConfig.getSmtpHost(), mailConfig.getSmtpPort(), mailConfig.getSender(), mailConfig.getSenderPassword())) {
            LOGGER.error("email config could not be null");
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }

        List<String> emails = alarmMessageBO.getRecipients().stream().map(AccountInfo::getEmail).filter(StringUtils::isNotBlank).collect(Collectors.toList());

        boolean result = EmailHelper.sendText(mailConfig.getSmtpHost(), mailConfig.getSmtpPort(), mailConfig.getSmtpAuth(), mailConfig.getSender(),
            mailConfig.getSenderPassword(), emails, alarmMessageBO.getTitle(), alarmMessageBO.getContent());

        messageResult.setSuccess(result);
        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.EMAIL;
    }

}
