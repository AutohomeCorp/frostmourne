package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alert;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AlertRepository implements IAlertRepository {

    @Resource
    private AlertDynamicMapper alertDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return alertDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Alert record) {
        return alertDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(Alert record) {
        return alertDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<Alert> selectByPrimaryKey(Long id) {
        return alertDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Alert record) {
        return alertDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Alert record) {
        return alertDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByAlarm(Long alarmId) {
        return alertDynamicMapper.delete(query -> query.where().and(AlertDynamicSqlSupport.alarm_id, isEqualTo(alarmId)));
    }

    @Override
    public Optional<Alert> findOneByAlarm(Long alarmId) {
        return alertDynamicMapper.selectOne(query -> query.where().and(AlertDynamicSqlSupport.alarm_id, isEqualTo(alarmId)).limit(1));
    }
}
