package com.autohome.frostmourne.monitor.model.contract;

import java.util.List;

import com.autohome.frostmourne.monitor.model.enums.AlarmUpgradeStatus;

/**
 * alert upgrade
 *
 * @author Aping
 * @since 2022/5/14 19:38
 */
public class AlertUpgradeContract {

    /**
     * 状态开关 OPEN:打开 CLOSE:关闭
     */
    private AlarmUpgradeStatus status;

    /**
     * 连续报警n次后升级
     */
    private Integer timesToUpgrade;

    /**
     * 报警方式(sms,dingding,email,http_post,wechat)
     */
    private List<String> ways;

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

    private List<String> recipients;

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

    public List<String> getWays() {
        return ways;
    }

    public void setWays(List<String> ways) {
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

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
}
