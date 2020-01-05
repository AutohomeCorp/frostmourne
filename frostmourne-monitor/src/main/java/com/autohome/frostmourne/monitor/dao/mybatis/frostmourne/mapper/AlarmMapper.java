package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import org.apache.ibatis.annotations.Param;

public interface AlarmMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);

    int updateStatus(@Param("alarmId") Long alarmId, @Param("status") String status);

    List<Alarm> find(@Param("alarmId") Long alarmId, @Param("name") String name,
                     @Param("teamName") String teamName, @Param("status") String status);

    void updateAlarmLastExecuteInfo(@Param("alarmId") Long alarmId,
                                    @Param("executeTime") Date executeTime,
                                    @Param("executeResult") String executeResult);

    int updateJobId(@Param("alarmId") Long alarmId, @Param("jobId") Long jobId);

    int total();
}