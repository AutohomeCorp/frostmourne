package com.autohome.frostmourne.monitor.service.account;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;

public interface IAccountService {

    Optional<AccountInfo> findByAccount(String account);

    List<Team> teams(Long departmentId);

    List<AccountInfo> search(String keyword);
}
