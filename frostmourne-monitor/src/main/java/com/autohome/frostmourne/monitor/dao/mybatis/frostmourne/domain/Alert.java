package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Alert {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.458+08:00", comments="Source field: alert.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.alarm_id")
    private Long alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.ways")
    private String ways;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.silence")
    private Long silence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.allow_sms_from")
    private Integer allowSmsFrom;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.allow_sms_to")
    private Integer allowSmsTo;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.ding_robot_hook")
    private String dingRobotHook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.http_post_url")
    private String httpPostUrl;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.wechat_robot_hook")
    private String wechatRobotHook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.feishu_robot_hook")
    private String feishuRobotHook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.461+08:00", comments="Source field: alert.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.alarm_id")
    public Long getAlarmId() {
        return alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.alarm_id")
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.ways")
    public String getWays() {
        return ways;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.ways")
    public void setWays(String ways) {
        this.ways = ways;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.silence")
    public Long getSilence() {
        return silence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.silence")
    public void setSilence(Long silence) {
        this.silence = silence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.allow_sms_from")
    public Integer getAllowSmsFrom() {
        return allowSmsFrom;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.allow_sms_from")
    public void setAllowSmsFrom(Integer allowSmsFrom) {
        this.allowSmsFrom = allowSmsFrom;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.allow_sms_to")
    public Integer getAllowSmsTo() {
        return allowSmsTo;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.462+08:00", comments="Source field: alert.allow_sms_to")
    public void setAllowSmsTo(Integer allowSmsTo) {
        this.allowSmsTo = allowSmsTo;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.ding_robot_hook")
    public String getDingRobotHook() {
        return dingRobotHook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.ding_robot_hook")
    public void setDingRobotHook(String dingRobotHook) {
        this.dingRobotHook = dingRobotHook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.http_post_url")
    public String getHttpPostUrl() {
        return httpPostUrl;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.http_post_url")
    public void setHttpPostUrl(String httpPostUrl) {
        this.httpPostUrl = httpPostUrl;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.wechat_robot_hook")
    public String getWechatRobotHook() {
        return wechatRobotHook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.wechat_robot_hook")
    public void setWechatRobotHook(String wechatRobotHook) {
        this.wechatRobotHook = wechatRobotHook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.feishu_robot_hook")
    public String getFeishuRobotHook() {
        return feishuRobotHook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.463+08:00", comments="Source field: alert.feishu_robot_hook")
    public void setFeishuRobotHook(String feishuRobotHook) {
        this.feishuRobotHook = feishuRobotHook;
    }
}