package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;

public interface IAlarmRepository {

    int deleteByPrimaryKey(Long id);

    Long insert(Alarm record);

    Optional<Alarm> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);

    int updateStatus(Long alarmId, String status);

    PagerContract<Alarm> findPage(int pageIndex, int pageSize, Long alarmId, String name, String teamName, String status, Long serviceId);

    void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, String executeResult);

    int updateJobId(Long alarmId, Long jobId);

    long total();

    List<Alarm> querySchedule(Long maxNextTime, Long size);

    int scheduleUpdate(long alarmId, long triggerLastTime, long triggerNextTime);
}
