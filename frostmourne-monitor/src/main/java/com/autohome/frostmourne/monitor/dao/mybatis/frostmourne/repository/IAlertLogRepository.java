package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import com.autohome.frostmourne.monitor.model.enums.AlertType;
import com.autohome.frostmourne.monitor.model.enums.SendStatus;
import com.autohome.frostmourne.monitor.model.enums.SilenceStatus;

public interface IAlertLogRepository {

    int deleteByPrimaryKey(Long id);

    int insert(AlertLog record);

    int insertSelective(AlertLog record);

    Optional<AlertLog> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlertLog record);

    int updateByPrimaryKey(AlertLog record);

    List<AlertLog> find(Date startTime, Date endTime, Long executeId, Long alarmId, String recipient, String way, SendStatus sendStatus, SilenceStatus inSilence,
                        AlertType alertType);

    Optional<AlertLog> selectLatest(Long alarmId, AlertType alertType, SilenceStatus inSilence);

    long count(Date startTime, Date endTime, SendStatus sendStatus, String recipient);
}
