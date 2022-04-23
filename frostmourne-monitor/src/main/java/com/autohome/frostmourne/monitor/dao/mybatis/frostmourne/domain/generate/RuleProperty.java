package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * 报警规则属性
 *
 * @author mybatis-generator
 */
public class RuleProperty implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控Id
     */
    private Long alarmId;

    /**
     * 报警规则ID
     */
    private Long ruleId;

    /**
     * 属性key
     */
    private String propKey;

    /**
     * 属性value
     */
    private String propValue;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getPropKey() {
        return propKey;
    }

    public void setPropKey(String propKey) {
        this.propKey = propKey == null ? null : propKey.trim();
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue == null ? null : propValue.trim();
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
        RuleProperty other = (RuleProperty) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getRuleId() == null ? other.getRuleId() == null : this.getRuleId().equals(other.getRuleId()))
            && (this.getPropKey() == null ? other.getPropKey() == null : this.getPropKey().equals(other.getPropKey()))
            && (this.getPropValue() == null ? other.getPropValue() == null : this.getPropValue().equals(other.getPropValue()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getRuleId() == null) ? 0 : getRuleId().hashCode());
        result = prime * result + ((getPropKey() == null) ? 0 : getPropKey().hashCode());
        result = prime * result + ((getPropValue() == null) ? 0 : getPropValue().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        return result;
    }
}