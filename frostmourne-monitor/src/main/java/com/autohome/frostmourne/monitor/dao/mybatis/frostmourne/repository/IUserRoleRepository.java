package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.UserContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserRole;

public interface IUserRoleRepository {

    int insertMultiple(List<UserRole> roles);

    int deleteByAccount(String account);

    void modifyByContract(UserContract contract);

    List<String> findByAccount(String account);

    Map<String, List<String>> findByAccountList(List<String> accountList);
}
