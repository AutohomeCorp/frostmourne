package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate;

import java.io.Serializable;
import java.util.Date;

/**
 * metric
 *
 * @author mybatis-generator
 */
public class Metric implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 数据源为http类型时无效。指标聚合类型. (count, spike, sum, avg)
     */
    private String aggregationType;

    /**
     * 聚合字段
     */
    private String aggregationField;

    /**
     * 指标类型(numeric：数值; ring_than: 环比; same_time: 同比; object: 对象)
     */
    private String metricType;

    /**
     * 监控ID
     */
    private Long alarmId;

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 数据源id
     */
    private Long dataSourceId;

    /**
     * 数据名id
     */
    private Long dataNameId;

    /**
     * 监控数据名。(http：表示静态http数据; 其他data_name关联data_name表)
     */
    private String dataName;

    /**
     * 查询语句
     */
    private String queryString;

    /**
     * http数据监控，post数据内容
     */
    private String postData;

    /**
     * 附加属性JSON格式
     */
    private String properties;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 分桶类型。terms: 字段值分组; date_histogram: 时间分组
     */
    private String bucketType;

    /**
     * 分桶字段
     */
    private String bucketField;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType == null ? null : aggregationType.trim();
    }

    public String getAggregationField() {
        return aggregationField;
    }

    public void setAggregationField(String aggregationField) {
        this.aggregationField = aggregationField == null ? null : aggregationField.trim();
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType == null ? null : metricType.trim();
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
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString == null ? null : queryString.trim();
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData == null ? null : postData.trim();
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getBucketType() {
        return bucketType;
    }

    public void setBucketType(String bucketType) {
        this.bucketType = bucketType == null ? null : bucketType.trim();
    }

    public String getBucketField() {
        return bucketField;
    }

    public void setBucketField(String bucketField) {
        this.bucketField = bucketField == null ? null : bucketField.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Metric other = (Metric) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAggregationType() == null ? other.getAggregationType() == null : this.getAggregationType().equals(other.getAggregationType()))
            && (this.getAggregationField() == null ? other.getAggregationField() == null : this.getAggregationField().equals(other.getAggregationField()))
            && (this.getMetricType() == null ? other.getMetricType() == null : this.getMetricType().equals(other.getMetricType()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getRuleId() == null ? other.getRuleId() == null : this.getRuleId().equals(other.getRuleId()))
            && (this.getDataSourceId() == null ? other.getDataSourceId() == null : this.getDataSourceId().equals(other.getDataSourceId()))
            && (this.getDataNameId() == null ? other.getDataNameId() == null : this.getDataNameId().equals(other.getDataNameId()))
            && (this.getDataName() == null ? other.getDataName() == null : this.getDataName().equals(other.getDataName()))
            && (this.getQueryString() == null ? other.getQueryString() == null : this.getQueryString().equals(other.getQueryString()))
            && (this.getPostData() == null ? other.getPostData() == null : this.getPostData().equals(other.getPostData()))
            && (this.getProperties() == null ? other.getProperties() == null : this.getProperties().equals(other.getProperties()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getBucketType() == null ? other.getBucketType() == null : this.getBucketType().equals(other.getBucketType()))
            && (this.getBucketField() == null ? other.getBucketField() == null : this.getBucketField().equals(other.getBucketField()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAggregationType() == null) ? 0 : getAggregationType().hashCode());
        result = prime * result + ((getAggregationField() == null) ? 0 : getAggregationField().hashCode());
        result = prime * result + ((getMetricType() == null) ? 0 : getMetricType().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getRuleId() == null) ? 0 : getRuleId().hashCode());
        result = prime * result + ((getDataSourceId() == null) ? 0 : getDataSourceId().hashCode());
        result = prime * result + ((getDataNameId() == null) ? 0 : getDataNameId().hashCode());
        result = prime * result + ((getDataName() == null) ? 0 : getDataName().hashCode());
        result = prime * result + ((getQueryString() == null) ? 0 : getQueryString().hashCode());
        result = prime * result + ((getPostData() == null) ? 0 : getPostData().hashCode());
        result = prime * result + ((getProperties() == null) ? 0 : getProperties().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getBucketType() == null) ? 0 : getBucketType().hashCode());
        result = prime * result + ((getBucketField() == null) ? 0 : getBucketField().hashCode());
        return result;
    }
}