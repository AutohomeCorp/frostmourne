package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.enums.VerifyResult;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmLogMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlertLogMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import com.autohome.frostmourne.monitor.service.admin.IStatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements IStatisticsService {

    @Resource
    private AlarmLogMapper alarmLogMapper;

    @Resource
    private AlertLogMapper alertLogMapper;

    @Resource
    private IAlertLogRepository alertLogRepository;

    @Resource
    private IAlarmLogRepository alarmLogRepository;

    @Resource
    private IAlarmRepository alarmRepository;

    public long taskTotalCount() {
        return alarmRepository.total();
    }

    public long executeCount(Date startTime, Date endTime) {
        return alarmLogRepository.count(startTime, endTime, null);
    }

    public long alarmCount(Date startTime, Date endTime) {
        return alarmLogRepository.count(startTime, endTime, VerifyResult.TRUE);
    }

    public List<AggregationDate> aggregationAlarm(Date startTime, Date endTime) {
        return alarmLogMapper.aggregation(startTime, endTime, VerifyResult.TRUE);
    }

    public long alertCount(Date startTime, Date endTime, String recipient) {
        return alertLogRepository.count(startTime, endTime, "SUCCESS", recipient);
    }

    public List<AggregationDate> aggregationAlert(Date startTime, Date endTime, String recipient) {
        return alertLogMapper.aggregation(startTime, endTime, "SUCCESS", recipient);
    }
}
