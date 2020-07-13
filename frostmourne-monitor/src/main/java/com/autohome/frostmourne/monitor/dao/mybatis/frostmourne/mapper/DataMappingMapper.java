package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataMapping;

public interface DataMappingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataMapping record);

    int insertSelective(DataMapping record);

    DataMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataMapping record);

    int updateByPrimaryKey(DataMapping record);
}