package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import org.apache.ibatis.annotations.Param;

public interface IAlertLogRepository {

    int deleteByPrimaryKey(Long id);

    int insert(AlertLog record);

    int insertSelective(AlertLog record);

    AlertLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlertLog record);

    int updateByPrimaryKey(AlertLog record);

    List<AlertLog> find(Date startTime, Date endTime, Long executeId, Long alarmId, String recipient,
                        String way, String sendStatus, String inSilence, String alertType);

    AlertLog selectLatest(Long alarmId, String alertType, String inSilence);

    int count(Date startTime, Date endTime, String sendStatus, String recipient);

    List<AggregationDate> aggregation(@Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime,
                                      @Param("sendStatus") String sendStatus,
                                      @Param("recipient") String recipient);
}
