package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.enums.VerifyResult;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmLogMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlertLogMapper;
import com.autohome.frostmourne.monitor.service.admin.IStatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements IStatisticsService {

    @Resource
    private AlarmMapper alarmMapper;

    @Resource
    private AlarmLogMapper alarmLogMapper;

    @Resource
    private AlertLogMapper alertLogMapper;

    public int taskTotalCount() {
        return alarmMapper.total();
    }

    public int executeCount(Date startTime, Date endTime) {
        return alarmLogMapper.count(startTime, endTime, null);
    }

    public int alarmCount(Date startTime, Date endTime) {
        return alarmLogMapper.count(startTime, endTime, VerifyResult.TRUE);
    }

    public List<AggregationDate> aggregationAlarm(Date startTime, Date endTime) {
        return alarmLogMapper.aggregation(startTime, endTime, VerifyResult.TRUE);
    }

    public int alertCount(Date startTime, Date endTime, String recipient) {
        return alertLogMapper.count(startTime, endTime, "SUCCESS", recipient);
    }

    public List<AggregationDate> aggregationAlert(Date startTime, Date endTime, String recipient) {
        return alertLogMapper.aggregation(startTime, endTime, "SUCCESS", recipient);
    }
}
