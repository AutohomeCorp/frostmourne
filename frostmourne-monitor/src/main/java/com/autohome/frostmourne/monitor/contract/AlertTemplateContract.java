package com.autohome.frostmourne.monitor.contract;

import java.util.Date;

public class AlertTemplateContract {

    private Long id;

    private String templateName;

    private String templateType;

    private String templateUnionCode;

    private String templateUnionName;

    private String content;

    private String creator;

    private Date createAt;

    private String modifier;

    private Date modifyAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateUnionCode() {
        return templateUnionCode;
    }

    public void setTemplateUnionCode(String templateUnionCode) {
        this.templateUnionCode = templateUnionCode;
    }

    public String getTemplateUnionName() {
        return templateUnionName;
    }

    public void setTemplateUnionName(String templateUnionName) {
        this.templateUnionName = templateUnionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}
