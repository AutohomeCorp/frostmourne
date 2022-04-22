package com.autohome.frostmourne.monitor.model.contract;

import com.autohome.frostmourne.monitor.model.enums.AlertTemplateEnums.TemplateType;

public class AlertTemplateSaveForm {

    private Long id;

    private String templateName;

    private TemplateType templateType;

    private String templateUnionCode;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
