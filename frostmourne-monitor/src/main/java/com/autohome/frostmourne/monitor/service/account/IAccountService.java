package com.autohome.frostmourne.monitor.service.account;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.account.Team;

import java.util.List;
import java.util.Optional;


public interface IAccountService {

    Optional<AccountInfo> findByAccount(String account);

    List<Team> teams(Long departmentId);

    List<AccountInfo> search(String keyword);
}
