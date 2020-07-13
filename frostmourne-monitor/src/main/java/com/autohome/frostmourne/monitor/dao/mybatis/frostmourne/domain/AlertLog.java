package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class AlertLog {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.alarm_id")
    private Long alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.execute_id")
    private Long execute_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.way")
    private String way;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.recipient")
    private String recipient;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.in_silence")
    private String in_silence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.send_status")
    private String send_status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.alert_type")
    private String alert_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.alarm_id")
    public Long getAlarm_id() {
        return alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.alarm_id")
    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.execute_id")
    public Long getExecute_id() {
        return execute_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.execute_id")
    public void setExecute_id(Long execute_id) {
        this.execute_id = execute_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.way")
    public String getWay() {
        return way;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source field: alert_log.way")
    public void setWay(String way) {
        this.way = way;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.recipient")
    public String getRecipient() {
        return recipient;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.recipient")
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.in_silence")
    public String getIn_silence() {
        return in_silence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.in_silence")
    public void setIn_silence(String in_silence) {
        this.in_silence = in_silence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.send_status")
    public String getSend_status() {
        return send_status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.send_status")
    public void setSend_status(String send_status) {
        this.send_status = send_status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.alert_type")
    public String getAlert_type() {
        return alert_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.alert_type")
    public void setAlert_type(String alert_type) {
        this.alert_type = alert_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.787+08:00", comments="Source field: alert_log.content")
    public void setContent(String content) {
        this.content = content;
    }
}