package com.autohome.frostmourne.monitor.service.admin;

public interface IScheduleService {

    Integer addJob(Long alarmId, String cron, String status);

    void updateJob(Long alarmId, Integer jobId, String cron, String status);

    void removeJob(Integer jobId);

    void openJob(Integer jobId);

    void closeJob(Integer jobId);
}
