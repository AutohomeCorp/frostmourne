package com.autohome.frostmourne.monitor.service.message.sender;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.AlertTemplateType;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import com.autohome.frostmourne.monitor.model.message.ding.*;

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
        List<String> atMobiles = cellphoneList.stream().map(m -> "@" + m).collect(Collectors.toList());

        if (AlertTemplateType.MARKDOWN.equals(alarmMessageBO.getAlertTemplateType())) {
            dingRobotMessage.setMsgtype("markdown");
            String dingContent = String.format("%s%n%s", String.join(" ", atMobiles), alarmMessageBO.getContent());
            if (dingContent.length() > 18000) {
                dingContent = dingContent.substring(0, 18000);
            }
            DingMarkdown dingMarkdown = new DingMarkdown();
            dingMarkdown.setTitle(alarmMessageBO.getTitle());
            dingMarkdown.setText(dingContent);
            dingRobotMessage.setMarkdown(dingMarkdown);
        } else {
            dingRobotMessage.setMsgtype("text");
            String dingContent = String.format("%s%n%s%n%s", alarmMessageBO.getTitle(), String.join(" ", atMobiles),
                alarmMessageBO.getContent());
            if (dingContent.length() > 18000) {
                dingContent = dingContent.substring(0, 18000);
            }
            DingText dingText = new DingText();
            dingText.setContent(dingContent);
            dingRobotMessage.setText(dingText);
        }

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
