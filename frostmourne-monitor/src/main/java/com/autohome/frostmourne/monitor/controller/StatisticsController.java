package com.autohome.frostmourne.monitor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AggregationDate;
import com.autohome.frostmourne.monitor.service.admin.IStatisticsService;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/statistics", "/api/monitor-api/statistics"})
public class StatisticsController {

    @Resource
    private IStatisticsService statisticsService;

    @RequestMapping(value = "/panelData", method = RequestMethod.GET)
    public Protocol<Map<String, Integer>> panelData(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date startTime,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date endTime) {
        String account = AuthTool.currentUser().getAccount();
        Map<String, Integer> map = new HashMap<>();
        map.put("taskCount", statisticsService.taskTotalCount());
        map.put("executeCount", statisticsService.executeCount(startTime, endTime));
        map.put("alarmCount", statisticsService.alarmCount(startTime, endTime));
        map.put("alertCount", statisticsService.alertCount(startTime, endTime, account));
        return new Protocol<>(map);
    }

    @RequestMapping(value = "/aggregation/alarm", method = RequestMethod.GET)
    public Protocol<List<AggregationDate>> aggregationAlarm(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date startTime,
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date endTime) {
        List<AggregationDate> list = statisticsService.aggregationAlarm(startTime, endTime);
        return new Protocol<>(list);
    }

    @RequestMapping(value = "/aggregation/alert", method = RequestMethod.GET)
    public Protocol<List<AggregationDate>> aggregationAlert(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date startTime,
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date endTime) {
        String account = AuthTool.currentUser().getAccount();
        List<AggregationDate> list = statisticsService.aggregationAlert(startTime, endTime, account);
        return new Protocol<>(list);
    }
}
