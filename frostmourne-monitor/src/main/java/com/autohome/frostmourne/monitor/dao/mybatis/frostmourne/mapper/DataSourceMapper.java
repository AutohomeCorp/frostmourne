package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;

public interface DataSourceMapper {

    DataSource selectByPrimaryKey(Long id);
}