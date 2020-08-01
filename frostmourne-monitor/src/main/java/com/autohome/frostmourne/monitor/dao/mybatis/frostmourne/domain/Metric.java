package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Metric {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.664+08:00", comments="Source field: metric.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.aggregation_type")
    private String aggregation_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.aggregation_field")
    private String aggregation_field;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.metric_type")
    private String metric_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.alarm_id")
    private Long alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.rule_id")
    private Long rule_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.data_source_id")
    private Long data_source_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.data_name_id")
    private Long data_name_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.data_name")
    private String data_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.query_string")
    private String query_string;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.post_data")
    private String post_data;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.properties")
    private String properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.create_at")
    private Date create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.667+08:00", comments="Source field: metric.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.aggregation_type")
    public String getAggregation_type() {
        return aggregation_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.aggregation_type")
    public void setAggregation_type(String aggregation_type) {
        this.aggregation_type = aggregation_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.aggregation_field")
    public String getAggregation_field() {
        return aggregation_field;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.aggregation_field")
    public void setAggregation_field(String aggregation_field) {
        this.aggregation_field = aggregation_field;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.metric_type")
    public String getMetric_type() {
        return metric_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.metric_type")
    public void setMetric_type(String metric_type) {
        this.metric_type = metric_type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.alarm_id")
    public Long getAlarm_id() {
        return alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.alarm_id")
    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.rule_id")
    public Long getRule_id() {
        return rule_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.rule_id")
    public void setRule_id(Long rule_id) {
        this.rule_id = rule_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.data_source_id")
    public Long getData_source_id() {
        return data_source_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.668+08:00", comments="Source field: metric.data_source_id")
    public void setData_source_id(Long data_source_id) {
        this.data_source_id = data_source_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.data_name_id")
    public Long getData_name_id() {
        return data_name_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.data_name_id")
    public void setData_name_id(Long data_name_id) {
        this.data_name_id = data_name_id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.data_name")
    public String getData_name() {
        return data_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.data_name")
    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.query_string")
    public String getQuery_string() {
        return query_string;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.query_string")
    public void setQuery_string(String query_string) {
        this.query_string = query_string;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.post_data")
    public String getPost_data() {
        return post_data;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.post_data")
    public void setPost_data(String post_data) {
        this.post_data = post_data;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.properties")
    public String getProperties() {
        return properties;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.properties")
    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.create_at")
    public Date getCreate_at() {
        return create_at;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.669+08:00", comments="Source field: metric.create_at")
    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}