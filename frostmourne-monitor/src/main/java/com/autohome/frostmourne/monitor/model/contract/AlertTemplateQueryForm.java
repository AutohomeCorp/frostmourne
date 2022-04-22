package com.autohome.frostmourne.monitor.model.contract;

import java.util.List;

public class AlertTemplateQueryForm {

    private String templateName;

    private String templateType;

    /**
     * 模板类型和关联码组合，templateType|templateUnionCode
     */
    private List<String> templateTypeUnionCodes;

    private Integer pageIndex = 1;

    private Integer pageSize = 20;

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

    public List<String> getTemplateTypeUnionCodes() {
        return templateTypeUnionCodes;
    }

    public void setTemplateTypeUnionCodes(List<String> templateTypeUnionCodes) {
        this.templateTypeUnionCodes = templateTypeUnionCodes;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
