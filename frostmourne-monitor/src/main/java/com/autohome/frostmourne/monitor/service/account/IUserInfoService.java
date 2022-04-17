package com.autohome.frostmourne.monitor.service.account;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.UserContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;

public interface IUserInfoService {

    boolean insert(UserContract contract);

    boolean delete(Long userId);

    boolean update(UserContract contract);

    PagerContract<UserContract> findPage(int pageIndex, int pageSize, Long id, String account, Long teamId);

    int deleteByTeam(Long teamId);

    UserContract findByAccount(String account);

    UserInfo findInfoByAccount(String account);

}
