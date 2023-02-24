package com.autohome.frostmourne.monitor.model.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.enums.AlertTemplateType;
import com.autohome.frostmourne.monitor.model.enums.MessageWay;

/**
 * 告警消息业务上下文
 *
 * @author Aping
 * @since 2022/3/28 13:21
 */
public class AlarmMessageBO {

    private String title;

    private String content;

    private AlertTemplateType alertTemplateType;

    private Map<String, Object> context;

    private List<AccountInfo> recipients;

    private List<MessageWay> ways;

    private String dingHook;

    private String httpPostEndpoint;

    private String wechatHook;

    private String feiShuHook;

    private String oneMessageHook;

    private List<MessageResult> resultList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AlertTemplateType getAlertTemplateType() {
        return alertTemplateType;
    }

    public void setAlertTemplateType(AlertTemplateType alertTemplateType) {
        this.alertTemplateType = alertTemplateType;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public List<AccountInfo> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<AccountInfo> recipients) {
        this.recipients = recipients;
    }

    public List<MessageWay> getWays() {
        return ways;
    }

    public void setWays(List<MessageWay> ways) {
        this.ways = ways;
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

    public String getFeiShuHook() {
        return feiShuHook;
    }

    public void setFeiShuHook(String feiShuHook) {
        this.feiShuHook = feiShuHook;
    }

    public String getOneMessageHook() {
        return oneMessageHook;
    }

    public void setOneMessageHook(String oneMessageHook) {
        this.oneMessageHook = oneMessageHook;
    }

    public List<MessageResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<MessageResult> resultList) {
        this.resultList = resultList;
    }
}
