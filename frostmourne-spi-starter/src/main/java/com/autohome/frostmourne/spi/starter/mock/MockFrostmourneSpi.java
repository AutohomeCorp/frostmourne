package com.autohome.frostmourne.spi.starter.mock;

import java.util.ArrayList;
import java.util.List;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;

public class MockFrostmourneSpi implements IFrostmourneSpiApi {

    @Override
    public Protocol<List<MessageResult>> send(AlarmMessage alarmMessage, String _appId) {
        List<MessageResult> messageResults = new ArrayList<>();
        for (String way : alarmMessage.getWays()) {
            MessageResult messageResult = new MessageResult();
            messageResult.setWay(way);
            messageResult.setSuccess(1);
            messageResults.add(messageResult);
        }
        return new Protocol<>(messageResults);
    }
}
