package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class AlertTemplate {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_name")
    private String templateName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_type")
    private String templateType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_union_code")
    private String templateUnionCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.content")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modifier")
    private String modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modify_at")
    private Date modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_name")
    public String getTemplateName() {
        return templateName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_name")
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_type")
    public String getTemplateType() {
        return templateType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_type")
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_union_code")
    public String getTemplateUnionCode() {
        return templateUnionCode;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_union_code")
    public void setTemplateUnionCode(String templateUnionCode) {
        this.templateUnionCode = templateUnionCode;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.content")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.content")
    public void setContent(String content) {
        this.content = content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modifier")
    public String getModifier() {
        return modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modifier")
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modify_at")
    public Date getModifyAt() {
        return modifyAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modify_at")
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}