package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertLogDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertLogDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import org.springframework.stereotype.Repository;

@Repository
public class AlertLogRepository implements IAlertLogRepository {

    @Resource
    private AlertLogDynamicMapper alertLogDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return alertLogDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AlertLog record) {
        return alertLogDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(AlertLog record) {
        return alertLogDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<AlertLog> selectByPrimaryKey(Long id) {
        return alertLogDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AlertLog record) {
        return alertLogDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AlertLog record) {
        return alertLogDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<AlertLog> find(Date startTime, Date endTime, Long executeId, Long alarmId, String recipient,
                               String way, String sendStatus, String inSilence, String alertType) {
        return alertLogDynamicMapper.select(query -> {
            query.where().and(AlertLogDynamicSqlSupport.create_at, isBetween(startTime).and(endTime).when((d1, d2) -> d1 != null && d2 != null))
                    .and(AlertLogDynamicSqlSupport.execute_id, isEqualTo(executeId).when(MybatisTool::notNullAndZero))
                    .and(AlertLogDynamicSqlSupport.alarm_id, isEqualTo(alarmId).when(MybatisTool::notNullAndZero))
                    .and(AlertLogDynamicSqlSupport.recipient, isEqualTo(recipient).when(MybatisTool::notNullAndEmpty))
                    .and(AlertLogDynamicSqlSupport.way, isEqualTo(way).when(MybatisTool::notNullAndEmpty))
                    .and(AlertLogDynamicSqlSupport.send_status, isEqualTo(sendStatus).when(MybatisTool::notNullAndEmpty))
                    .and(AlertLogDynamicSqlSupport.in_silence, isEqualTo(inSilence).when(MybatisTool::notNullAndEmpty))
                    .and(AlertLogDynamicSqlSupport.alert_type, isEqualTo(alertType).when(MybatisTool::notNullAndEmpty))
                    .orderBy(AlertLogDynamicSqlSupport.create_at.descending());
            return query;
        });
    }

    @Override
    public Optional<AlertLog> selectLatest(Long alarmId, String alertType, String inSilence) {
        return alertLogDynamicMapper.selectOne(query -> query.where()
                .and(AlertLogDynamicSqlSupport.alarm_id, isEqualTo(alarmId))
                .and(AlertLogDynamicSqlSupport.alert_type, isEqualTo(alertType))
                .and(AlertLogDynamicSqlSupport.in_silence, isEqualTo(inSilence))
                .orderBy(AlertLogDynamicSqlSupport.create_at.descending())
                .limit(1)
        );
    }

    @Override
    public long count(Date startTime, Date endTime, String sendStatus, String recipient) {
        return alertLogDynamicMapper.count(query -> query.where()
                .and(AlertLogDynamicSqlSupport.create_at, isBetween(startTime).and(endTime))
                .and(AlertLogDynamicSqlSupport.send_status, isEqualTo(sendStatus).when(MybatisTool::notNullAndEmpty))
                .and(AlertLogDynamicSqlSupport.recipient, isEqualTo(recipient)));
    }
}
