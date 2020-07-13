package com.autohome.frostmourne.monitor.service.account;

import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;

public interface IUserInfoService {

    boolean insert(UserInfo userInfo, String account);

    boolean delete(Long userId);

    boolean update(UserInfo userInfo, String account);

    PagerContract<UserInfo> findPage(int pageIndex, int pageSize, Long id, String account, Long teamId);

    int deleteByTeam(Long teamId);

    Optional<UserInfo> findByAccount(String account);
}
