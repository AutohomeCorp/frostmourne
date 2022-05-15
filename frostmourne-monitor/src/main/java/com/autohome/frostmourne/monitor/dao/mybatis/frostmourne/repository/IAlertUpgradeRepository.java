package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertUpgrade;

/**
 * AlertEvent repository
 *
 * @author Aping
 * @since 2022/5/14 19:46
 */
public interface IAlertUpgradeRepository {

    int insert(AlertUpgrade alertUpgrade);

    int deleteByAlarmId(Long alarmId);

    AlertUpgrade findOneByAlarmId(Long alarmId);
}
