package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.UserContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserRole;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.UserRoleDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.UserRoleDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IUserRoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepository implements IUserRoleRepository {

    @Resource
    private UserRoleDynamicMapper userRoleDynamicMapper;

    @Override
    public int insertMultiple(List<UserRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return 0;
        }
        return roles.stream().mapToInt(r -> userRoleDynamicMapper.insert(r)).sum();
    }

    @Override
    public int deleteByAccount(String account) {
        return userRoleDynamicMapper.delete(
                query -> query.where(UserRoleDynamicSqlSupport.account, isEqualTo(account)));
    }

    @Override
    public void modifyByContract(UserContract contract) {
        deleteByAccount(contract.getAccount());
        if (contract.getRoles() == null || contract.getRoles().isEmpty()) {
            return;
        }

        List<UserRole> roles = contract.getRoles().stream().map(role -> {
            UserRole userRole = new UserRole();
            userRole.setAccount(contract.getAccount());
            userRole.setRole(role);
            userRole.setCreator(contract.getModifier());
            userRole.setCreate_at(new Date());
            return userRole;
        }).collect(Collectors.toList());
        insertMultiple(roles);
    }

    @Override
    public List<String> findByAccount(String account) {
        List<UserRole> list = userRoleDynamicMapper.select(
                query -> query.where().and(UserRoleDynamicSqlSupport.account, isEqualTo(account)));
        if (list == null || list.isEmpty()) {
            return Collections.singletonList("user");
        }
        return list.stream().map(UserRole::getRole).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<String>> findByAccountList(List<String> accountList) {
        List<UserRole> list = userRoleDynamicMapper.select(
                query -> query.where().and(UserRoleDynamicSqlSupport.account, isIn(accountList)));
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        return list.stream().collect(Collectors.groupingBy(
                UserRole::getAccount,
                HashMap::new,
                Collectors.mapping(UserRole::getRole, Collectors.toList())));
    }

}
