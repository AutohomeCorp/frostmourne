package com.autohome.frostmourne.spi.service;

import java.util.List;

import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;

public interface IMessageService {

    List<MessageResult> send(AlarmMessage alarmMessage);
}
