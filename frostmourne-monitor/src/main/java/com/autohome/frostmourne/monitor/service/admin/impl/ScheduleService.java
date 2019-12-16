package com.autohome.frostmourne.monitor.service.admin.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.dao.http.IXxlJob;
import com.autohome.frostmourne.monitor.dao.http.model.XxlJobInfo;
import com.autohome.frostmourne.monitor.service.admin.IScheduleService;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements IScheduleService {

    @Value("${xxl.job.executor.id}")
    private Integer jobExecutorId;

    @Value("${xxl.job.alarm.email}")
    private String alarmEmail;

    @Resource
    private IXxlJob xxlJobApi;

    @Override
    public Integer addJob(Long alarmId, String cron, String status) {
        XxlJobInfo xxlJobInfo = toXxlJobInfo(alarmId, cron, new Date());
        xxlJobInfo.setAddTime(xxlJobInfo.getUpdateTime());
        ReturnT<String> returnT = xxlJobApi.addJob(xxlJobInfo);
        if (returnT.getCode() != 200) {
            throw new RuntimeException("error when addJob, return: " + JacksonUtil.serialize(returnT));
        }
        Integer jobId = Integer.parseInt(returnT.getContent());
        if (status.equalsIgnoreCase(AlarmStatus.OPEN)) {
            try {
                this.openJob(jobId);
            } catch (Exception ex) {
                this.removeJob(jobId);
                throw ex;
            }
        }
        return jobId;
    }

    @Override
    public void updateJob(Long alarmId, Integer jobId, String cron, String status) {
        XxlJobInfo xxlJobInfo = toXxlJobInfo(alarmId, cron, new Date());
        xxlJobInfo.setId(jobId);
        ReturnT<String> returnT = xxlJobApi.updateJob(xxlJobInfo);
        if (returnT.getCode() != 200) {
            throw new RuntimeException("error when updateJob, return: " + JacksonUtil.serialize(returnT));
        }
        if (status.equalsIgnoreCase(AlarmStatus.OPEN)) {
            this.openJob(jobId);
        } else {
            this.closeJob(jobId);
        }
    }

    @Override
    public void removeJob(Integer jobId) {
        ReturnT<String> returnT = xxlJobApi.remove(jobId);
        if (returnT.getCode() != 200) {
            throw new RuntimeException("error when removeJob, return: " + JacksonUtil.serialize(returnT));
        }
    }

    @Override
    public void openJob(Integer jobId) {
        ReturnT<String> returnT = xxlJobApi.start(jobId);
        if (returnT.getCode() != 200) {
            throw new RuntimeException("error when openJob, return: " + JacksonUtil.serialize(returnT));
        }
    }

    @Override
    public void closeJob(Integer jobId) {
        ReturnT<String> returnT = xxlJobApi.stop(jobId);
        if (returnT.getCode() != 200) {
            throw new RuntimeException("error when closeJob, return: " + JacksonUtil.serialize(returnT));
        }
    }

    private XxlJobInfo toXxlJobInfo(Long alarmId, String cron, Date now) {
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        xxlJobInfo.setAlarmEmail(alarmEmail);
        xxlJobInfo.setAuthor("frostmourne");
        xxlJobInfo.setJobGroup(jobExecutorId);
        xxlJobInfo.setJobCron(cron);
        xxlJobInfo.setUpdateTime(now);
        xxlJobInfo.setExecutorRouteStrategy("RANDOM");
        xxlJobInfo.setExecutorHandler("frostmourneJobHandler");
        xxlJobInfo.setExecutorBlockStrategy("COVER_EARLY");
        xxlJobInfo.setExecutorFailRetryCount(1);
        xxlJobInfo.setExecutorTimeout(300);
        xxlJobInfo.setJobDesc("frostmourneJob-" + alarmId);
        xxlJobInfo.setGlueType("BEAN");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("alarmId", alarmId);
        xxlJobInfo.setExecutorParam(JacksonUtil.serialize(paramMap));

        return xxlJobInfo;
    }
}
