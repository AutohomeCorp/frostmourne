package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
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
        return alarmLogDynamicMapper.select(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.createAt, isBetween(startTime).and(endTime).when((d1, d2) -> d1 != null && d2 != null))
                    .and(AlarmLogDynamicSqlSupport.alarmId, isEqualTo(alarmId).when(MybatisTool::notNullAndZero))
                    .and(AlarmLogDynamicSqlSupport.verifyResult, isEqualTo(verifyResult).when(MybatisTool::notNullAndEmpty))
                    .and(AlarmLogDynamicSqlSupport.executeResult, isEqualTo(executeResult).when(MybatisTool::notNullAndEmpty))
                    .orderBy(createAt.descending());
            return query;
        });
    }

    @Override
    public Optional<AlarmLog> selectLatest(Long alarmId, String verifyResult) {
        return alarmLogDynamicMapper.selectOne(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.alarmId, isEqualTo(alarmId))
                    .and(AlarmLogDynamicSqlSupport.verifyResult, isEqualTo(verifyResult).when(MybatisTool::notNullAndEmpty))
                    .orderBy(AlarmLogDynamicSqlSupport.id.descending())
                    .limit(1);
            return query;
        });
    }

    @Override
    public void clearBefore(Date reserveLine) {
        alarmLogDynamicMapper.delete(query -> {
            query.where().and(createAt, isLessThan(reserveLine));
            return query;
        });
    }

    @Override
    public long count(Date startTime, Date endTime, String verifyResult) {
        return alarmLogDynamicMapper.count(query -> {
            query.where().and(createAt, isBetween(startTime).and(endTime))
                    .and(AlarmLogDynamicSqlSupport.verifyResult, isEqualTo(verifyResult).when(MybatisTool::notNullAndEmpty));
            return query;
        });
    }
}
