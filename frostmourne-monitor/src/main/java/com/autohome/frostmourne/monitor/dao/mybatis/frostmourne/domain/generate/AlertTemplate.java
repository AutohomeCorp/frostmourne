package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * 报警模板
 *
 * @author mybatis-generator
 */
public class AlertTemplate implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 模板类型关联code，根据不同模板类型关联不同的源
     */
    private String templateUnionCode;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyAt;

    private static final long serialVersionUID = 1L;

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
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType == null ? null : templateType.trim();
    }

    public String getTemplateUnionCode() {
        return templateUnionCode;
    }

    public void setTemplateUnionCode(String templateUnionCode) {
        this.templateUnionCode = templateUnionCode == null ? null : templateUnionCode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AlertTemplate other = (AlertTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTemplateName() == null ? other.getTemplateName() == null : this.getTemplateName().equals(other.getTemplateName()))
            && (this.getTemplateType() == null ? other.getTemplateType() == null : this.getTemplateType().equals(other.getTemplateType()))
            && (this.getTemplateUnionCode() == null ? other.getTemplateUnionCode() == null : this.getTemplateUnionCode().equals(other.getTemplateUnionCode()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyAt() == null ? other.getModifyAt() == null : this.getModifyAt().equals(other.getModifyAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTemplateName() == null) ? 0 : getTemplateName().hashCode());
        result = prime * result + ((getTemplateType() == null) ? 0 : getTemplateType().hashCode());
        result = prime * result + ((getTemplateUnionCode() == null) ? 0 : getTemplateUnionCode().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyAt() == null) ? 0 : getModifyAt().hashCode());
        return result;
    }
}