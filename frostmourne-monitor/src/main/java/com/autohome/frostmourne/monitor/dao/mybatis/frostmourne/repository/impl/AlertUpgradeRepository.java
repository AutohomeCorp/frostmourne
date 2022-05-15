package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertUpgrade;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertUpgradeDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertUpgradeDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertUpgradeRepository;

/**
 * alert upgrade
 *
 * @author Aping
 * @since 2022/5/14 19:46
 */
@Service
public class AlertUpgradeRepository implements IAlertUpgradeRepository {

    @Resource
    private AlertUpgradeDynamicMapper alertUpgradeDynamicMapper;

    @Override
    public int insert(AlertUpgrade alertUpgrade) {
        return alertUpgradeDynamicMapper.insert(alertUpgrade);
    }

    @Override
    public int deleteByAlarmId(Long alarmId) {
        return alertUpgradeDynamicMapper.delete(query -> query.where().and(AlertUpgradeDynamicSqlSupport.alarmId, isEqualTo(alarmId)));
    }

    @Override
    public AlertUpgrade findOneByAlarmId(Long alarmId) {
        return alertUpgradeDynamicMapper.selectOne(query -> query.where().and(AlertUpgradeDynamicSqlSupport.alarmId, isEqualTo(alarmId)).limit(1)).orElse(null);
    }
}
