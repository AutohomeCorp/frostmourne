package com.autohome.frostmourne.monitor.service.account;

import java.util.List;

import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;

public interface IAccountService {

    AccountInfo findByAccount(String account);

    List<Team> teams(String department);

    List<AccountInfo> search(String keyword);
}
