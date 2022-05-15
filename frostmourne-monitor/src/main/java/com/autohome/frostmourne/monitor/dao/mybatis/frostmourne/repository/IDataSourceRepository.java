package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;

public interface IDataSourceRepository {

    int deleteByPrimaryKey(Long id);

    int insert(DataSource record);

    int insertSelective(DataSource record);

    Optional<DataSource> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSource record);

    int updateByPrimaryKey(DataSource record);

    List<DataSource> find(DataSourceType datasourceType);

    List<DataSource> findByIdList(List<Long> idList);
}
