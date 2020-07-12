package com.autohome.frostmourne.spi.starter.mock;

import java.util.ArrayList;
import java.util.List;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import com.autohome.frostmourne.spi.starter.model.MessageResult;
import com.autohome.frostmourne.spi.starter.model.Team;

public class MockFrostmourneSpi implements IFrostmourneSpiApi {

    @Override
    public Protocol<AccountInfo> findByAccount(String _appId, String account) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount("admin");
        accountInfo.setEmail("admin@frostmourne.com");
        accountInfo.setFullName("管理员");
        accountInfo.setMobile("150XXXX0501");
        accountInfo.setTeamId(-1L);
        accountInfo.setTeamName("炒鸡赛亚人");

        return new Protocol<>(accountInfo);
    }

    @Override
    public Protocol<List<AccountInfo>> search(String _appId, String keyword) {
        List<AccountInfo> accountInfoList = new ArrayList<>();
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount("admin");
        accountInfo.setEmail("admin@frostmourne.com");
        accountInfo.setFullName("管理员");
        accountInfo.setMobile("150XXXX0501");
        accountInfo.setTeamId(-1L);
        accountInfo.setTeamName("抄鸡赛亚人");
        accountInfoList.add(accountInfo);
        return new Protocol<>(accountInfoList);
    }

    @Override
    public Protocol<List<Team>> teams(String _appId, String department) {
        Team team1 = new Team();
        team1.setId(-1L);
        team1.setDepartment("tech");
        team1.setFullName("研发团队一");
        team1.setName("tech.team1");

        Team team2 = new Team();
        team2.setId(-2L);
        team2.setDepartment("tech");
        team2.setFullName("研发团队二");
        team2.setName("tech.team2");

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        return new Protocol<>(teams);
    }

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

    @Override
    public Protocol<String> shortenLink(String _appId, String longUrl) {
        return new Protocol<>("");
    }
}
