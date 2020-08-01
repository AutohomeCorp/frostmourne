package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class AlertTemplate {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.597+08:00", comments="Source field: alert_template.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_name")
    private String template_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_type")
    private String template_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_union_code")
    private String template_union_code;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.602+08:00", comments="Source field: alert_template.modify_at")
    private Date modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.6+08:00", comments="Source field: alert_template.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_name")
    public String getTemplate_name() {
        return template_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_name")
    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_type")
    public String getTemplate_type() {
        return template_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_type")
    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_union_code")
    public String getTemplate_union_code() {
        return template_union_code;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.template_union_code")
    public void setTemplate_union_code(String template_union_code) {
        this.template_union_code = template_union_code;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.content")
    public void setContent(String content) {
        this.content = content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.601+08:00", comments="Source field: alert_template.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.602+08:00", comments="Source field: alert_template.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.602+08:00", comments="Source field: alert_template.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.602+08:00", comments="Source field: alert_template.modify_at")
    public Date getModify_at() {
        return modify_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.602+08:00", comments="Source field: alert_template.modify_at")
    public void setModify_at(Date modify_at) {
        this.modify_at = modify_at;
    }
}