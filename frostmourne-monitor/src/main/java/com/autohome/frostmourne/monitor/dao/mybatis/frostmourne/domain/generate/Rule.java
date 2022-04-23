package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * 报警规则配置
 *
 * @author mybatis-generator
 */
public class Rule implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 规则类型(numeric,percentage,expression)
     */
    private String ruleType;

    /**
     * 报警ID
     */
    private Long alarmId;

    /**
     * 报警内容模板
     */
    private String alertTemplate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 报警消息类型(TEXT,MARKDOWN)
     */
    private String alertTemplateType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlertTemplate() {
        return alertTemplate;
    }

    public void setAlertTemplate(String alertTemplate) {
        this.alertTemplate = alertTemplate == null ? null : alertTemplate.trim();
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

    public String getAlertTemplateType() {
        return alertTemplateType;
    }

    public void setAlertTemplateType(String alertTemplateType) {
        this.alertTemplateType = alertTemplateType == null ? null : alertTemplateType.trim();
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
        Rule other = (Rule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRuleType() == null ? other.getRuleType() == null : this.getRuleType().equals(other.getRuleType()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getAlertTemplate() == null ? other.getAlertTemplate() == null : this.getAlertTemplate().equals(other.getAlertTemplate()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getAlertTemplateType() == null ? other.getAlertTemplateType() == null : this.getAlertTemplateType().equals(other.getAlertTemplateType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRuleType() == null) ? 0 : getRuleType().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getAlertTemplate() == null) ? 0 : getAlertTemplate().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getAlertTemplateType() == null) ? 0 : getAlertTemplateType().hashCode());
        return result;
    }
}