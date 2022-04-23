package com.autohome.frostmourne.monitor.message;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.service.message.MessageService;

/**
 * 消息测试
 *
 * @author Aping
 * @since 2022/3/29 19:56
 */
@SpringBootTest
public class MessageServiceTest {

    @Resource
    private MessageService messageService;

    @Test
    public void send() {

        AlarmMessageBO alarmMessageBO = new AlarmMessageBO();
        alarmMessageBO.setContent(String.format("消息类型: [问题] %s分钟内连续报警将不重复发送\n%s", 1L, "test"));
        alarmMessageBO.setTitle("title");
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setEmail("your@qq.cn");
        List<AccountInfo> accountInfos = new ArrayList<>();
        accountInfos.add(accountInfo);
        alarmMessageBO.setRecipients(accountInfos);
        List<MessageWay> messageWays = new ArrayList<>();
        messageWays.add(MessageWay.WECHAT_ROBOT);
        messageWays.add(MessageWay.EMAIL);
        alarmMessageBO.setWays(messageWays);
        alarmMessageBO.setWechatHook("your wechat hook url");

        messageService.send(alarmMessageBO);

    }

}
