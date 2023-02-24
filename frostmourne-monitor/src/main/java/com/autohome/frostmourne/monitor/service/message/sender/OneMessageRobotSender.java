package com.autohome.frostmourne.monitor.service.message.sender;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;
import com.autohome.frostmourne.monitor.model.message.AlarmMessageBO;
import com.autohome.frostmourne.monitor.model.message.MessageResult;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class OneMessageRobotSender extends MessageSenderChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneMessageRobotSender.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void send(AlarmMessageBO alarmMessageBO) {
        MessageResult messageResult = new MessageResult(myWay(), false);
        try {
            //String urlEncodedMessage = URLEncoder.encode(alarmMessageBO.getContent(), StandardCharsets.UTF_8.toString());
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(alarmMessageBO.getOneMessageHook() + "{message}", String.class,
                    ImmutableMap.of("message", alarmMessageBO.getContent()));
            messageResult.setSuccess(responseEntity.getStatusCode() == HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("error when one message robot message", ex);
        }
        alarmMessageBO.getResultList().add(messageResult);
    }

    @Override
    public MessageWay myWay() {
        return MessageWay.ONE_MESSAGE_ROBOT;
    }
}
