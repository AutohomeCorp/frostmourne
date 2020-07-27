package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;

public interface IAlarmLogRepository {

    int deleteByPrimaryKey(Long id);

    int insert(AlarmLog record);

    int insertSelective(AlarmLog record);

    Optional<AlarmLog> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmLog record);

    int updateByPrimaryKey(AlarmLog record);

    List<AlarmLog> find(Date startTime, Date endTime, Long alarmId, String verifyResult, String executeResult);

    Optional<AlarmLog> selectLatest(Long alarmId, String verifyResult);

    void clearBefore(Date reserveLine);

    long count(Date startTime, Date endTime, String verifyResult);
}
