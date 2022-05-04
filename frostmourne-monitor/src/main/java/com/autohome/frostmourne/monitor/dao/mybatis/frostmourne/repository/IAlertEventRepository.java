package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertEvent;
import com.autohome.frostmourne.monitor.model.enums.AlertType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * AlertEvent repository
 *
 * @author Aping
 * @since 2022/5/2 19:56
 */
public interface IAlertEventRepository {

    int insert(AlertEvent alertEvent);

    List<AlertEvent> selectAllByTime(Long alarmId, Boolean inSilence, AlertType alertType, LocalDateTime startTime, LocalDateTime endTime);

}
