package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Recipient;

public interface IRecipientRepository {

    int deleteByPrimaryKey(Long id);

    int insert(Recipient record);

    int insertSelective(Recipient record);

    Optional<Recipient> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recipient record);

    int updateByPrimaryKey(Recipient record);

    int deleteByAlarm(Long alarmId);

    List<Recipient> findByAlarm(Long alarmId);
}
