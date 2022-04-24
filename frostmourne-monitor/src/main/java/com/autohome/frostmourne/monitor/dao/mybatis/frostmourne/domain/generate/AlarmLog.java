package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * 监控任务执行日志
 *
 * @author mybatis-generator
 */
public class AlarmLog implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控ID
     */
    private Long alarmId;

    /**
     * 监控任务执行开始时间
     */
    private Date exeStart;

    /**
     * 监控任务执行结束时间
     */
    private Date exeEnd;

    /**
     * 监控任务执行耗时，单位：毫秒
     */
    private Integer cost;

    /**
     * 执行结果(SUCCESS,ERROR)
     */
    private String executeResult;

    /**
     * NONE,TRUE,FALSE
     */
    private String verifyResult;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 日志消息
     */
    private String message;

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

    public Date getExeStart() {
        return exeStart;
    }

    public void setExeStart(Date exeStart) {
        this.exeStart = exeStart;
    }

    public Date getExeEnd() {
        return exeEnd;
    }

    public void setExeEnd(Date exeEnd) {
        this.exeEnd = exeEnd;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult == null ? null : executeResult.trim();
    }

    public String getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(String verifyResult) {
        this.verifyResult = verifyResult == null ? null : verifyResult.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
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
        AlarmLog other = (AlarmLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getExeStart() == null ? other.getExeStart() == null : this.getExeStart().equals(other.getExeStart()))
            && (this.getExeEnd() == null ? other.getExeEnd() == null : this.getExeEnd().equals(other.getExeEnd()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getExecuteResult() == null ? other.getExecuteResult() == null : this.getExecuteResult().equals(other.getExecuteResult()))
            && (this.getVerifyResult() == null ? other.getVerifyResult() == null : this.getVerifyResult().equals(other.getVerifyResult()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getExeStart() == null) ? 0 : getExeStart().hashCode());
        result = prime * result + ((getExeEnd() == null) ? 0 : getExeEnd().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getExecuteResult() == null) ? 0 : getExecuteResult().hashCode());
        result = prime * result + ((getVerifyResult() == null) ? 0 : getVerifyResult().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        return result;
    }
}