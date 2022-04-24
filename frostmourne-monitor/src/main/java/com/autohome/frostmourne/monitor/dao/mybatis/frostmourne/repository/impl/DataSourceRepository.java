package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataSourceDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataSourceDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDataSourceRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceRepository implements IDataSourceRepository {

    @Resource
    private DataSourceDynamicMapper dataSourceDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dataSourceDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataSource record) {
        return dataSourceDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(DataSource record) {
        return dataSourceDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<DataSource> selectByPrimaryKey(Long id) {
        return dataSourceDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DataSource record) {
        return dataSourceDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DataSource record) {
        return dataSourceDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<DataSource> find(String datasourceType) {
        return dataSourceDynamicMapper
            .select(query -> query.where().and(DataSourceDynamicSqlSupport.datasourceType, isEqualTo(datasourceType).when(MybatisTool::notNullAndEmpty))
                .orderBy(DataSourceDynamicSqlSupport.createAt.descending()));
    }

    @Override
    public List<DataSource> findByIdList(List<Long> idList) {
        return dataSourceDynamicMapper.select(query -> query.where().and(DataSourceDynamicSqlSupport.id, isIn(idList)));
    }
}
