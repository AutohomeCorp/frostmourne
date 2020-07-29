package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;
import org.apache.ibatis.annotations.Param;

public interface DataSourceMapper {

    DataSource selectByPrimaryKey(Long id);

    List<DataSource> find(@Param("datasourceType") String datasourceType);

    List<DataSource> findByIdList(@Param("idList") List<Long> idList);
}