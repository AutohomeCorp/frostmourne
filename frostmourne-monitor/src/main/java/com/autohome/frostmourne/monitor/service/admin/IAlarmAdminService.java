package com.autohome.frostmourne.monitor.service.admin;

import java.util.Date;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.contract.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;

public interface IAlarmAdminService {

    boolean atomicSave(AlarmContract alarmContract);

    boolean delete(Long alarmId);

    boolean open(Long alarmId);

    boolean close(Long alarmId);

    AlarmContract findById(Long alarmId);

    PagerContract<Alarm> find(int pageIndex, int pageSize, Long alarmId, String name,
                              String teamName, String status, Long serverId);

    void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status);

    void padAlarm(AlarmContract alarmContract);
}
