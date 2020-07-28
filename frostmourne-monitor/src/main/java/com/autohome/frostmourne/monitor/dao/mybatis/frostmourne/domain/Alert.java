package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Alert {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.918+08:00", comments="Source field: alert.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.alarm_id")
    private Long alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.ways")
    private String ways;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.silence")
    private Long silence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.allow_sms_from")
    private Integer allow_sms_from;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.allow_sms_to")
    private Integer allow_sms_to;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.ding_robot_hook")
    private String ding_robot_hook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.http_post_url")
    private String http_post_url;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.wechat_robot_hook")
    private String wechat_robot_hook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.921+08:00", comments="Source field: alert.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.alarm_id")
    public Long getAlarm_id() {
        return alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.alarm_id")
    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.ways")
    public String getWays() {
        return ways;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.ways")
    public void setWays(String ways) {
        this.ways = ways;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.silence")
    public Long getSilence() {
        return silence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.silence")
    public void setSilence(Long silence) {
        this.silence = silence;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.allow_sms_from")
    public Integer getAllow_sms_from() {
        return allow_sms_from;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.922+08:00", comments="Source field: alert.allow_sms_from")
    public void setAllow_sms_from(Integer allow_sms_from) {
        this.allow_sms_from = allow_sms_from;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.allow_sms_to")
    public Integer getAllow_sms_to() {
        return allow_sms_to;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.allow_sms_to")
    public void setAllow_sms_to(Integer allow_sms_to) {
        this.allow_sms_to = allow_sms_to;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.ding_robot_hook")
    public String getDing_robot_hook() {
        return ding_robot_hook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.ding_robot_hook")
    public void setDing_robot_hook(String ding_robot_hook) {
        this.ding_robot_hook = ding_robot_hook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.http_post_url")
    public String getHttp_post_url() {
        return http_post_url;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.http_post_url")
    public void setHttp_post_url(String http_post_url) {
        this.http_post_url = http_post_url;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.wechat_robot_hook")
    public String getWechat_robot_hook() {
        return wechat_robot_hook;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:16:59.923+08:00", comments="Source field: alert.wechat_robot_hook")
    public void setWechat_robot_hook(String wechat_robot_hook) {
        this.wechat_robot_hook = wechat_robot_hook;
    }
}