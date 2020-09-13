package com.autohome.frostmourne.monitor.contract;

import java.util.Map;

public class MetricContract {

    private String metricType;

    private String aggregationType;

    private String aggregationField;

    private Long alarmId;

    private Long ruleId;

    private Long dataSourceId;

    private Long dataNameId;

    private String dataName;

    private Map<String, Object> properties;

    private String queryString;

    private String postData;

    private DataSourceContract dataSourceContract;

    private DataNameContract dataNameContract;

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    public String getAggregationField() {
        return aggregationField;
    }

    public void setAggregationField(String aggregationField) {
        this.aggregationField = aggregationField;
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Long getDataNameId() {
        return dataNameId;
    }

    public void setDataNameId(Long dataNameId) {
        this.dataNameId = dataNameId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public DataSourceContract getDataSourceContract() {
        return dataSourceContract;
    }

    public void setDataSourceContract(DataSourceContract dataSourceContract) {
        this.dataSourceContract = dataSourceContract;
    }

    public DataNameContract getDataNameContract() {
        return dataNameContract;
    }

    public void setDataNameContract(DataNameContract dataNameContract) {
        this.dataNameContract = dataNameContract;
    }
}
