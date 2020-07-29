package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;
import org.apache.ibatis.annotations.Param;

public interface DataNameMapper {

    DataName selectByPrimaryKey(Long id);
    /*int deleteByPrimaryKey(Long id);

    int insert(DataName record);

    int insertSelective(DataName record);

    int updateByPrimaryKeySelective(DataName record);

    int updateByPrimaryKey(DataName record);

    DataName findByName(String dataName);

    List<DataName> find(@Param("datasourceType") String datasourceType,
                        @Param("datasourceId") Long datasourceId);*/
}