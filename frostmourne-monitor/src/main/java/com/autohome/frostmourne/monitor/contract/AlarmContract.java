package com.autohome.frostmourne.monitor.contract;

import java.util.Date;

public class AlarmContract {

    private Long id;

    private String alarmName;

    private String alarmType;

    private String description;

    private String ownerKey;

    private String status;

    private String executeResult;

    private Date executeAt;

    private Long jobId;

    private String cron;

    private String operator;

    private String teamName;

    private String riskLevel;

    private String recoverNoticeStatus;

    private MetricContract metricContract;

    private RuleContract ruleContract;

    private AlertContract alertContract;

    private ServiceInfoSimpleContract serviceInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    public Date getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Date executeAt) {
        this.executeAt = executeAt;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRecoverNoticeStatus() {
        return recoverNoticeStatus;
    }

    public void setRecoverNoticeStatus(String recoverNoticeStatus) {
        this.recoverNoticeStatus = recoverNoticeStatus;
    }

    public MetricContract getMetricContract() {
        return metricContract;
    }

    public void setMetricContract(MetricContract metricContract) {
        this.metricContract = metricContract;
    }

    public RuleContract getRuleContract() {
        return ruleContract;
    }

    public void setRuleContract(RuleContract ruleContract) {
        this.ruleContract = ruleContract;
    }

    public AlertContract getAlertContract() {
        return alertContract;
    }

    public void setAlertContract(AlertContract alertContract) {
        this.alertContract = alertContract;
    }

    public ServiceInfoSimpleContract getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfoSimpleContract serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
