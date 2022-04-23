package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.RuleProperty;

public interface IRulePropertyRepository {

    int deleteByPrimaryKey(Long id);

    int insert(RuleProperty record);

    int insertSelective(RuleProperty record);

    Optional<RuleProperty> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RuleProperty record);

    int updateByPrimaryKey(RuleProperty record);

    int deleteByAlarm(Long alarmId);

    List<RuleProperty> findByRuleId(Long ruleId);
}
