package com.autohome.frostmourne.spi.starter.api;

import java.util.List;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface IFrostmourneSpiApi {

    @RequestLine("GET /user/findByAccount?_appId={_appId}&account={account}")
    @Headers("Content-Type: application/json")
    Protocol<UserInfo> findByAccount(@Param("_appId") String _appId, @Param("account") String account);

    @RequestLine("GET /user/search?_appId={_appId}&keyword={keyword}")
    @Headers("Content-Type: application/json")
    Protocol<List<UserInfo>> search(@Param("_appId") String _appId, @Param("keyword") String keyword);

    @RequestLine("GET /org/teams?_appId={_appId}&department={department}")
    @Headers("Content-Type: application/json")
    Protocol<List<Team>> teams(@Param("_appId") String _appId, @Param("department") String department);

    @RequestLine("POST /message/send?_appId={_appId}")
    @Headers("Content-Type: application/json")
    Protocol<List<MessageResult>> send(AlarmMessage alarmMessage, @Param("_appId") String _appId);

    @RequestLine("GET /shorten/link?_appId={_appId}&longUrl={longUrl}")
    @Headers("Content-Type: application/json")
    Protocol<String> shortenLink(@Param("_appId") String _appId, @Param("longUrl") String longUrl);
}
