package com.autohome.frostmourne.spi.controller;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.service.IMessageService;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Resource
    private IMessageService messageService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Protocol<List<MessageResult>> send(@RequestParam(name = "_appId", required = true) String _appId,
                                              @RequestBody AlarmMessage alarmMessage) {
        if (alarmMessage.getRecipients() == null || alarmMessage.getRecipients().size() == 0) {
            return new Protocol(502, "recipients could not be null or empty when send message");
        }
        List<MessageResult> messageResultList = messageService.send(alarmMessage);
        return new Protocol<>(messageResultList);
    }
}
