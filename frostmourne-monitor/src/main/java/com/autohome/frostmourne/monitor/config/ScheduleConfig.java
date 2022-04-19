package com.autohome.frostmourne.monitor.config;

import javax.annotation.Resource;

import com.alibaba.druid.pool.DruidDataSource;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IAlarmRepository;
import com.autohome.frostmourne.monitor.service.core.schedule.IRemoteTriggerService;
import com.autohome.frostmourne.monitor.service.core.schedule.JobScheduleHelper;
import com.autohome.frostmourne.monitor.service.core.schedule.JobTriggerHelper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfig implements InitializingBean, DisposableBean {

    private static ScheduleConfig scheduleConfig = null;

    public static ScheduleConfig getInstance() {
        return scheduleConfig;
    }

    @Value("${schedule.enabled}")
    private Boolean scheduleEnabled;

    @Value("${schedule.trigger.pool.fast.max}")
    private Integer triggerPoolFastMax;

    @Value("${schedule.trigger.pool.slow.max}")
    private Integer triggerPoolSlowMax;

    @Resource
    private IAlarmRepository alarmRepository;

    @Resource
    private IRemoteTriggerService remoteTriggerService;

    @Resource(name = "frostmourneDataSource")
    private DruidDataSource druidDataSource;

    public Integer getTriggerPoolFastMax() {
        if (triggerPoolFastMax < 200) {
            return 200;
        }
        return triggerPoolFastMax;
    }

    public Integer getTriggerPoolSlowMax() {
        if (triggerPoolSlowMax < 200) {
            return 200;
        }
        return triggerPoolSlowMax;
    }

    public Boolean getScheduleEnabled() {
        return scheduleEnabled;
    }

    @Override
    public void destroy() throws Exception {

        if (this.scheduleEnabled) {
            JobScheduleHelper.getInstance().toStop();
            JobTriggerHelper.toStop();
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduleConfig = this;

        if (this.scheduleEnabled) {
            JobTriggerHelper.toStart();
            JobScheduleHelper.getInstance().start();
        }
    }

    public IAlarmRepository getAlarmRepository() {
        return alarmRepository;
    }

    public IRemoteTriggerService getRemoteTriggerService() {
        return remoteTriggerService;
    }

    public DruidDataSource getDruidDataSource() {
        return druidDataSource;
    }
}
