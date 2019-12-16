package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Recipient;

public interface RecipientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Recipient record);

    int insertSelective(Recipient record);

    Recipient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recipient record);

    int updateByPrimaryKey(Recipient record);

    int deleteByAlarm(Long alarmId);

    List<Recipient> findByAlarm(Long alarmId);
}