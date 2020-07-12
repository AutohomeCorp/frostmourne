package com.autohome.frostmourne.monitor.service.account.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.Consts;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
import com.autohome.frostmourne.spi.starter.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FrostmourneSpiAccountService implements IAccountService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FrostmourneSpiAccountService.class);

    @Resource
    private IFrostmourneSpiApi frostmourneSpiApi;

    @Override
    public AccountInfo findByAccount(String account) {
        return frostmourneSpiApi.findByAccount(Consts.APP_ID, account).getResult();
    }

    @Override
    public List<Team> teams(String department) {
        Protocol<List<Team>> protocol = frostmourneSpiApi.teams(Consts.APP_ID, department);
        if (protocol.getReturncode() != 0) {
            LOGGER.error("error when spi teams, response: {}", JacksonUtil.serialize(protocol));
            return new ArrayList<>();
        }
        return protocol.getResult();
    }

    @Override
    public List<AccountInfo> search(String keyword) {
        Protocol<List<AccountInfo>> protocol = frostmourneSpiApi.search(Consts.APP_ID, keyword);
        if (protocol.getReturncode() != 0) {
            LOGGER.error("error when spi search keyword: {}, response: {}", keyword, JacksonUtil.serialize(protocol));
            return new ArrayList<>();
        }
        return protocol.getResult();
    }
}
