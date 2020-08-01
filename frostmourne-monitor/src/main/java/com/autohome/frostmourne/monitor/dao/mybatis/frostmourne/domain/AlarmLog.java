package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class AlarmLog {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.086+08:00", comments="Source field: alarm_log.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.alarm_id")
    private Long alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.exe_start")
    private Date exe_start;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.exe_end")
    private Date exe_end;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.cost")
    private Integer cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.execute_result")
    private String execute_result;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.verify_result")
    private String verify_result;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.message")
    private String message;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.089+08:00", comments="Source field: alarm_log.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.alarm_id")
    public Long getAlarm_id() {
        return alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.alarm_id")
    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.exe_start")
    public Date getExe_start() {
        return exe_start;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.exe_start")
    public void setExe_start(Date exe_start) {
        this.exe_start = exe_start;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.exe_end")
    public Date getExe_end() {
        return exe_end;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.exe_end")
    public void setExe_end(Date exe_end) {
        this.exe_end = exe_end;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.cost")
    public Integer getCost() {
        return cost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.cost")
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.execute_result")
    public String getExecute_result() {
        return execute_result;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.execute_result")
    public void setExecute_result(String execute_result) {
        this.execute_result = execute_result;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.09+08:00", comments="Source field: alarm_log.verify_result")
    public String getVerify_result() {
        return verify_result;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.verify_result")
    public void setVerify_result(String verify_result) {
        this.verify_result = verify_result;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.message")
    public String getMessage() {
        return message;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.091+08:00", comments="Source field: alarm_log.message")
    public void setMessage(String message) {
        this.message = message;
    }
}