package com.autohome.frostmourne.monitor.contract;

import java.util.Date;

public class AlarmContract {

    private Long id;

    private String alarm_name;

    private String alarm_type;

    private String description;

    private String owner_key;

    private String status;

    private String execute_result;

    private Date execute_at;

    private Long job_id;

    private String cron;

    private String operator;

    private String team_name;

    private String risk_level;

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

    public String getAlarm_name() {
        return alarm_name;
    }

    public void setAlarm_name(String alarm_name) {
        this.alarm_name = alarm_name;
    }

    public String getAlarm_type() {
        return alarm_type;
    }

    public void setAlarm_type(String alarm_type) {
        this.alarm_type = alarm_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner_key() {
        return owner_key;
    }

    public void setOwner_key(String owner_key) {
        this.owner_key = owner_key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecute_result() {
        return execute_result;
    }

    public void setExecute_result(String execute_result) {
        this.execute_result = execute_result;
    }

    public Date getExecute_at() {
        return execute_at;
    }

    public void setExecute_at(Date execute_at) {
        this.execute_at = execute_at;
    }

    public Long getJobId() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public AlertContract getAlertContract() {
        return alertContract;
    }

    public void setAlertContract(AlertContract alertContract) {
        this.alertContract = alertContract;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public ServiceInfoSimpleContract getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfoSimpleContract serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }
}
