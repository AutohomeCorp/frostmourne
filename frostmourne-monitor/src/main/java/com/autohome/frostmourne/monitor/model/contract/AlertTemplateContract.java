package com.autohome.frostmourne.monitor.model.contract;

import java.util.Date;
import java.util.List;

import com.autohome.frostmourne.monitor.model.enums.TemplateType;

public class AlertTemplateContract {

    private Long id;

    private String templateName;

    private TemplateType templateType;

    private String templateUnionCode;

    private List<String> templateTypeTreeValues;

    private List<String> templateTypeTreeLabels;

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

    public TemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
    }

    public String getTemplateUnionCode() {
        return templateUnionCode;
    }

    public void setTemplateUnionCode(String templateUnionCode) {
        this.templateUnionCode = templateUnionCode;
    }

    public List<String> getTemplateTypeTreeValues() {
        return templateTypeTreeValues;
    }

    public void setTemplateTypeTreeValues(List<String> templateTypeTreeValues) {
        this.templateTypeTreeValues = templateTypeTreeValues;
    }

    public List<String> getTemplateTypeTreeLabels() {
        return templateTypeTreeLabels;
    }

    public void setTemplateTypeTreeLabels(List<String> templateTypeTreeLabels) {
        this.templateTypeTreeLabels = templateTypeTreeLabels;
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
