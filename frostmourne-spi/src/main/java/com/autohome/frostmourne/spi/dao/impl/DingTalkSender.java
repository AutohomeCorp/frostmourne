package com.autohome.frostmourne.spi.dao.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DingTalkSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(DingTalkSender.class);

    private String appkey;

    private String appsecret;

    private String tokenUrl;

    private String token;

    private long expiresTime = 0;

    private int defaultExpire = 7200; //seconds

    @Resource
    private RestTemplate restTemplate;

    public DingTalkSender(String appkey, String appsecret) {
        this.appkey = appkey;
        this.appsecret = appsecret;

        if (!Strings.isNullOrEmpty(appkey) && !Strings.isNullOrEmpty(appsecret)) {
            this.tokenUrl = String.format("https://oapi.dingtalk.com/gettoken?appkey=%s&appsecret=%s", appkey, appsecret);
        }
    }

    public boolean send(List<String> users, String content) {
        if (Strings.isNullOrEmpty(this.appkey)) {
            LOGGER.error("appkey could not be null when send by dingtalk");
            return false;
        }

        if (Strings.isNullOrEmpty(this.appsecret)) {
            LOGGER.error("appsecret could not be null when send by dingtalk");
            return false;
        }

        return true;
    }

    private String token() {
        if (!Strings.isNullOrEmpty(token) && expiresTime > System.currentTimeMillis()) {
            return this.token;
        }
        //https://ding-doc.dingtalk.com/doc#/serverapi2/eev437
        ResponseEntity<String> tokenResponseEntity = restTemplate.getForEntity(this.tokenUrl, String.class);
        if (tokenResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when get dingtalk token, response: " + tokenResponseEntity.getBody());
            return null;
        }
        String tokenJson = tokenResponseEntity.getBody();
        Map<String, Object> tokenMap = JacksonUtil.deSerialize(tokenJson, new TypeReference<Map<String, Object>>() {
        });
        if (tokenMap.containsKey("errcode")) {
            Integer errcode = (Integer) tokenMap.get("errcode");
            if (errcode != 0) {
                LOGGER.error("error when get wechat token, response: " + tokenJson);
                return null;
            }
        }
        this.token = tokenMap.get("access_token").toString();
        this.expiresTime = System.currentTimeMillis() + (defaultExpire - 300) * 1000;
        return this.token;
    }
}
