package com.autohome.frostmourne.monitor.contract;

import java.util.Date;
import java.util.List;

public class AlertContract {

    private Long alarm_id;

    private List<String> ways;

    private Long silence;

    private String creator;

    private Date create_at;

    private Integer allow_sms_from;

    private Integer allow_sms_to;

    private String ding_robot_hook;

    private String http_post_url;

    private String wechat_robot_hook;

    private List<String> recipients;

    public Long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    public List<String> getWays() {
        return ways;
    }

    public void setWays(List<String> ways) {
        this.ways = ways;
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
        this.creator = creator;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Integer getAllow_sms_from() {
        return allow_sms_from;
    }

    public void setAllow_sms_from(Integer allow_sms_from) {
        this.allow_sms_from = allow_sms_from;
    }

    public Integer getAllow_sms_to() {
        return allow_sms_to;
    }

    public void setAllow_sms_to(Integer allow_sms_to) {
        this.allow_sms_to = allow_sms_to;
    }

    public String getDing_robot_hook() {
        return ding_robot_hook;
    }

    public void setDing_robot_hook(String ding_robot_hook) {
        this.ding_robot_hook = ding_robot_hook;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getHttp_post_url() {
        return http_post_url;
    }

    public void setHttp_post_url(String http_post_url) {
        this.http_post_url = http_post_url;
    }

    public String getWechat_robot_hook() {
        return wechat_robot_hook;
    }

    public void setWechat_robot_hook(String wechat_robot_hook) {
        this.wechat_robot_hook = wechat_robot_hook;
    }
}
