package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import org.apache.ibatis.annotations.Param;

public interface AlertLogMapper {
    AlertLog selectByPrimaryKey(Long id);

    List<AggregationDate> aggregation(@Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime,
                                      @Param("sendStatus") String sendStatus,
                                      @Param("recipient") String recipient);
}