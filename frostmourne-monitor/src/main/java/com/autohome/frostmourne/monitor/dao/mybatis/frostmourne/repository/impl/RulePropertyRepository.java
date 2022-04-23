package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.RuleProperty;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RulePropertyDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RulePropertyDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IRulePropertyRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RulePropertyRepository implements IRulePropertyRepository {

    @Resource
    private RulePropertyDynamicMapper rulePropertyDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return rulePropertyDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RuleProperty record) {
        return rulePropertyDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(RuleProperty record) {
        return rulePropertyDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<RuleProperty> selectByPrimaryKey(Long id) {
        return rulePropertyDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RuleProperty record) {
        return rulePropertyDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RuleProperty record) {
        return rulePropertyDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByAlarm(Long alarmId) {
        return rulePropertyDynamicMapper.delete(query -> query.where().and(RulePropertyDynamicSqlSupport.alarmId, isEqualTo(alarmId)));
    }

    @Override
    public List<RuleProperty> findByRuleId(Long ruleId) {
        return rulePropertyDynamicMapper.select(query -> query.where().and(RulePropertyDynamicSqlSupport.ruleId, isEqualTo(ruleId)));
    }
}
