package com.autohome.frostmourne.spi.starter.api;

import java.util.List;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface IFrostmourneSpiApi {

    @RequestLine("POST /message/send?_appId={_appId}")
    @Headers("Content-Type: application/json")
    Protocol<List<MessageResult>> send(AlarmMessage alarmMessage, @Param("_appId") String _appId);
}
