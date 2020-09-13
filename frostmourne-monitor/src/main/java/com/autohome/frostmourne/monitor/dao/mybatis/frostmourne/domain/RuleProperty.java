package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class RuleProperty {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.alarm_id")
    private Long alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.rule_id")
    private Long ruleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.prop_key")
    private String propKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.prop_value")
    private String propValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.alarm_id")
    public Long getAlarmId() {
        return alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.alarm_id")
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.rule_id")
    public Long getRuleId() {
        return ruleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.rule_id")
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.prop_key")
    public String getPropKey() {
        return propKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.999+08:00", comments="Source field: rule_property.prop_key")
    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.prop_value")
    public String getPropValue() {
        return propValue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.prop_value")
    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source field: rule_property.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}