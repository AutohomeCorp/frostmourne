package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.generate.AlertLogMapper;
import org.apache.ibatis.annotations.Param;

public interface AlertLogExtendMapper extends AlertLogMapper {

    List<AggregationDate> aggregation(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("sendStatus") String sendStatus,
        @Param("recipient") String recipient);
}
