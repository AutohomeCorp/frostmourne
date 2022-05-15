package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertEvent;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertEventDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertEventDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertEventRepository;
import com.autohome.frostmourne.monitor.model.enums.AlertType;
import com.autohome.frostmourne.monitor.tool.MybatisTool;

/**
 * AlertEvent repository impl
 *
 * @author Aping
 * @since 2022/5/2 19:56
 */
@Repository
public class AlertEventRepository implements IAlertEventRepository {

    @Resource
    private AlertEventDynamicMapper alertEventDynamicMapper;

    @Override
    public int insert(AlertEvent alertEvent) {
        return alertEventDynamicMapper.insert(alertEvent);
    }

    @Override
    public List<AlertEvent> selectAllByTime(Long alarmId, Boolean inSilence, AlertType alertType, LocalDateTime startTime, LocalDateTime endTime) {
        return alertEventDynamicMapper.select(query -> {
            query.where().and(AlertEventDynamicSqlSupport.createAt, isBetween(startTime).and(endTime).when((d1, d2) -> d1 != null && d2 != null))
                .and(AlertEventDynamicSqlSupport.alarmId, isEqualTo(alarmId).when(MybatisTool::notNullAndZero))
                .and(AlertEventDynamicSqlSupport.inSilence, isEqualTo(inSilence).when(MybatisTool::notNull))
                .and(AlertEventDynamicSqlSupport.alertType, isEqualTo(alertType).when(MybatisTool::notNull))
                .orderBy(AlertEventDynamicSqlSupport.createAt.descending());
            return query;
        });
    }
}
