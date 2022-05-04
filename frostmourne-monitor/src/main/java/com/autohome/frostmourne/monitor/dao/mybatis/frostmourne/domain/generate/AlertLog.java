package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import com.autohome.frostmourne.monitor.model.enums.AlertType;
import java.io.Serializable;
import java.util.Date;

/**
 * 报警日志
 *
 * @author mybatis-generator
 */
public class AlertLog implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控ID
     */
    private Long alarmId;

    /**
     * 监控执行ID
     */
    private Long executeId;

    /**
     * 报警方式
     */
    private String way;

    /**
     * 报警接收人
     */
    private String recipient;

    /**
     * 是否在静默期(YES,NO)
     */
    private String inSilence;

    /**
     * 发送状态(NONE,SUCCESS,FAIL,FORBID)
     */
    private String sendStatus;

    /**
     * 消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)
     */
    private AlertType alertType;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 报警内容
     */
    private String content;

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

    public Long getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Long executeId) {
        this.executeId = executeId;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient == null ? null : recipient.trim();
    }

    public String getInSilence() {
        return inSilence;
    }

    public void setInSilence(String inSilence) {
        this.inSilence = inSilence == null ? null : inSilence.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        AlertLog other = (AlertLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getExecuteId() == null ? other.getExecuteId() == null : this.getExecuteId().equals(other.getExecuteId()))
            && (this.getWay() == null ? other.getWay() == null : this.getWay().equals(other.getWay()))
            && (this.getRecipient() == null ? other.getRecipient() == null : this.getRecipient().equals(other.getRecipient()))
            && (this.getInSilence() == null ? other.getInSilence() == null : this.getInSilence().equals(other.getInSilence()))
            && (this.getSendStatus() == null ? other.getSendStatus() == null : this.getSendStatus().equals(other.getSendStatus()))
            && (this.getAlertType() == null ? other.getAlertType() == null : this.getAlertType().equals(other.getAlertType()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getExecuteId() == null) ? 0 : getExecuteId().hashCode());
        result = prime * result + ((getWay() == null) ? 0 : getWay().hashCode());
        result = prime * result + ((getRecipient() == null) ? 0 : getRecipient().hashCode());
        result = prime * result + ((getInSilence() == null) ? 0 : getInSilence().hashCode());
        result = prime * result + ((getSendStatus() == null) ? 0 : getSendStatus().hashCode());
        result = prime * result + ((getAlertType() == null) ? 0 : getAlertType().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}