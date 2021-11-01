package com.autohome.frostmourne.spi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.dao.IFeiShuSender;
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

public class FeiShuSender implements IFeiShuSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeiShuSender.class);

    private RestTemplate restTemplate;

    public FeiShuSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean send(List<String> users, String title, String content, String webHook) {

        if (Strings.isNullOrEmpty(webHook)) {
            LOGGER.error("webhook can not be null when send feishu message");
            return false;
        }

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        content = String.format("%s%n%s", title, content);
        Map<String, Object> data = new HashMap<>();
        data.put("msg_type", "text");
        data.put("content", ImmutableMap.of("text", content));
        String json = JacksonUtil.serialize(data);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(webHook, request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when send feishu robot message, code: {} response: {}", messageResponseEntity.getStatusCodeValue(), messageResponseEntity.getBody());
            return false;
        }
        String responseJson = messageResponseEntity.getBody();
        Map<String, Object> responseMap = JacksonUtil.deSerialize(responseJson, new TypeReference<Map<String, Object>>() {
        });
        if (responseMap.containsKey("code")) {
            Integer errcode = (Integer) responseMap.get("code");
            if (errcode != 0) {
                LOGGER.error("error when send feishu robot message, response: {}", responseJson);
                return false;
            }
        }
        return true;
    }
}
