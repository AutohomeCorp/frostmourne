package com.autohome.frostmourne.monitor.service.message.sender;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;

/**
 * 飞书消息发送器
 *
 * @author Aping
 * @since 2022/4/06 23:06
 */
@Component
public class FeishuRobotSender extends MessageSenderChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeishuRobotSender.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void send(AlarmMessageBO alarmMessageBO) {

        MessageResult messageResult = new MessageResult(myWay(), false);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String content = String.format("%s%n%s", alarmMessageBO.getTitle(), alarmMessageBO.getContent());
        Map<String, Object> data = new HashMap<>();
        data.put("msg_type", "text");
        data.put("content", ImmutableMap.of("text", content));
        String json = JacksonUtil.serialize(data);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(alarmMessageBO.getFeiShuHook(), request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when send feishu robot message, code: {} response: {}", messageResponseEntity.getStatusCodeValue(),
                messageResponseEntity.getBody());
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }
        String responseJson = messageResponseEntity.getBody();
        Map<String, Object> responseMap = JacksonUtil.deSerialize(responseJson, new TypeReference<Map<String, Object>>() {});
        if (responseMap.containsKey("code")) {
            if ((Integer)responseMap.get("code") != 0) {
                LOGGER.error("error when send feishu robot message, response: {}", responseJson);
                alarmMessageBO.getResultList().add(messageResult);
                return;
            }
        }
        messageResult.setSuccess(true);
        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.FEI_SHU_ROBOT;
    }

}
