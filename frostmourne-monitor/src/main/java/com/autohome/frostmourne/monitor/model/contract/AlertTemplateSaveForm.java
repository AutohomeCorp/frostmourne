package com.autohome.frostmourne.monitor.model.contract;

import com.autohome.frostmourne.monitor.controller.annotation.constraints.TemplateUnicodeAnnotation;
import com.autohome.frostmourne.monitor.model.enums.TemplateType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@TemplateUnicodeAnnotation(message = "模板类型:关联码不能为空且长度不超过200")
public class AlertTemplateSaveForm {

    @NotNull(message = "非空")
    private Long id;

    @NotEmpty(message = "模板名称非空")
    @Size(min = 3, max = 50, message = "模板名称长度介于3~50之间")
    private String templateName;

    @NotNull(message = "模板类型不能为空")
    private TemplateType templateType;

    private String templateUnionCode;

    @NotEmpty(message = "模板内容不能为空")
    @Size(min = 5, max = 5000, message = "模板内容长度介于5~5000之间")
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
