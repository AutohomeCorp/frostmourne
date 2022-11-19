package com.autohome.frostmourne.monitor.service.admin;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Alarm;
import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;

public interface IAlarmAdminService {

    boolean atomicSave(AlarmContract alarmContract);

    boolean delete(Long alarmId);

    boolean open(Long alarmId);

    boolean close(Long alarmId);

    AlarmContract findById(Long alarmId);

    PagerContract<Alarm> find(int pageIndex, int pageSize, Long alarmId, String name, String teamName, AlarmStatus status, Long serviceId);

    void updateAlarmLastExecuteInfo(Long alarmId, Date executeTime, ExecuteStatus status);

    void padAlarm(AlarmContract alarmContract);

    void updateTeamNameByIdList(List<Long> alarmIdList, String newTeamName);
}
