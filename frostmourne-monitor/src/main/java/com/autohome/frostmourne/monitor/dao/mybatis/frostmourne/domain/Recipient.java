package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Recipient {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.alarm_id")
    private Long alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.alert_id")
    private Long alert_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.account")
    private String account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.alarm_id")
    public Long getAlarm_id() {
        return alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.alarm_id")
    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.alert_id")
    public Long getAlert_id() {
        return alert_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.alert_id")
    public void setAlert_id(Long alert_id) {
        this.alert_id = alert_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.account")
    public String getAccount() {
        return account;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.account")
    public void setAccount(String account) {
        this.account = account;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source field: recipient.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}