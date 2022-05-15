package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import com.autohome.frostmourne.monitor.model.enums.AlertType;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 报警事件
 *
 * @author mybatis-generator
 */
public class AlertEvent implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控ID
     */
    private Long alarmId;

    /**
     * 消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)
     */
    private AlertType alertType;

    /**
     * 是否在静默期
     */
    private Boolean inSilence;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 报警升级
     */
    private Boolean upgrade;

    /**
     * 摘要md5
     */
    private String eventMd5;

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

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    public Boolean getInSilence() {
        return inSilence;
    }

    public void setInSilence(Boolean inSilence) {
        this.inSilence = inSilence;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Boolean getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Boolean upgrade) {
        this.upgrade = upgrade;
    }

    public String getEventMd5() {
        return eventMd5;
    }

    public void setEventMd5(String eventMd5) {
        this.eventMd5 = eventMd5 == null ? null : eventMd5.trim();
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
        AlertEvent other = (AlertEvent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getAlertType() == null ? other.getAlertType() == null : this.getAlertType().equals(other.getAlertType()))
            && (this.getInSilence() == null ? other.getInSilence() == null : this.getInSilence().equals(other.getInSilence()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getUpgrade() == null ? other.getUpgrade() == null : this.getUpgrade().equals(other.getUpgrade()))
            && (this.getEventMd5() == null ? other.getEventMd5() == null : this.getEventMd5().equals(other.getEventMd5()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getAlertType() == null) ? 0 : getAlertType().hashCode());
        result = prime * result + ((getInSilence() == null) ? 0 : getInSilence().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpgrade() == null) ? 0 : getUpgrade().hashCode());
        result = prime * result + ((getEventMd5() == null) ? 0 : getEventMd5().hashCode());
        return result;
    }
}