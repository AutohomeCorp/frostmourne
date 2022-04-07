package com.autohome.frostmourne.monitor.service.message;

import com.autohome.frostmourne.monitor.service.message.sender.MessageSenderChain;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 消息通知层
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Service
public class MessageService {

    private MessageSenderChain messageSenderChain;

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    private void buildChain() {
        Map<String, MessageSenderChain> messageSenderChainMap = applicationContext.getBeansOfType(MessageSenderChain.class);
        AtomicReference<MessageSenderChain> node = new AtomicReference<>();
        messageSenderChainMap.forEach((k, v) -> {
            if (node.get() == null) {
                node.set(v);
                this.messageSenderChain = node.get();
                return;
            }

            node.get().setNextChain(v);
            node.set(v);
        });
    }

    public void send(AlarmMessageBO alarmMessageBO) {
        messageSenderChain.chain(alarmMessageBO);
    }

}
