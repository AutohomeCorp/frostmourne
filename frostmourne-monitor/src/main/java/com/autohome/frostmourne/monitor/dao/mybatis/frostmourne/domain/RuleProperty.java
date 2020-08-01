package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class RuleProperty {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.alarm_id")
    private Long alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.rule_id")
    private Long rule_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.prop_key")
    private String prop_key;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.prop_value")
    private String prop_value;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.alarm_id")
    public Long getAlarm_id() {
        return alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.alarm_id")
    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.rule_id")
    public Long getRule_id() {
        return rule_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.rule_id")
    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.prop_key")
    public String getProp_key() {
        return prop_key;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.prop_key")
    public void setProp_key(String prop_key) {
        this.prop_key = prop_key;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.334+08:00", comments="Source field: rule_property.prop_value")
    public String getProp_value() {
        return prop_value;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.prop_value")
    public void setProp_value(String prop_value) {
        this.prop_value = prop_value;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.335+08:00", comments="Source field: rule_property.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}