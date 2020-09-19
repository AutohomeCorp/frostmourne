package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class AlarmLog {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.alarm_id")
    private Long alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.exe_start")
    private Date exeStart;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.exe_end")
    private Date exeEnd;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.cost")
    private Integer cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.execute_result")
    private String executeResult;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.verify_result")
    private String verifyResult;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.message")
    private String message;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.alarm_id")
    public Long getAlarmId() {
        return alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.alarm_id")
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.exe_start")
    public Date getExeStart() {
        return exeStart;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.969+08:00", comments="Source field: alarm_log.exe_start")
    public void setExeStart(Date exeStart) {
        this.exeStart = exeStart;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.exe_end")
    public Date getExeEnd() {
        return exeEnd;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.exe_end")
    public void setExeEnd(Date exeEnd) {
        this.exeEnd = exeEnd;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.cost")
    public Integer getCost() {
        return cost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.cost")
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.execute_result")
    public String getExecuteResult() {
        return executeResult;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.execute_result")
    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.verify_result")
    public String getVerifyResult() {
        return verifyResult;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.verify_result")
    public void setVerifyResult(String verifyResult) {
        this.verifyResult = verifyResult;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.message")
    public String getMessage() {
        return message;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.message")
    public void setMessage(String message) {
        this.message = message;
    }
}