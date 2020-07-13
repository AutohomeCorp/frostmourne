package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlarmLogMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.AlertLogMapper;
import com.autohome.frostmourne.monitor.service.admin.ILogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

@Service
public class LogService implements ILogService {

    @Resource
    private AlarmLogMapper alarmLogMapper;

    @Resource
    private AlertLogMapper alertLogMapper;

    public PagerContract<AlarmLog> findAlarmLog(int pageIndex, int pageSize, Date startTime,
                                                Date endTime, Long alarmId, String verifyResult, String executeResult) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<AlarmLog> list = alarmLogMapper.find(startTime, endTime, alarmId, verifyResult, executeResult);
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int) page.getTotal());
    }

    public PagerContract<AlertLog> findAlertLog(int pageIndex, int pageSize, Date startTime, Date endTime,
                                                Long executeId, Long alarmId, String recipient, String way,
                                                String sendStatus, String inSilence, String alertType) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<AlertLog> list = alertLogMapper.find(startTime, endTime, executeId, alarmId, recipient, way, sendStatus, inSilence, alertType);
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int) page.getTotal());
    }
}
