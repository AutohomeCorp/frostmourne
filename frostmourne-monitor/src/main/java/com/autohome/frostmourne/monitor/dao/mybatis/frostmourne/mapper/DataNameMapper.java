package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;

public interface DataNameMapper {

    DataName selectByPrimaryKey(Long id);
}