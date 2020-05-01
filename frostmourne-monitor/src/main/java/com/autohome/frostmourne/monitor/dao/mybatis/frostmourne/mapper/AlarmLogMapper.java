package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import org.apache.ibatis.annotations.Param;

public interface AlarmLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlarmLog record);

    int insertSelective(AlarmLog record);

    AlarmLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmLog record);

    int updateByPrimaryKeyWithBLOBs(AlarmLog record);

    int updateByPrimaryKey(AlarmLog record);

    List<AlarmLog> find(@Param("startTime") Date startTime,
                        @Param("endTime") Date endTime,
                        @Param("alarmId") Long alarmId,
                        @Param("verifyResult") String verifyResult,
                        @Param("executeResult") String executeResult);

    AlarmLog selectLatest(@Param("alarmId") Long alarmId,
                          @Param("verifyResult") String verifyResult);

    void clearBefore(Date reserveLine);

    Integer count(@Param("startTime") Date startTime,
                  @Param("endTime") Date endTime,
                  @Param("verifyResult") String verifyResult);

    List<AggregationDate> aggregation(@Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime,
                                      @Param("verifyResult") String verifyResult);
}