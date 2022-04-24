package com.autohome.frostmourne.monitor.service.admin;

import java.util.Date;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;

public interface ILogService {

    PagerContract<AlarmLog> findAlarmLog(int pageIndex, int pageSize, Date startTime, Date endTime, Long alarmId, String verifyResult, String executeResult);

    PagerContract<AlertLog> findAlertLog(int pageIndex, int pageSize, Date startTime, Date endTime, Long executeId, Long alarmId, String recipient, String way,
        String sendStatus, String inSilence, String alertType);
}
