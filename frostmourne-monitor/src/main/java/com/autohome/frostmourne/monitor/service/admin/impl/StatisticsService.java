package com.autohome.frostmourne.monitor.service.admin.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmMapper;
import com.autohome.frostmourne.monitor.service.admin.IStatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService implements IStatisticsService {

    @Resource
    private AlarmMapper alarmMapper;

    public int alarmTotalCount() {
        return alarmMapper.total();
    }
}
