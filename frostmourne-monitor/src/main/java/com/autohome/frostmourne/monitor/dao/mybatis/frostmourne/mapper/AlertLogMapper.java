package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import org.apache.ibatis.annotations.Param;

public interface AlertLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlertLog record);

    int insertSelective(AlertLog record);

    AlertLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlertLog record);

    int updateByPrimaryKeyWithBLOBs(AlertLog record);

    int updateByPrimaryKey(AlertLog record);

    List<AlertLog> find(@Param("startTime") Date startTime,
                        @Param("endTime") Date endTime,
                        @Param("executeId") Long executeId,
                        @Param("alarmId") Long alarmId,
                        @Param("recipient") String recipient,
                        @Param("way") String way,
                        @Param("sendStatus") String sendStatus,
                        @Param("inSilence") String inSilence,
                        @Param("alertType") String alertType);

    AlertLog selectLatest(@Param("alarmId") Long alarmId,
                          @Param("alertType") String alertType,
                          @Param("inSilence") String inSilence);

    int count(@Param("startTime") Date startTime,
              @Param("endTime") Date endTime,
              @Param("sendStatus") String sendStatus,
              @Param("recipient") String recipient);

    List<AggregationDate> aggregation(@Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime,
                                      @Param("sendStatus") String sendStatus,
                                      @Param("recipient") String recipient);
}