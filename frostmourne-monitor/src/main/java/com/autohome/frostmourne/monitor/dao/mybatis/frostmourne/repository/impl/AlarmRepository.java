package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import org.mybatis.dynamic.sql.select.CountDSL;
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
        alarmDynamicMapper.insert(record);
        return record.getId();
    }

    @Override
    public Optional<Alarm> selectByPrimaryKey(Long id) {
        return alarmDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Alarm record) {
        return alarmDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Alarm record) {
        return alarmDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatus(Long alarmId, String status) {
        Alarm record = new Alarm();
        record.setId(alarmId);
        record.setStatus(status);
        return alarmDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Alarm> find(Long alarmId, String name, String teamName, String status) {
        return alarmDynamicMapper.select(c -> c.where()
                .and(AlarmDynamicSqlSupport.id, isEqualTo(alarmId).when(MybatisTool::notNullAndZero))
                .and(AlarmDynamicSqlSupport.alarm_name, isLike(name).when(MybatisTool::notNullAndEmpty).then(MybatisTool::twoSideVagueMatch))
                .and(AlarmDynamicSqlSupport.team_name, isEqualTo(teamName).when(MybatisTool::notNullAndEmpty))
                .and(AlarmDynamicSqlSupport.status, isEqualTo(status).when(MybatisTool::notNullAndEmpty))
                .orderBy(AlarmDynamicSqlSupport.create_at.descending()));
    }

    @Override
    public void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, String executeResult) {
        Alarm record = new Alarm();
        record.setId(alarmId);
        record.setExecute_at(executeTime);
        record.setExecute_result(executeResult);
        alarmDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateJobId(Long alarmId, Long jobId) {
        Alarm record = new Alarm();
        record.setId(alarmId);
        record.setJob_id(jobId);
        return alarmDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public long total() {
        return alarmDynamicMapper.count(CountDSL::where);
    }
}
