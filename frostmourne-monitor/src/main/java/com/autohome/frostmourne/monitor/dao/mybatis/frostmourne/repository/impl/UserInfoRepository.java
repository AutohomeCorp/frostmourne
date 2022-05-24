package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.UserInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.UserInfoDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.UserInfoDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IUserInfoRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoRepository implements IUserInfoRepository {

    @Resource
    private UserInfoDynamicMapper userInfoDynamicMapper;

    @Override
    public boolean insert(UserInfo userInfo) {
        return userInfoDynamicMapper.insert(userInfo) > 0;
    }

    @Override
    public boolean delete(Long userId) {
        return userInfoDynamicMapper.deleteByPrimaryKey(userId) > 0;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        return userInfoDynamicMapper.updateByPrimaryKeySelective(userInfo) > 0;
    }

    @Override
    public PagerContract<UserInfo> findPage(int pageIndex, int pageSize, Long id, String account, Long teamId) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<UserInfo> list = userInfoDynamicMapper.select(query -> {
            query.where().and(UserInfoDynamicSqlSupport.id, isEqualTo(id).when(Objects::nonNull))
                .and(UserInfoDynamicSqlSupport.teamId, isEqualTo(teamId).when(s -> teamId != null && teamId > 0))
                .and(UserInfoDynamicSqlSupport.account, isLike(account).when(Objects::nonNull).then(s -> s + "%"));
            return query.orderBy(UserInfoDynamicSqlSupport.id.descending());
        });
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int)page.getTotal());
    }

    @Override
    public int deleteByTeam(Long teamId) {
        return userInfoDynamicMapper.delete(query -> query.where().and(UserInfoDynamicSqlSupport.teamId, isEqualTo(teamId)));
    }

    @Override
    public Optional<UserInfo> findByAccount(String account) {
        return userInfoDynamicMapper.selectOne(query -> query.where().and(UserInfoDynamicSqlSupport.account, isEqualTo(account)));
    }
}
