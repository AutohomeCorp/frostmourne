package com.autohome.frostmourne.monitor.service.message.sender;

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
import java.util.Map;

/**
 * http post发送器
 *
 * @author Aping
 * @since 2022/3/27 20:29
 */
@Component
public class HttpPostSender extends MessageSenderChain {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpPostSender.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void send(AlarmMessageBO alarmMessageBO) {

        MessageResult messageResult = new MessageResult(myWay(), false);
        try {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            Map<String, Object> data = new HashMap<>();
            data.put("recipients", alarmMessageBO.getRecipients());
            data.put("content", alarmMessageBO.getContent());
            data.put("title", alarmMessageBO.getTitle());
            data.put("context", alarmMessageBO.getContext());
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
            ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(alarmMessageBO.getHttpPostEndpoint(), request, String.class);
            boolean result = responseEntity.getStatusCode() == HttpStatus.OK;

            messageResult.setSuccess(result);
        } catch (Exception ex) {
            LOGGER.error("error when send http post, url: " + alarmMessageBO.getHttpPostEndpoint(), ex);
        }
        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.HTTP_POST;
    }
}
