package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isIn;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataNameDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataNameDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDataNameRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import org.springframework.stereotype.Repository;

@Repository
public class DataNameRepository implements IDataNameRepository {

    @Resource
    private DataNameDynamicMapper dataNameDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dataNameDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DataName record) {
        return dataNameDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(DataName record) {
        return dataNameDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<DataName> selectByPrimaryKey(Long id) {
        return dataNameDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DataName record) {
        return dataNameDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DataName record) {
        return dataNameDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public Optional<DataName> findByName(String dataName) {
        return dataNameDynamicMapper.selectOne(query -> query.where().and(DataNameDynamicSqlSupport.DATA_NAME, isEqualTo(dataName)));
    }

    @Override
    public List<DataName> findByNames(List<String> dataNames) {
        return dataNameDynamicMapper.select(query -> query.where()
                .and(DataNameDynamicSqlSupport.DATA_NAME, isIn(dataNames))
                .orderBy(DataNameDynamicSqlSupport.createAt.descending()));
    }

    @Override
    public List<DataName> find(String datasourceType, Long datasourceId) {
        return dataNameDynamicMapper.select(query -> query.where()
                .and(DataNameDynamicSqlSupport.datasourceType, isEqualTo(datasourceType).when(MybatisTool::notNullAndEmpty))
                .and(DataNameDynamicSqlSupport.dataSourceId, isEqualTo(datasourceId).when(MybatisTool::notNullAndZero))
                .orderBy(DataNameDynamicSqlSupport.createAt.descending()));
    }
}
