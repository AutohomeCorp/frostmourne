package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import com.autohome.frostmourne.monitor.model.enums.AlarmUpgradeStatus;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 报警升级配置
 *
 * @author mybatis-generator
 */
public class AlertUpgrade implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 监控ID
     */
    private Long alarmId;

    /**
     * 状态开关 OPEN:打开  CLOSE:关闭
     */
    private AlarmUpgradeStatus status;

    /**
     * 连续报警n次后升级
     */
    private Integer timesToUpgrade;

    /**
     * 报警方式(sms,dingding,email,http_post,wechat)
     */
    private String ways;

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
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * OneMessage机器人hook地址
     */
    private String oneMessageRobotHook;

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

    public AlarmUpgradeStatus getStatus() {
        return status;
    }

    public void setStatus(AlarmUpgradeStatus status) {
        this.status = status;
    }

    public Integer getTimesToUpgrade() {
        return timesToUpgrade;
    }

    public void setTimesToUpgrade(Integer timesToUpgrade) {
        this.timesToUpgrade = timesToUpgrade;
    }

    public String getWays() {
        return ways;
    }

    public void setWays(String ways) {
        this.ways = ways;
    }

    public String getDingRobotHook() {
        return dingRobotHook;
    }

    public void setDingRobotHook(String dingRobotHook) {
        this.dingRobotHook = dingRobotHook;
    }

    public String getHttpPostUrl() {
        return httpPostUrl;
    }

    public void setHttpPostUrl(String httpPostUrl) {
        this.httpPostUrl = httpPostUrl;
    }

    public String getWechatRobotHook() {
        return wechatRobotHook;
    }

    public void setWechatRobotHook(String wechatRobotHook) {
        this.wechatRobotHook = wechatRobotHook;
    }

    public String getFeishuRobotHook() {
        return feishuRobotHook;
    }

    public void setFeishuRobotHook(String feishuRobotHook) {
        this.feishuRobotHook = feishuRobotHook;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getOneMessageRobotHook() {
        return oneMessageRobotHook;
    }

    public void setOneMessageRobotHook(String oneMessageRobotHook) {
        this.oneMessageRobotHook = oneMessageRobotHook;
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
        AlertUpgrade other = (AlertUpgrade) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTimesToUpgrade() == null ? other.getTimesToUpgrade() == null : this.getTimesToUpgrade().equals(other.getTimesToUpgrade()))
            && (this.getWays() == null ? other.getWays() == null : this.getWays().equals(other.getWays()))
            && (this.getDingRobotHook() == null ? other.getDingRobotHook() == null : this.getDingRobotHook().equals(other.getDingRobotHook()))
            && (this.getHttpPostUrl() == null ? other.getHttpPostUrl() == null : this.getHttpPostUrl().equals(other.getHttpPostUrl()))
            && (this.getWechatRobotHook() == null ? other.getWechatRobotHook() == null : this.getWechatRobotHook().equals(other.getWechatRobotHook()))
            && (this.getFeishuRobotHook() == null ? other.getFeishuRobotHook() == null : this.getFeishuRobotHook().equals(other.getFeishuRobotHook()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getOneMessageRobotHook() == null ? other.getOneMessageRobotHook() == null : this.getOneMessageRobotHook().equals(other.getOneMessageRobotHook()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTimesToUpgrade() == null) ? 0 : getTimesToUpgrade().hashCode());
        result = prime * result + ((getWays() == null) ? 0 : getWays().hashCode());
        result = prime * result + ((getDingRobotHook() == null) ? 0 : getDingRobotHook().hashCode());
        result = prime * result + ((getHttpPostUrl() == null) ? 0 : getHttpPostUrl().hashCode());
        result = prime * result + ((getWechatRobotHook() == null) ? 0 : getWechatRobotHook().hashCode());
        result = prime * result + ((getFeishuRobotHook() == null) ? 0 : getFeishuRobotHook().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getOneMessageRobotHook() == null) ? 0 : getOneMessageRobotHook().hashCode());
        return result;
    }
}