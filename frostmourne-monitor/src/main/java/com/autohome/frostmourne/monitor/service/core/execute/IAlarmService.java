package com.autohome.frostmourne.monitor.service.core.execute;

import com.autohome.frostmourne.monitor.model.contract.AlarmContract;

public interface IAlarmService {

    AlarmProcessLogger run(Long alarmId, boolean test);

    AlarmProcessLogger test(AlarmContract alarmContract);

    AlarmProcessLogger run(AlarmContract alarmContract, boolean test);
}
