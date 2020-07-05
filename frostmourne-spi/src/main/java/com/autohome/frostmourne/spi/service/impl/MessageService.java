package com.autohome.frostmourne.spi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.contract.DingAt;
import com.autohome.frostmourne.spi.contract.DingMessageResponse;
import com.autohome.frostmourne.spi.contract.DingRobotMessage;
import com.autohome.frostmourne.spi.contract.DingText;
import com.autohome.frostmourne.spi.dao.IEmailSender;
import com.autohome.frostmourne.spi.dao.IWeChatSender;
import com.autohome.frostmourne.spi.plugin.IDingSenderPlugin;
import com.autohome.frostmourne.spi.plugin.ISmsSenderPlugin;
import com.autohome.frostmourne.spi.service.IMessageService;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService implements IMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Resource
    private IEmailSender emailSender;

    @Resource
    private IWeChatSender weChatSender;

    @Resource
    private IDingSenderPlugin dingSenderPlugin;

    @Resource
    private ISmsSenderPlugin smsSenderPlugin;

    @Resource
    private RestTemplate restTemplate;

    public List<MessageResult> send(AlarmMessage alarmMessage) {
        List<MessageResult> results = new ArrayList<>();
        for (String way : alarmMessage.getWays()) {
            MessageResult messageResult = new MessageResult();
            messageResult.setWay(way);
            boolean success = innerSend(way, alarmMessage);
            messageResult.setSuccess(success ? 1 : 0);
            results.add(messageResult);
        }
        return results;
    }

    private boolean innerSend(String way, AlarmMessage alarmMessage) {
        if (way.equalsIgnoreCase("email")) {
            List<String> emails = alarmMessage.getRecipients().stream()
                    .filter(m -> !Strings.isNullOrEmpty(m.getEmail()))
                    .map(UserInfo::getEmail).collect(Collectors.toList());
            return emailSender.send(alarmMessage.getTitle(), alarmMessage.getContent(), emails);
        }

        List<String> cellphoneList = alarmMessage.getRecipients().stream()
                .map(UserInfo::getMobile).collect(Collectors.toList());
        if (way.equalsIgnoreCase("dingding")) {
            if (Strings.isNullOrEmpty(alarmMessage.getDingHook())) {
                return dingSenderPlugin.send(alarmMessage.getTitle(), alarmMessage.getContent(), cellphoneList);
            } else {
                return sendByDingRobot(alarmMessage.getDingHook(), alarmMessage, cellphoneList);
            }
        }

        if (way.equalsIgnoreCase("sms")) {
            return smsSenderPlugin.send(alarmMessage.getTitle(), alarmMessage.getContent(), cellphoneList);
        }

        if (way.equalsIgnoreCase("http_post") && !Strings.isNullOrEmpty(alarmMessage.getHttpPostEndpoint())) {
            return sendHttpPost(alarmMessage.getHttpPostEndpoint(), alarmMessage);
        }

        if (way.equalsIgnoreCase("wechat")) {
            List<String> wxidList = alarmMessage.getRecipients().stream()
                    .filter(m -> !Strings.isNullOrEmpty(m.getWxid()))
                    .map(UserInfo::getWxid).collect(Collectors.toList());
            return weChatSender.send(wxidList, alarmMessage.getTitle(), alarmMessage.getContent(), alarmMessage.getWechatHook());
        }

        throw new IllegalArgumentException("unknown way: " + way);
    }

    boolean sendByDingRobot(String hook, AlarmMessage alarmMessage, List<String> recipients) {
        DingRobotMessage dingRobotMessage = new DingRobotMessage();
        DingAt dingAt = new DingAt();
        dingAt.setAtAll(false);
        dingAt.setAtMobiles(recipients);
        dingRobotMessage.setAt(dingAt);

        DingText dingText = new DingText();
        List<String> atMobiles = recipients.stream().map(m -> "@" + m).collect(Collectors.toList());
        String dingContent = null;
        dingContent = String.format("%s%n%s%n%s", alarmMessage.getTitle(), String.join(" ", atMobiles), alarmMessage.getContent());
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
            DingMessageResponse dingMessageResponse = restTemplate.postForObject(hook, request, DingMessageResponse.class);
            return dingMessageResponse != null && dingMessageResponse.getErrcode() != null && dingMessageResponse.getErrcode() == 0;
        } catch (Exception ex) {
            LOGGER.error("error when send ding robot message", ex);
            return false;
        }
    }

    boolean sendHttpPost(String httpPostEndPoint, AlarmMessage alarmMessage) {
        try {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            Map<String, Object> data = new HashMap<>();
            data.put("recipients", alarmMessage.getRecipients());
            data.put("content", alarmMessage.getContent());
            data.put("title", alarmMessage.getTitle());
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(httpPostEndPoint, request, String.class);
            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (Exception ex) {
            LOGGER.error("error when send http post, url: " + httpPostEndPoint, ex);
            return false;
        }
    }
}
