package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;

public interface IAlertLogRepository {

    int deleteByPrimaryKey(Long id);

    int insert(AlertLog record);

    int insertSelective(AlertLog record);

    Optional<AlertLog> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlertLog record);

    int updateByPrimaryKey(AlertLog record);

    List<AlertLog> find(Date startTime, Date endTime, Long executeId, Long alarmId, String recipient, String way, String sendStatus, String inSilence,
        String alertType);

    Optional<AlertLog> selectLatest(Long alarmId, String alertType, String inSilence);

    long count(Date startTime, Date endTime, String sendStatus, String recipient);
}
