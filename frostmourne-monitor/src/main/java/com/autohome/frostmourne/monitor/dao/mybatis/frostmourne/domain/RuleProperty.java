package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class RuleProperty {
    private Long id;

    private Long alarm_id;

    private Long rule_id;

    private String prop_key;

    private String prop_value;

    private String creator;

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

    public Long getRule_id() {
        return rule_id;
    }

    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

    public String getProp_key() {
        return prop_key;
    }

    public void setProp_key(String prop_key) {
        this.prop_key = prop_key == null ? null : prop_key.trim();
    }

    public String getProp_value() {
        return prop_value;
    }

    public void setProp_value(String prop_value) {
        this.prop_value = prop_value == null ? null : prop_value.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}