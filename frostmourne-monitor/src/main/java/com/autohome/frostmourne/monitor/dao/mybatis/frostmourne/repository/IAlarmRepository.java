package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import org.apache.ibatis.annotations.Param;

public interface IAlarmRepository {

    int deleteByPrimaryKey(Long id);

    Long insert(Alarm record);

    Optional<Alarm> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);

    int updateStatus(@Param("alarmId") Long alarmId, @Param("status") String status);

    PagerContract<Alarm> findPage(int pageIndex, int pageSize, Long alarmId, String name, String teamName, String status);

    void updateAlarmLastExecuteInfo(@Param("alarmId") Long alarmId,
                                    @Param("executeTime") Date executeTime,
                                    @Param("executeResult") String executeResult);

    int updateJobId(@Param("alarmId") Long alarmId, @Param("jobId") Long jobId);

    long total();
}
