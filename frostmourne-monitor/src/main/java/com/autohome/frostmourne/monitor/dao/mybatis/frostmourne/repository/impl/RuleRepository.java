package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Rule;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RuleDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RuleDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IRuleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private RuleDynamicMapper ruleDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return ruleDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Rule record) {
        return ruleDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(Rule record) {
        return ruleDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<Rule> selectByPrimaryKey(Long id) {
        return ruleDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Rule record) {
        return ruleDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Rule record) {
        return ruleDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByAlarm(Long alarmId) {
        return ruleDynamicMapper.delete(query -> query.where()
                .and(RuleDynamicSqlSupport.alarm_id, isEqualTo(alarmId)));
    }

    @Override
    public Optional<Rule> findOneByAlarm(Long alarmId) {
        return ruleDynamicMapper.selectOne(query -> query.where()
                .and(RuleDynamicSqlSupport.alarm_id, isEqualTo(alarmId)));
    }
}
