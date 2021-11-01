package com.autohome.frostmourne.spi.starter.model;

import java.util.List;
import java.util.Map;

public class AlarmMessage {

    private String title;

    private String content;

    private Map<String, Object> context;

    private List<AccountInfo> recipients;

    private List<String> ways;

    private String dingHook;

    private String httpPostEndpoint;

    private String wechatHook;

    private String feiShuHook;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AccountInfo> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<AccountInfo> recipients) {
        this.recipients = recipients;
    }

    public List<String> getWays() {
        return ways;
    }

    public void setWays(List<String> ways) {
        this.ways = ways;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDingHook() {
        return dingHook;
    }

    public void setDingHook(String dingHook) {
        this.dingHook = dingHook;
    }

    public String getHttpPostEndpoint() {
        return httpPostEndpoint;
    }

    public void setHttpPostEndpoint(String httpPostEndpoint) {
        this.httpPostEndpoint = httpPostEndpoint;
    }

    public String getWechatHook() {
        return wechatHook;
    }

    public void setWechatHook(String wechatHook) {
        this.wechatHook = wechatHook;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public String getFeiShuHook() {
        return feiShuHook;
    }

    public void setFeiShuHook(String feiShuHook) {
        this.feiShuHook = feiShuHook;
    }
}
