package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Alarm {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.989+08:00", comments="Source field: alarm.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.992+08:00", comments="Source field: alarm.alarm_name")
    private String alarmName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.alarm_type")
    private String alarmType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.description")
    private String description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.owner_key")
    private String ownerKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.status")
    private String status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.execute_result")
    private String executeResult;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.execute_at")
    private Date executeAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.job_id")
    private Long jobId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.cron")
    private String cron;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.modify_at")
    private Date modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.team_name")
    private String teamName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.risk_level")
    private String riskLevel;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.service_id")
    private Long serviceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.recover_notice_status")
    private String recoverNoticeStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.992+08:00", comments="Source field: alarm.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.992+08:00", comments="Source field: alarm.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.992+08:00", comments="Source field: alarm.alarm_name")
    public String getAlarmName() {
        return alarmName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.alarm_name")
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.alarm_type")
    public String getAlarmType() {
        return alarmType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.alarm_type")
    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.description")
    public String getDescription() {
        return description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.description")
    public void setDescription(String description) {
        this.description = description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.owner_key")
    public String getOwnerKey() {
        return ownerKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.owner_key")
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.status")
    public String getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.status")
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.execute_result")
    public String getExecuteResult() {
        return executeResult;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.993+08:00", comments="Source field: alarm.execute_result")
    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.execute_at")
    public Date getExecuteAt() {
        return executeAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.execute_at")
    public void setExecuteAt(Date executeAt) {
        this.executeAt = executeAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.job_id")
    public Long getJobId() {
        return jobId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.job_id")
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.cron")
    public String getCron() {
        return cron;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.cron")
    public void setCron(String cron) {
        this.cron = cron;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.modify_at")
    public Date getModifyAt() {
        return modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.994+08:00", comments="Source field: alarm.modify_at")
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.team_name")
    public String getTeamName() {
        return teamName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.team_name")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.risk_level")
    public String getRiskLevel() {
        return riskLevel;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.risk_level")
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.service_id")
    public Long getServiceId() {
        return serviceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.service_id")
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.recover_notice_status")
    public String getRecoverNoticeStatus() {
        return recoverNoticeStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.995+08:00", comments="Source field: alarm.recover_notice_status")
    public void setRecoverNoticeStatus(String recoverNoticeStatus) {
        this.recoverNoticeStatus = recoverNoticeStatus;
    }
}