package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.enums.SendStatus;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmLogExtendMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlertLogExtendMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import com.autohome.frostmourne.monitor.model.enums.VerifyResult;
import com.autohome.frostmourne.monitor.service.admin.IStatisticsService;

@Service
public class StatisticsService implements IStatisticsService {

    @Resource
    private AlarmLogExtendMapper alarmLogExtendMapper;

    @Resource
    private AlertLogExtendMapper alertLogExtendMapper;

    @Resource
    private IAlertLogRepository alertLogRepository;

    @Resource
    private IAlarmLogRepository alarmLogRepository;

    @Resource
    private IAlarmRepository alarmRepository;

    @Override
    public long taskTotalCount() {
        return alarmRepository.total();
    }

    @Override
    public long executeCount(Date startTime, Date endTime) {
        return alarmLogRepository.count(startTime, endTime, null);
    }

    @Override
    public long alarmCount(Date startTime, Date endTime) {
        return alarmLogRepository.count(startTime, endTime, VerifyResult.TRUE);
    }

    @Override
    public List<AggregationDate> aggregationAlarm(Date startTime, Date endTime) {
        return alarmLogExtendMapper.aggregation(startTime, endTime, VerifyResult.TRUE.name());
    }

    @Override
    public long alertCount(Date startTime, Date endTime, String recipient) {
        return alertLogRepository.count(startTime, endTime, SendStatus.SUCCESS, recipient);
    }

    @Override
    public List<AggregationDate> aggregationAlert(Date startTime, Date endTime, String recipient) {
        return alertLogExtendMapper.aggregation(startTime, endTime, SendStatus.SUCCESS.name(), recipient);
    }
}
