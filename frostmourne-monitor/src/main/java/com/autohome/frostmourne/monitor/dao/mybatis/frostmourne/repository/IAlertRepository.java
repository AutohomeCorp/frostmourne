package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Alert;

public interface IAlertRepository {

    int deleteByPrimaryKey(Long id);

    int insert(Alert record);

    int insertSelective(Alert record);

    Optional<Alert> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alert record);

    int updateByPrimaryKey(Alert record);

    int deleteByAlarm(Long alarmId);

    Optional<Alert> findOneByAlarm(Long alarmId);
}
