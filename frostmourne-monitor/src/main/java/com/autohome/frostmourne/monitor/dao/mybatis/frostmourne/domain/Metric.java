package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain;

import java.util.Date;
import javax.annotation.Generated;

public class Metric {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.aggregation_type")
    private String aggregationType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.aggregation_field")
    private String aggregationField;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.metric_type")
    private String metricType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.alarm_id")
    private Long alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.rule_id")
    private Long ruleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_source_id")
    private Long dataSourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_name_id")
    private Long dataNameId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_name")
    private String dataName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.query_string")
    private String queryString;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.post_data")
    private String postData;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.properties")
    private String properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.creator")
    private String creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.create_at")
    private Date createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.id")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.aggregation_type")
    public String getAggregationType() {
        return aggregationType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.aggregation_type")
    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.aggregation_field")
    public String getAggregationField() {
        return aggregationField;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.aggregation_field")
    public void setAggregationField(String aggregationField) {
        this.aggregationField = aggregationField;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source field: metric.metric_type")
    public String getMetricType() {
        return metricType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.metric_type")
    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.alarm_id")
    public Long getAlarmId() {
        return alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.alarm_id")
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.rule_id")
    public Long getRuleId() {
        return ruleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.rule_id")
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_source_id")
    public Long getDataSourceId() {
        return dataSourceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_source_id")
    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_name_id")
    public Long getDataNameId() {
        return dataNameId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_name_id")
    public void setDataNameId(Long dataNameId) {
        this.dataNameId = dataNameId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_name")
    public String getDataName() {
        return dataName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.data_name")
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.query_string")
    public String getQueryString() {
        return queryString;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.query_string")
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.post_data")
    public String getPostData() {
        return postData;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.post_data")
    public void setPostData(String postData) {
        this.postData = postData;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.properties")
    public String getProperties() {
        return properties;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.987+08:00", comments="Source field: metric.properties")
    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.creator")
    public String getCreator() {
        return creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.create_at")
    public Date getCreateAt() {
        return createAt;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.create_at")
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}