package com.autohome.frostmourne.monitor.service.message.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;

/**
 * 短信消息发送器
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Component
public class SmsSender extends MessageSenderChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    @Override
    public void send(AlarmMessageBO alarmMessageBO) {

        MessageResult messageResult = new MessageResult(myWay(), false);
        // TODO

        messageResult.setSuccess(true);
        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.SMS;
    }

}
