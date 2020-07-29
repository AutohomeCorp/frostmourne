package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Rule;

public interface RuleMapper {

    Rule selectByPrimaryKey(Long id);

    /*int deleteByPrimaryKey(Long id);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);

    int deleteByAlarm(Long alarmId);

    Rule findOneByAlarm(Long alarmId);*/
}