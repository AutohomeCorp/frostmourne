package com.autohome.frostmourne.monitor.service.core.execute;

import com.autohome.frostmourne.monitor.contract.AlarmContract;

public interface IAlarmService {

    AlarmProcessLogger run(String account, Long alarmId, boolean test);

    AlarmProcessLogger test(AlarmContract alarmContract);

    AlarmProcessLogger run(AlarmContract alarmContract, boolean test);
}
