package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;

public class Metric {
    private Long id;

    private String aggregation_type;

    private String aggregation_field;

    private String metric_type;

    private Long alarm_id;

    private Long rule_id;

    private Long data_source_id;

    private Long data_name_id;

    private String data_name;

    private String query_string;

    private String properties;

    private String creator;

    private Date create_at;

    private String post_data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregation_type() {
        return aggregation_type;
    }

    public void setAggregation_type(String aggregation_type) {
        this.aggregation_type = aggregation_type == null ? null : aggregation_type.trim();
    }

    public String getAggregation_field() {
        return aggregation_field;
    }

    public void setAggregation_field(String aggregation_field) {
        this.aggregation_field = aggregation_field == null ? null : aggregation_field.trim();
    }

    public String getMetric_type() {
        return metric_type;
    }

    public void setMetric_type(String metric_type) {
        this.metric_type = metric_type == null ? null : metric_type.trim();
    }

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

    public Long getData_source_id() {
        return data_source_id;
    }

    public void setData_source_id(Long data_source_id) {
        this.data_source_id = data_source_id;
    }

    public Long getData_name_id() {
        return data_name_id;
    }

    public void setData_name_id(Long data_name_id) {
        this.data_name_id = data_name_id;
    }

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name == null ? null : data_name.trim();
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string == null ? null : query_string.trim();
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getPost_data() {
        return post_data;
    }

    public void setPost_data(String post_data) {
        this.post_data = post_data == null ? null : post_data.trim();
    }
}