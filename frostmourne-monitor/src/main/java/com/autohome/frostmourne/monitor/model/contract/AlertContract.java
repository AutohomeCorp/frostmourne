package com.autohome.frostmourne.monitor.model.contract;

import java.util.Date;
import java.util.List;

public class AlertContract {

    private Long alarm_id;

    private List<String> ways;

    private Long silence;

    private String silenceExpression;

    private String creator;

    private Date createAt;

    private Integer allowSmsFrom;

    private Integer allowSmsTo;

    private String dingRobotHook;

    private String httpPostUrl;

    private String wechatRobotHook;

    private String feishuRobotHook;

    private String oneMessageRobotHook;

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

    public String getSilenceExpression() {
        return silenceExpression;
    }

    public void setSilenceExpression(String silenceExpression) {
        this.silenceExpression = silenceExpression;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getFeishuRobotHook() {
        return feishuRobotHook;
    }

    public void setFeishuRobotHook(String feishuRobotHook) {
        this.feishuRobotHook = feishuRobotHook;
    }

    public String getOneMessageRobotHook() {
        return oneMessageRobotHook;
    }

    public void setOneMessageRobotHook(String oneMessageRobotHook) {
        this.oneMessageRobotHook = oneMessageRobotHook;
    }
}
