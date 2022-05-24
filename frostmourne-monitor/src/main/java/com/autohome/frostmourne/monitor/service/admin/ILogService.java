package com.autohome.frostmourne.monitor.service.admin;

import java.util.Date;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import com.autohome.frostmourne.monitor.model.enums.*;

public interface ILogService {

    PagerContract<AlarmLog> findAlarmLog(int pageIndex, int pageSize, Date startTime, Date endTime, Long alarmId, VerifyResult verifyResult,
        ExecuteStatus executeResult, Boolean alert);

    PagerContract<AlertLog> findAlertLog(int pageIndex, int pageSize, Date startTime, Date endTime, Long executeId, Long alarmId, String recipient, String way,
        SendStatus sendStatus, SilenceStatus inSilence, AlertType alertType);
}
