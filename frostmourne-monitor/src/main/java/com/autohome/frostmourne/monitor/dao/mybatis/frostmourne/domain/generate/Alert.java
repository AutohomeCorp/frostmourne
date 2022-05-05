package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * 报警配置
 *
 * @author mybatis-generator
 */
public class Alert implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控ID
     */
    private Long alarmId;

    /**
     * 报警方式(sms,dingding,email,http_post,wechat)
     */
    private String ways;

    /**
     * 静默时间，单位：分钟
     */
    private Long silence;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 短信允许发送开始时间，[0,23]
     */
    private Integer allowSmsFrom;

    /**
     * 短信允许发送结束时间，[0,23]
     */
    private Integer allowSmsTo;

    /**
     * 钉钉机器人hook地址
     */
    private String dingRobotHook;

    /**
     * http post报警方式地址
     */
    private String httpPostUrl;

    /**
     * 企业微信机器人hook地址
     */
    private String wechatRobotHook;

    /**
     * 飞书机器人hook地址
     */
    private String feishuRobotHook;

    /**
     * 静默判断表达式
     */
    private String silenceExpression;

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

    public String getWays() {
        return ways;
    }

    public void setWays(String ways) {
        this.ways = ways == null ? null : ways.trim();
    }

    public Long getSilence() {
        return silence;
    }

    public void setSilence(Long silence) {
        this.silence = silence;
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

    public Integer getAllowSmsFrom() {
        return allowSmsFrom;
    }

    public void setAllowSmsFrom(Integer allowSmsFrom) {
        this.allowSmsFrom = allowSmsFrom;
    }

    public Integer getAllowSmsTo() {
        return allowSmsTo;
    }

    public void setAllowSmsTo(Integer allowSmsTo) {
        this.allowSmsTo = allowSmsTo;
    }

    public String getDingRobotHook() {
        return dingRobotHook;
    }

    public void setDingRobotHook(String dingRobotHook) {
        this.dingRobotHook = dingRobotHook == null ? null : dingRobotHook.trim();
    }

    public String getHttpPostUrl() {
        return httpPostUrl;
    }

    public void setHttpPostUrl(String httpPostUrl) {
        this.httpPostUrl = httpPostUrl == null ? null : httpPostUrl.trim();
    }

    public String getWechatRobotHook() {
        return wechatRobotHook;
    }

    public void setWechatRobotHook(String wechatRobotHook) {
        this.wechatRobotHook = wechatRobotHook == null ? null : wechatRobotHook.trim();
    }

    public String getFeishuRobotHook() {
        return feishuRobotHook;
    }

    public void setFeishuRobotHook(String feishuRobotHook) {
        this.feishuRobotHook = feishuRobotHook == null ? null : feishuRobotHook.trim();
    }

    public String getSilenceExpression() {
        return silenceExpression;
    }

    public void setSilenceExpression(String silenceExpression) {
        this.silenceExpression = silenceExpression == null ? null : silenceExpression.trim();
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
        Alert other = (Alert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getWays() == null ? other.getWays() == null : this.getWays().equals(other.getWays()))
            && (this.getSilence() == null ? other.getSilence() == null : this.getSilence().equals(other.getSilence()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getAllowSmsFrom() == null ? other.getAllowSmsFrom() == null : this.getAllowSmsFrom().equals(other.getAllowSmsFrom()))
            && (this.getAllowSmsTo() == null ? other.getAllowSmsTo() == null : this.getAllowSmsTo().equals(other.getAllowSmsTo()))
            && (this.getDingRobotHook() == null ? other.getDingRobotHook() == null : this.getDingRobotHook().equals(other.getDingRobotHook()))
            && (this.getHttpPostUrl() == null ? other.getHttpPostUrl() == null : this.getHttpPostUrl().equals(other.getHttpPostUrl()))
            && (this.getWechatRobotHook() == null ? other.getWechatRobotHook() == null : this.getWechatRobotHook().equals(other.getWechatRobotHook()))
            && (this.getFeishuRobotHook() == null ? other.getFeishuRobotHook() == null : this.getFeishuRobotHook().equals(other.getFeishuRobotHook()))
            && (this.getSilenceExpression() == null ? other.getSilenceExpression() == null : this.getSilenceExpression().equals(other.getSilenceExpression()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getWays() == null) ? 0 : getWays().hashCode());
        result = prime * result + ((getSilence() == null) ? 0 : getSilence().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getAllowSmsFrom() == null) ? 0 : getAllowSmsFrom().hashCode());
        result = prime * result + ((getAllowSmsTo() == null) ? 0 : getAllowSmsTo().hashCode());
        result = prime * result + ((getDingRobotHook() == null) ? 0 : getDingRobotHook().hashCode());
        result = prime * result + ((getHttpPostUrl() == null) ? 0 : getHttpPostUrl().hashCode());
        result = prime * result + ((getWechatRobotHook() == null) ? 0 : getWechatRobotHook().hashCode());
        result = prime * result + ((getFeishuRobotHook() == null) ? 0 : getFeishuRobotHook().hashCode());
        result = prime * result + ((getSilenceExpression() == null) ? 0 : getSilenceExpression().hashCode());
        return result;
    }
}