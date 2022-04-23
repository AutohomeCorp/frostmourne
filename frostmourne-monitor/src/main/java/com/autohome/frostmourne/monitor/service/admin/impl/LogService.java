package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlertLogRepository;
import com.autohome.frostmourne.monitor.service.admin.ILogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class LogService implements ILogService {

    @Resource
    private IAlarmLogRepository alarmLogRepository;

    @Resource
    private IAlertLogRepository alertLogRepository;

    @Override
    public PagerContract<AlarmLog> findAlarmLog(int pageIndex, int pageSize, Date startTime, Date endTime, Long alarmId, String verifyResult,
        String executeResult) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<AlarmLog> list = alarmLogRepository.find(startTime, endTime, alarmId, verifyResult, executeResult);
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int)page.getTotal());
    }

    @Override
    public PagerContract<AlertLog> findAlertLog(int pageIndex, int pageSize, Date startTime, Date endTime, Long executeId, Long alarmId, String recipient,
        String way, String sendStatus, String inSilence, String alertType) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<AlertLog> list = alertLogRepository.find(startTime, endTime, executeId, alarmId, recipient, way, sendStatus, inSilence, alertType);
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int)page.getTotal());
    }
}
