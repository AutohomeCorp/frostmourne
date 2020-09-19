package com.autohome.frostmourne.monitor.task;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmLogRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TaskComponent {

    @Value("${alarm.log.reserve.days}")
    private Integer alarmLogReserviceDays;

    @Resource
    private IAlarmLogRepository alarmLogRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    public void rollingClearAlarmLog() {
        DateTime reserveLine = DateTime.now().withTimeAtStartOfDay().minusDays(alarmLogReserviceDays);
        alarmLogRepository.clearBefore(reserveLine.toDate());
    }
}
