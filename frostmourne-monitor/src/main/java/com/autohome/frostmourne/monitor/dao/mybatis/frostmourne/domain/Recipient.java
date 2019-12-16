package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class Recipient {
    private Long id;

    private Long alarm_id;

    private Long alert_id;

    private String account;

    private Date create_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    public Long getAlert_id() {
        return alert_id;
    }

    public void setAlert_id(Long alert_id) {
        this.alert_id = alert_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}