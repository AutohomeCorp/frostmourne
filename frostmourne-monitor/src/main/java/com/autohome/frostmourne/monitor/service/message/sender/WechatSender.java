package com.autohome.frostmourne.monitor.service.message.sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.config.properties.WechatProperties;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.AlertTemplateType;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

/**
 * 企业微信消息发送器
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Component
public class WechatSender extends MessageSenderChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatSender.class);

    private String tokenUrl;

    private String token;

    private long expiresTime = 0;

    @Resource
    private WechatProperties wechatProperties;

    @Resource
    private RestTemplate restTemplate;

    /**
     * see document https://work.weixin.qq.com/api/doc/90000/90135/90236
     */
    @Override
    public void send(AlarmMessageBO alarmMessageBO) {

        MessageResult messageResult = new MessageResult(myWay(), false);
        if (StringUtils.isAnyBlank(wechatProperties.getCorpId(), wechatProperties.getAgentId(), wechatProperties.getSecret())) {
            LOGGER.error("corpId, agentId or secret could not be null when send by wechat");
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }

        if (StringUtils.isBlank(tokenUrl)) {
            tokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + wechatProperties.getCorpId() + "&corpsecret=" + wechatProperties.getSecret();
        }

        List<String> wxidList = alarmMessageBO.getRecipients().stream().map(AccountInfo::getWxid).filter(StringUtils::isNotBlank).collect(Collectors.toList());

        if (wxidList.isEmpty()) {
            LOGGER.error("user could not be empty when send wechat message");
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }

        String token = token();
        if (Strings.isNullOrEmpty(token)) {
            LOGGER.error("could not get token when send wechat message");
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }

        String messageUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        Map<String, Object> data = new HashMap<>(5);
        data.put("touser", String.join("|", wxidList));
        if (AlertTemplateType.MARKDOWN.equals(alarmMessageBO.getAlertTemplateType())) {
            String content = alarmMessageBO.getContent();
            if (content.length() > 2048) {
                content = content.substring(0, 2048);
            }
            data.put("msgtype", "markdown");
            data.put("markdown", ImmutableMap.of("content", content));
        } else {
            String content = String.format("%s%n%s", alarmMessageBO.getTitle(), alarmMessageBO.getContent());
            if (content.length() > 2048) {
                content = content.substring(0, 2048);
            }
            data.put("msgtype", "text");
            data.put("text", ImmutableMap.of("content", content));
        }
        data.put("agentid", wechatProperties.getAgentId());
        data.put("safe", 0);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(messageUrl, request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when send wechat message, response: " + messageResponseEntity.getBody());
            alarmMessageBO.getResultList().add(messageResult);
            return;
        }

        messageResult.setSuccess(true);
        alarmMessageBO.getResultList().add(messageResult);
    }

    /**
     * see document https://qydev.weixin.qq.com/wiki/index.php?title=%E4%B8%BB%E5%8A%A8%E8%B0%83%E7%94%A8
     */
    private String token() {
        if (System.currentTimeMillis() < expiresTime && !Strings.isNullOrEmpty(token)) {
            return token;
        }

        ResponseEntity<String> tokenResponseEntity = restTemplate.getForEntity(this.tokenUrl, String.class);
        if (tokenResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when get wechat token, response: " + tokenResponseEntity.getBody());
            return null;
        }
        String tokenJson = tokenResponseEntity.getBody();
        Map<String, Object> tokenMap = JacksonUtil.deSerialize(tokenJson, new TypeReference<Map<String, Object>>() {});
        if (tokenMap.containsKey("errcode")) {
            Integer errcode = (Integer)tokenMap.get("errcode");
            if (errcode != 0) {
                LOGGER.error("error when get wechat token, response: " + tokenJson);
                return null;
            }
        }
        String newToken = tokenMap.get("access_token").toString();
        int expiresIn = (Integer)tokenMap.get("expires_in");
        this.token = newToken;
        this.expiresTime = System.currentTimeMillis() + (expiresIn - 300) * 1000L;
        return token;
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.WECHAT;
    }
}
