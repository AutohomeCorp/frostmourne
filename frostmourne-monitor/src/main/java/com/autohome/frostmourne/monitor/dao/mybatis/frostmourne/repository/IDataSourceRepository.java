package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;

public interface IDataSourceRepository {

    int deleteByPrimaryKey(Long id);

    int insert(DataSource record);

    int insertSelective(DataSource record);

    Optional<DataSource> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSource record);

    int updateByPrimaryKey(DataSource record);

    List<DataSource> find(String datasourceType);

    List<DataSource> findByIdList(List<Long> idList);
}
