package com.autohome.frostmourne.monitor.model.contract;

import com.autohome.frostmourne.monitor.model.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.model.enums.RecoverNoticeStatus;
import com.autohome.frostmourne.monitor.model.enums.RiskLevel;

import java.util.Date;

public class AlarmContract {

    private Long id;

    private String alarmName;

    private String alarmType;

    private String description;

    private String ownerKey;

    private AlarmStatus status;

    private ExecuteStatus executeResult;

    private Date executeAt;

    private Long jobId;

    private String cron;

    private String operator;

    private String teamName;

    private RiskLevel riskLevel;

    private RecoverNoticeStatus recoverNoticeStatus;

    private MetricContract metricContract;

    private RuleContract ruleContract;

    private AlertContract alertContract;

    private ServiceInfoSimpleContract serviceInfo;

    private AlertUpgradeContract alertUpgradeContract;

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

    public AlarmStatus getStatus() {
        return status;
    }

    public void setStatus(AlarmStatus status) {
        this.status = status;
    }

    public ExecuteStatus getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(ExecuteStatus executeResult) {
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

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public RecoverNoticeStatus getRecoverNoticeStatus() {
        return recoverNoticeStatus;
    }

    public void setRecoverNoticeStatus(RecoverNoticeStatus recoverNoticeStatus) {
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

    public AlertUpgradeContract getAlertUpgradeContract() {
        return alertUpgradeContract;
    }

    public void setAlertUpgradeContract(AlertUpgradeContract alertUpgradeContract) {
        this.alertUpgradeContract = alertUpgradeContract;
    }
}
