package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AlarmLogRepository implements IAlarmLogRepository {

    @Resource
    private AlarmLogDynamicMapper alarmLogDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return alarmLogDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AlarmLog record) {
        return alarmLogDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(AlarmLog record) {
        return alarmLogDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<AlarmLog> selectByPrimaryKey(Long id) {
        return alarmLogDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AlarmLog record) {
        return alarmLogDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AlarmLog record) {
        return alarmLogDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AlarmLog> find(Date startTime, Date endTime, Long alarmId, String verifyResult, String executeResult) {
        return null;
    }

    @Override
    public AlarmLog selectLatest(Long alarmId, String verifyResult) {
        return null;
    }

    @Override
    public void clearBefore(Date reserveLine) {

    }

    @Override
    public Integer count(Date startTime, Date endTime, String verifyResult) {
        return null;
    }

    @Override
    public List<AggregationDate> aggregation(Date startTime, Date endTime, String verifyResult) {
        return null;
    }
}
