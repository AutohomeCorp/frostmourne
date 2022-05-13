package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.model.enums.VerifyResult;
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
    public List<AlarmLog> find(Date startTime, Date endTime, Long alarmId, VerifyResult verifyResult, ExecuteStatus executeResult, Boolean alert) {
        return alarmLogDynamicMapper.select(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.createAt, isBetween(startTime).and(endTime).when((d1, d2) -> d1 != null && d2 != null))
                .and(AlarmLogDynamicSqlSupport.alarmId, isEqualTo(alarmId).when(MybatisTool::notNullAndZero))
                .and(AlarmLogDynamicSqlSupport.verifyResult, isEqualTo(verifyResult).when(MybatisTool::notNull))
                .and(AlarmLogDynamicSqlSupport.alert, isEqualTo(alert).when(MybatisTool::notNull))
                .and(AlarmLogDynamicSqlSupport.executeResult, isEqualTo(executeResult).when(MybatisTool::notNull))
                .orderBy(AlarmLogDynamicSqlSupport.createAt.descending());
            return query;
        });
    }

    @Override
    public Optional<AlarmLog> selectLatest(Long alarmId, VerifyResult verifyResult) {
        return alarmLogDynamicMapper.selectOne(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.alarmId, isEqualTo(alarmId))
                .and(AlarmLogDynamicSqlSupport.verifyResult, isEqualTo(verifyResult).when(MybatisTool::notNull))
                .orderBy(AlarmLogDynamicSqlSupport.id.descending()).limit(1);
            return query;
        });
    }

    @Override
    public void clearBefore(Date reserveLine) {
        alarmLogDynamicMapper.delete(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.createAt, isLessThan(reserveLine));
            return query;
        });
    }

    @Override
    public long count(Date startTime, Date endTime, VerifyResult verifyResult) {
        return alarmLogDynamicMapper.count(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.createAt, isBetween(startTime).and(endTime)).and(AlarmLogDynamicSqlSupport.verifyResult,
                isEqualTo(verifyResult).when(MybatisTool::notNull));
            return query;
        });
    }

    @Override
    public List<AlarmLog> selectRecently(int rows) {
        return alarmLogDynamicMapper.select(query -> {
            query.where().and(AlarmLogDynamicSqlSupport.executeResult, isEqualTo(ExecuteStatus.SUCCESS).when(MybatisTool::notNull))
                .orderBy(AlarmLogDynamicSqlSupport.createAt.descending()).limit(rows);
            return query;
        });
    }
}
