package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertLogDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AlertLogRepository implements IAlertLogRepository {

    @Resource
    private AlertLogDynamicMapper alertLogDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(AlertLog record) {
        return 0;
    }

    @Override
    public int insertSelective(AlertLog record) {
        return 0;
    }

    @Override
    public AlertLog selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(AlertLog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(AlertLog record) {
        return 0;
    }

    @Override
    public List<AlertLog> find(Date startTime, Date endTime, Long executeId, Long alarmId, String recipient, String way, String sendStatus, String inSilence, String alertType) {
        return null;
    }

    @Override
    public AlertLog selectLatest(Long alarmId, String alertType, String inSilence) {
        return null;
    }

    @Override
    public int count(Date startTime, Date endTime, String sendStatus, String recipient) {
        return 0;
    }

    @Override
    public List<AggregationDate> aggregation(Date startTime, Date endTime, String sendStatus, String recipient) {
        return null;
    }
}
