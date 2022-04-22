package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import com.autohome.frostmourne.monitor.model.enums.AlertTemplateType;

import java.util.Date;
import javax.annotation.Generated;

public class Rule {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.rule_type")
    private String ruleType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alarm_id")
    private Long alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alert_template_type")
    private AlertTemplateType alertTemplateType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alert_template")
    private String alertTemplate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.rule_type")
    public String getRuleType() {
        return ruleType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.rule_type")
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alarm_id")
    public Long getAlarmId() {
        return alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alarm_id")
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alert_template_type")
    public AlertTemplateType getAlertTemplateType() {
        return alertTemplateType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alert_template_type")
    public void setAlertTemplateType(AlertTemplateType alertTemplateType) {
        this.alertTemplateType = alertTemplateType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alert_template")
    public String getAlertTemplate() {
        return alertTemplate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.alert_template")
    public void setAlertTemplate(String alertTemplate) {
        this.alertTemplate = alertTemplate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.997+08:00", comments="Source field: rule.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}