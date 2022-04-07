package com.autohome.frostmourne.monitor.service.message.sender;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 企业微信机器人消息发送器
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Component
public class WechatRobotSender extends MessageSenderChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatRobotSender.class);

    @Resource
    private RestTemplate restTemplate;

    /**
     * see document https://work.weixin.qq.com/help?person_id=1&doc_id=13376
     */
    @Override
    public void send(AlarmMessageBO alarmMessageBO) {
        MessageResult messageResult = new MessageResult(myWay(), false);

        List<String> wxidList = alarmMessageBO.getRecipients().stream().map(AccountInfo::getWxid)
            .filter(StringUtils::isNotBlank).collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String content = String.format("%s%n%s", alarmMessageBO.getTitle(), alarmMessageBO.getContent());
        if (content.length() > 2048) {
            content = content.substring(0, 2048);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("text", ImmutableMap.of("mentioned_list", wxidList, "content", content));
        data.put("msgtype", "text");
        // HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        String json = JacksonUtil.serialize(data);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> messageResponseEntity =
            restTemplate.postForEntity(alarmMessageBO.getWechatHook(), request, String.class);
        // response like: {"errcode":0,"errmsg":"ok"}
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when send wechat robot message, code: {} response: {}",
                messageResponseEntity.getStatusCodeValue(), messageResponseEntity.getBody());
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }
        String responseJson = messageResponseEntity.getBody();
        Map<String, Object> responseMap =
            JacksonUtil.deSerialize(responseJson, new TypeReference<Map<String, Object>>() {});

        if (responseMap.containsKey("errcode")) {
            Integer errcode = (Integer)responseMap.get("errcode");
            if (errcode != 0) {
                LOGGER.error("error when send wechat robot message, response: " + responseJson);
                alarmMessageBO.getResultList().add(messageResult);
                return;
            }
        }
        messageResult.setSuccess(true);
        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.WECHAT_ROBOT;
    }
}
