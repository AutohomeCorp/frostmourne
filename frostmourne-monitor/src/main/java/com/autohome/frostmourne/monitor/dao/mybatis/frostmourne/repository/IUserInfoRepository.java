package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;

public interface IUserInfoRepository {

    boolean insert(UserInfo userInfo);

    boolean delete(Long userId);

    boolean update(UserInfo userInfo);

    PagerContract<UserInfo> findPage(int pageIndex, int pageSize, Long id, String account, Long teamId);

    int deleteByTeam(Long teamId);

    Optional<UserInfo> findByAccount(String account);
}
