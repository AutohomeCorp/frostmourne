package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alert;

public interface AlertMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Alert record);

    int insertSelective(Alert record);

    Alert selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alert record);

    int updateByPrimaryKey(Alert record);

    int deleteByAlarm(Long alarmId);

    Alert findOneByAlarm(Long alarmId);
}