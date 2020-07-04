package com.autohome.frostmourne.monitor.service.admin;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;

public interface IStatisticsService {

    int taskTotalCount();

    int executeCount(Date startTime, Date endTime);

    int alarmCount(Date startTime, Date endTime);

    List<AggregationDate> aggregationAlarm(Date startTime, Date endTime);

    int alertCount(Date startTime, Date endTime, String account);

    List<AggregationDate> aggregationAlert(Date startTime, Date endTime, String recipient);
}
