package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AlarmRepository implements IAlarmRepository {

    @Resource
    private AlarmDynamicMapper alarmDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return alarmDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long insert(Alarm record) {
        return null;
    }

    @Override
    public int insertSelective(Alarm record) {
        return 0;
    }

    @Override
    public Alarm selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Alarm record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Alarm record) {
        return 0;
    }

    @Override
    public int updateStatus(Long alarmId, String status) {
        return 0;
    }

    @Override
    public List<Alarm> find(Long alarmId, String name, String teamName, String status) {
        return null;
    }

    @Override
    public void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, String executeResult) {

    }

    @Override
    public int updateJobId(Long alarmId, Long jobId) {
        return 0;
    }

    @Override
    public int total() {
        return 0;
    }
}
