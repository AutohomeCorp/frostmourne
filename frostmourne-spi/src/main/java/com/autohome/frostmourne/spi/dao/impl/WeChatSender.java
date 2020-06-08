package com.autohome.frostmourne.spi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.dao.IWeChatSender;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeChatSender implements IWeChatSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(WeChatSender.class);

    /**
     * 企业Id
     */
    private String corpId;

    /**
     * 应用Id
     */
    private String agentId;

    /**
     * 应用密钥
     */
    private String secret;

    private String tokenUrl;

    @Resource
    private RestTemplate restTemplate;

    public WeChatSender(String corpId, String agentId, String secret) {
        this.corpId = corpId;
        this.agentId = agentId;
        this.secret = secret;

        if (!Strings.isNullOrEmpty(this.corpId) && !Strings.isNullOrEmpty(this.secret)) {
            this.tokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + this.corpId + "&corpsecret=" + this.secret;
        }
    }

    public boolean send(List<String> users, String content) {
        if (Strings.isNullOrEmpty(this.corpId)) {
            LOGGER.error("corpId could not be null when send by wechat");
            return false;
        }

        if (Strings.isNullOrEmpty(this.agentId)) {
            LOGGER.error("agentId could not be null when send by wechat");
            return false;
        }

        if (Strings.isNullOrEmpty(this.secret)) {
            LOGGER.error("secret could not be null when send by wechat");
            return false;
        }

        if (users.size() == 0) {
            LOGGER.error("user could not be empty when send wechat message");
            return false;
        }

        ResponseEntity<String> tokenResponseEntity = restTemplate.getForEntity(this.tokenUrl, String.class);
        if (tokenResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when get wechat token, response: " + tokenResponseEntity.getBody());
            return false;
        }
        String tokenJson = tokenResponseEntity.getBody();
        Map<String, Object> tokenMap = JacksonUtil.deSerialize(tokenJson, new TypeReference<Map<String, Object>>() {
        });
        String token = tokenMap.get("access_token").toString();
        String messageUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        Map<String, Object> data = new HashMap<>();
        data.put("touser", String.join("|", users));
        data.put("msgtype", "text");
        data.put("agentid", this.agentId);
        data.put("safe", 0);
        if (content.length() > 2048) {
            content = content.substring(0, 2048);
        }
        data.put("text", ImmutableMap.of("content", content));
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(messageUrl, request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when send wechat message, response: " + messageResponseEntity.getBody());
            return false;
        }
        return true;
    }
}
