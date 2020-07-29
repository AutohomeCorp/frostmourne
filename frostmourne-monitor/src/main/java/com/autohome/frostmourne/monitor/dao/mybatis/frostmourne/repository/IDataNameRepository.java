package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;

public interface IDataNameRepository {

    int deleteByPrimaryKey(Long id);

    int insert(DataName record);

    int insertSelective(DataName record);

    Optional<DataName> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataName record);

    int updateByPrimaryKey(DataName record);

    Optional<DataName> findByName(String dataName);

    List<DataName> find(String datasourceType, Long datasourceId);
}
