package com.autohome.frostmourne.monitor.contract;

import java.util.Map;

public class MetricContract {

    private String metric_type;

    private String aggregation_type;

    private String aggregation_field;

    private Long alarm_id;

    private Long rule_id;

    private Long data_source_id;

    private Long data_name_id;

    private String data_name;

    private Map<String, Object> properties;

    private String query_string;

    private String post_data;

    private DataSourceContract dataSourceContract;

    private DataNameContract dataNameContract;

    public Long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    public Long getRule_id() {
        return rule_id;
    }

    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

    public Long getDataSourceId() {
        return data_source_id;
    }

    public void setDataSourceId(Long data_source_id) {
        this.data_source_id = data_source_id;
    }

    public String getDataName() {
        return data_name;
    }

    public void setDataName(String data_name) {
        this.data_name = data_name;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string;
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

    public Long getDataName_id() {
        return data_name_id;
    }

    public void setDataName_id(Long data_name_id) {
        this.data_name_id = data_name_id;
    }

    public String getMetric_type() {
        return metric_type;
    }

    public void setMetric_type(String metric_type) {
        this.metric_type = metric_type;
    }

    public String getPost_data() {
        return post_data;
    }

    public void setPost_data(String post_data) {
        this.post_data = post_data;
    }

    public String getAggregation_type() {
        return aggregation_type;
    }

    public void setAggregation_type(String aggregation_type) {
        this.aggregation_type = aggregation_type;
    }

    public String getAggregation_field() {
        return aggregation_field;
    }

    public void setAggregation_field(String aggregation_field) {
        this.aggregation_field = aggregation_field;
    }
}
