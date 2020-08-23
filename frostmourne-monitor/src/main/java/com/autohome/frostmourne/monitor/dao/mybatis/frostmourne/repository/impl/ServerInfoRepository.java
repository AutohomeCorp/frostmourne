package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.ServerInfoQueryForm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServerInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IServerInfoRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.dynamic.sql.SortSpecification;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ServerInfoRepository implements IServerInfoRepository {

    @Resource
    private ServerInfoDynamicMapper serverInfoDynamicMapper;

    @Override
    public void insertSelective(ServerInfo record) {
        serverInfoDynamicMapper.insertSelective(record);
    }

    @Override
    public void updateByPrimaryKeySelective(ServerInfo record) {
        serverInfoDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Optional<ServerInfo> getById(Long id) {
        return serverInfoDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ServerInfo> listByIds(List<Long> ids) {
        return serverInfoDynamicMapper.select(query -> query.where()
                .and(ServerInfoDynamicSqlSupport.id, SqlBuilder.isIn(ids)));
    }

    @Override
    public PageInfo<ServerInfo> find(ServerInfoQueryForm form) {
        PageHelper.startPage(form.getPageIndex(), form.getPageSize());
        List<ServerInfo> records = serverInfoDynamicMapper.select(query -> query.where()
                .and(ServerInfoDynamicSqlSupport.server_name, isLike(form.getServerName()).when(MybatisTool::notNullAndEmpty).then(MybatisTool::twoSideVagueMatch))
                .orderBy(this.parseOrderByColumns(form)));
        return new PageInfo<>(records);
    }

    private SortSpecification[] parseOrderByColumns(ServerInfoQueryForm form) {
        if ("SERVER_NAME".equalsIgnoreCase(form.getOrderType())) {
            return new SortSpecification[]{ServerInfoDynamicSqlSupport.server_name};
        }
        return new SortSpecification[]{ServerInfoDynamicSqlSupport.create_at.descending()};
    }

}
