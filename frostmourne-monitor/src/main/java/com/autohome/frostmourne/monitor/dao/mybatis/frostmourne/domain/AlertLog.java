package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class AlertLog {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.alarm_id")
    private Long alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.execute_id")
    private Long executeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.way")
    private String way;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.recipient")
    private String recipient;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.in_silence")
    private String inSilence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.send_status")
    private String sendStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.alert_type")
    private String alertType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.alarm_id")
    public Long getAlarmId() {
        return alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.alarm_id")
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.execute_id")
    public Long getExecuteId() {
        return executeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.execute_id")
    public void setExecuteId(Long executeId) {
        this.executeId = executeId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.way")
    public String getWay() {
        return way;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.way")
    public void setWay(String way) {
        this.way = way;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.recipient")
    public String getRecipient() {
        return recipient;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.recipient")
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.in_silence")
    public String getInSilence() {
        return inSilence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.in_silence")
    public void setInSilence(String inSilence) {
        this.inSilence = inSilence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.send_status")
    public String getSendStatus() {
        return sendStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.send_status")
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.alert_type")
    public String getAlertType() {
        return alertType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.alert_type")
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.977+08:00", comments="Source field: alert_log.content")
    public void setContent(String content) {
        this.content = content;
    }
}