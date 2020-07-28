package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import org.apache.ibatis.annotations.Param;

public interface AlarmLogMapper {

    AlarmLog selectByPrimaryKey(Long id);

    List<AggregationDate> aggregation(@Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime,
                                      @Param("verifyResult") String verifyResult);
}