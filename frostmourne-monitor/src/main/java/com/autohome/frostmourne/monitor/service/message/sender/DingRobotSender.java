package com.autohome.frostmourne.monitor.service.message.sender;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.message.*;
import com.autohome.frostmourne.monitor.model.message.ding.DingAt;
import com.autohome.frostmourne.monitor.model.message.ding.DingMessageResponse;
import com.autohome.frostmourne.monitor.model.message.ding.DingRobotMessage;
import com.autohome.frostmourne.monitor.model.message.ding.DingText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 钉钉机器人消息发送器
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Component
public class DingRobotSender extends MessageSenderChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(DingRobotSender.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void send(AlarmMessageBO alarmMessageBO) {

        MessageResult messageResult = new MessageResult(myWay(), false);

        DingRobotMessage dingRobotMessage = new DingRobotMessage();
        DingAt dingAt = new DingAt();
        dingAt.setAtAll(false);
        List<String> cellphoneList =
            alarmMessageBO.getRecipients().stream().map(AccountInfo::getMobile).collect(Collectors.toList());
        dingAt.setAtMobiles(cellphoneList);
        dingRobotMessage.setAt(dingAt);

        DingText dingText = new DingText();
        List<String> atMobiles = cellphoneList.stream().map(m -> "@" + m).collect(Collectors.toList());
        String dingContent = null;
        dingContent = String.format("%s%n%s%n%s", alarmMessageBO.getTitle(), String.join(" ", atMobiles), alarmMessageBO.getContent());
        if (dingContent.length() > 18000) {
            dingContent = dingContent.substring(0, 18000);
        }
        dingText.setContent(dingContent);
        dingRobotMessage.setMsgtype("text");
        dingRobotMessage.setText(dingText);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<DingRobotMessage> request = new HttpEntity<>(dingRobotMessage, headers);
        try {
            DingMessageResponse dingMessageResponse =
                restTemplate.postForObject(alarmMessageBO.getDingHook(), request, DingMessageResponse.class);
            boolean result = dingMessageResponse != null && dingMessageResponse.getErrcode() != null
                && dingMessageResponse.getErrcode() == 0;
            if (!result) {
                LOGGER.error("error when send ding robot message, response: {}",
                    JacksonUtil.serialize(dingMessageResponse));
            }
            messageResult.setSuccess(result);
        } catch (Exception ex) {
            LOGGER.error("error when send ding robot message", ex);
        }

        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.DING_ROBOT;
    }
}
