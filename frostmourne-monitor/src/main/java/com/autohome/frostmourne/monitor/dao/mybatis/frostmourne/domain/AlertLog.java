package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class AlertLog {
    private Long id;

    private Long alarm_id;

    private Long execute_id;

    private String way;

    private String recipient;

    private String in_silence;

    private String send_status;

    private Date create_at;

    private String alert_type;

    private String content;

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

    public Long getExecute_id() {
        return execute_id;
    }

    public void setExecute_id(Long execute_id) {
        this.execute_id = execute_id;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getIn_silence() {
        return in_silence;
    }

    public void setIn_silence(String in_silence) {
        this.in_silence = in_silence == null ? null : in_silence.trim();
    }

    public String getSend_status() {
        return send_status;
    }

    public void setSend_status(String send_status) {
        this.send_status = send_status == null ? null : send_status.trim();
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getAlert_type() {
        return alert_type;
    }

    public void setAlert_type(String alert_type) {
        this.alert_type = alert_type == null ? null : alert_type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}