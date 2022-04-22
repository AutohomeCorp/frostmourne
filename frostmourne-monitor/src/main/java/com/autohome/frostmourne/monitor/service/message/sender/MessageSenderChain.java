package com.autohome.frostmourne.monitor.service.message.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;

/**
 * 消息通知责任链
 *
 * @author Aping
 * @since 2022/3/27 20:11
 */
public abstract class MessageSenderChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderChain.class);

    private MessageSenderChain nextChain;

    public void chain(AlarmMessageBO alarmMessageBO) {
        if (alarmMessageBO.getWays().contains(myWay())) {
            try {
                send(alarmMessageBO);
            } catch (Exception e) {
                LOGGER.error("catch exception when send {} alarm message", myWay(), e);
            }
        }

        if (nextChain != null) {
            nextChain.chain(alarmMessageBO);
        }
    }

    public abstract void send(AlarmMessageBO alarmMessageBO);

    public abstract MessageWay myWay();

    public void setNextChain(MessageSenderChain nextChain) {
        this.nextChain = nextChain;
    }
}
