package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Alarm {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.752+08:00", comments="Source field: alarm.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.alarm_name")
    private String alarm_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.alarm_type")
    private String alarm_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.description")
    private String description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.owner_key")
    private String owner_key;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.status")
    private String status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.execute_result")
    private String execute_result;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.execute_at")
    private Date execute_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.job_id")
    private Long job_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.cron")
    private String cron;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.modify_at")
    private Date modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.team_name")
    private String team_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.754+08:00", comments="Source field: alarm.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.alarm_name")
    public String getAlarm_name() {
        return alarm_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.alarm_name")
    public void setAlarm_name(String alarm_name) {
        this.alarm_name = alarm_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.alarm_type")
    public String getAlarm_type() {
        return alarm_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.alarm_type")
    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.description")
    public String getDescription() {
        return description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.owner_key")
    public String getOwner_key() {
        return owner_key;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.owner_key")
    public void setOwner_key(String owner_key) {
        this.owner_key = owner_key;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.status")
    public String getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.status")
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.execute_result")
    public String getExecute_result() {
        return execute_result;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.755+08:00", comments="Source field: alarm.execute_result")
    public void setExecute_result(String execute_result) {
        this.execute_result = execute_result;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.execute_at")
    public Date getExecute_at() {
        return execute_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.execute_at")
    public void setExecute_at(Date execute_at) {
        this.execute_at = execute_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.job_id")
    public Long getJob_id() {
        return job_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.job_id")
    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.cron")
    public String getCron() {
        return cron;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.cron")
    public void setCron(String cron) {
        this.cron = cron;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.modify_at")
    public Date getModify_at() {
        return modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.modify_at")
    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.team_name")
    public String getTeam_name() {
        return team_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.756+08:00", comments="Source field: alarm.team_name")
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}