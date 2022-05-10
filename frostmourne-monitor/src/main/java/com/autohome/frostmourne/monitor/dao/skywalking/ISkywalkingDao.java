package com.autohome.frostmourne.monitor.dao.skywalking;

import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingAlarmQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingAlarms;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogs;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 0:23
 */
public interface ISkywalkingDao {

    SkywalkingLogs queryLogs(String user, String password, String skywalkingAddr, SkywalkingLogQueryCondition condition);

    SkywalkingAlarms queryAlarms(String user, String password, String skywalkingAddr, SkywalkingAlarmQueryCondition condition);
}
