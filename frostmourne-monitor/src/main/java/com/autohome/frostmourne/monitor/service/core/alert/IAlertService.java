package com.autohome.frostmourne.monitor.service.core.alert;

import com.autohome.frostmourne.monitor.service.core.execute.AlarmProcessLogger;

public interface IAlertService {

    void alert(AlarmProcessLogger alarmProcessLogger);

    void alarmLog(AlarmProcessLogger alarmProcessLogger);
}
