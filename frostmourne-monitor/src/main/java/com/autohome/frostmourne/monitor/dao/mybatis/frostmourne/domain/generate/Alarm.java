package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * 监控报警
 *
 * @author mybatis-generator
 */
public class Alarm implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控名称
     */
    private String alarmName;

    /**
     * 监控数据类型。(http: http监控；其他值: 关联data_name表)
     */
    private String alarmType;

    /**
     * 监控描述
     */
    private String description;

    /**
     * 所属对象关键字
     */
    private String ownerKey;

    /**
     * 开关状态（OPEN,CLOSE）
     */
    private String status;

    /**
     * 最近一次执行结果
     */
    private String executeResult;

    /**
     * 最近一次执行时间
     */
    private Date executeAt;

    /**
     * 调度任务id
     */
    private Long jobId;

    /**
     * cron表达式
     */
    private String cron;

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

    /**
     * 监控所属团队
     */
    private String teamName;

    /**
     * 风险等级。info: 提示；important: 重要；emergency: 紧急； crash: 我崩了
     */
    private String riskLevel;

    /**
     * 服务ID
     */
    private Long serviceId;

    /**
     * 恢复通知开关（OPEN,CLOSE）
     */
    private String recoverNoticeStatus;

    /**
     * 上次调度时间
     */
    private Long triggerLastTime;

    /**
     * 下次调度时间
     */
    private Long triggerNextTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName == null ? null : alarmName.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey == null ? null : ownerKey.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult == null ? null : executeResult.trim();
    }

    public Date getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Date executeAt) {
        this.executeAt = executeAt;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getRecoverNoticeStatus() {
        return recoverNoticeStatus;
    }

    public void setRecoverNoticeStatus(String recoverNoticeStatus) {
        this.recoverNoticeStatus = recoverNoticeStatus == null ? null : recoverNoticeStatus.trim();
    }

    public Long getTriggerLastTime() {
        return triggerLastTime;
    }

    public void setTriggerLastTime(Long triggerLastTime) {
        this.triggerLastTime = triggerLastTime;
    }

    public Long getTriggerNextTime() {
        return triggerNextTime;
    }

    public void setTriggerNextTime(Long triggerNextTime) {
        this.triggerNextTime = triggerNextTime;
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
        Alarm other = (Alarm) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmName() == null ? other.getAlarmName() == null : this.getAlarmName().equals(other.getAlarmName()))
            && (this.getAlarmType() == null ? other.getAlarmType() == null : this.getAlarmType().equals(other.getAlarmType()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getOwnerKey() == null ? other.getOwnerKey() == null : this.getOwnerKey().equals(other.getOwnerKey()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExecuteResult() == null ? other.getExecuteResult() == null : this.getExecuteResult().equals(other.getExecuteResult()))
            && (this.getExecuteAt() == null ? other.getExecuteAt() == null : this.getExecuteAt().equals(other.getExecuteAt()))
            && (this.getJobId() == null ? other.getJobId() == null : this.getJobId().equals(other.getJobId()))
            && (this.getCron() == null ? other.getCron() == null : this.getCron().equals(other.getCron()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getModifyAt() == null ? other.getModifyAt() == null : this.getModifyAt().equals(other.getModifyAt()))
            && (this.getTeamName() == null ? other.getTeamName() == null : this.getTeamName().equals(other.getTeamName()))
            && (this.getRiskLevel() == null ? other.getRiskLevel() == null : this.getRiskLevel().equals(other.getRiskLevel()))
            && (this.getServiceId() == null ? other.getServiceId() == null : this.getServiceId().equals(other.getServiceId()))
            && (this.getRecoverNoticeStatus() == null ? other.getRecoverNoticeStatus() == null : this.getRecoverNoticeStatus().equals(other.getRecoverNoticeStatus()))
            && (this.getTriggerLastTime() == null ? other.getTriggerLastTime() == null : this.getTriggerLastTime().equals(other.getTriggerLastTime()))
            && (this.getTriggerNextTime() == null ? other.getTriggerNextTime() == null : this.getTriggerNextTime().equals(other.getTriggerNextTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmName() == null) ? 0 : getAlarmName().hashCode());
        result = prime * result + ((getAlarmType() == null) ? 0 : getAlarmType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getOwnerKey() == null) ? 0 : getOwnerKey().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExecuteResult() == null) ? 0 : getExecuteResult().hashCode());
        result = prime * result + ((getExecuteAt() == null) ? 0 : getExecuteAt().hashCode());
        result = prime * result + ((getJobId() == null) ? 0 : getJobId().hashCode());
        result = prime * result + ((getCron() == null) ? 0 : getCron().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getModifyAt() == null) ? 0 : getModifyAt().hashCode());
        result = prime * result + ((getTeamName() == null) ? 0 : getTeamName().hashCode());
        result = prime * result + ((getRiskLevel() == null) ? 0 : getRiskLevel().hashCode());
        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());
        result = prime * result + ((getRecoverNoticeStatus() == null) ? 0 : getRecoverNoticeStatus().hashCode());
        result = prime * result + ((getTriggerLastTime() == null) ? 0 : getTriggerLastTime().hashCode());
        result = prime * result + ((getTriggerNextTime() == null) ? 0 : getTriggerNextTime().hashCode());
        return result;
    }
}