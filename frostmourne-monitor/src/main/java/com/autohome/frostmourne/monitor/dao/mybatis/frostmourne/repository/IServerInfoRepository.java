package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.contract.ServerInfoQueryForm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServerInfo;
import com.github.pagehelper.PageInfo;

public interface IServerInfoRepository {

    void insertSelective(ServerInfo record);

    void updateByPrimaryKeySelective(ServerInfo record);

    Optional<ServerInfo> getById(Long id);

    List<ServerInfo> listByIds(List<Long> ids);

    PageInfo<ServerInfo> find(ServerInfoQueryForm form);

}
