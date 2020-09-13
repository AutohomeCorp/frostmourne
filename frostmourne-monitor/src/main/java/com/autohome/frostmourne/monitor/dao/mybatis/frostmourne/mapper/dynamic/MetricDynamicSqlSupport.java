package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MetricDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source Table: metric")
    public static final Metric metric = new Metric();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.id")
    public static final SqlColumn<Long> id = metric.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.aggregation_type")
    public static final SqlColumn<String> aggregationType = metric.aggregationType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.aggregation_field")
    public static final SqlColumn<String> aggregationField = metric.aggregationField;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.metric_type")
    public static final SqlColumn<String> metricType = metric.metricType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source field: metric.alarm_id")
    public static final SqlColumn<Long> alarmId = metric.alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.989+08:00", comments="Source field: metric.rule_id")
    public static final SqlColumn<Long> ruleId = metric.ruleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.989+08:00", comments="Source field: metric.data_source_id")
    public static final SqlColumn<Long> dataSourceId = metric.dataSourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.989+08:00", comments="Source field: metric.data_name_id")
    public static final SqlColumn<Long> dataNameId = metric.dataNameId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.989+08:00", comments="Source field: metric.data_name")
    public static final SqlColumn<String> dataName = metric.dataName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.989+08:00", comments="Source field: metric.query_string")
    public static final SqlColumn<String> queryString = metric.queryString;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.99+08:00", comments="Source field: metric.post_data")
    public static final SqlColumn<String> postData = metric.postData;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.99+08:00", comments="Source field: metric.properties")
    public static final SqlColumn<String> properties = metric.properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.99+08:00", comments="Source field: metric.creator")
    public static final SqlColumn<String> creator = metric.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.99+08:00", comments="Source field: metric.create_at")
    public static final SqlColumn<Date> createAt = metric.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.988+08:00", comments="Source Table: metric")
    public static final class Metric extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> aggregationType = column("aggregation_type", JDBCType.VARCHAR);

        public final SqlColumn<String> aggregationField = column("aggregation_field", JDBCType.VARCHAR);

        public final SqlColumn<String> metricType = column("metric_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> ruleId = column("rule_id", JDBCType.BIGINT);

        public final SqlColumn<Long> dataSourceId = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<Long> dataNameId = column("data_name_id", JDBCType.BIGINT);

        public final SqlColumn<String> dataName = column("data_name", JDBCType.VARCHAR);

        public final SqlColumn<String> queryString = column("query_string", JDBCType.VARCHAR);

        public final SqlColumn<String> postData = column("post_data", JDBCType.VARCHAR);

        public final SqlColumn<String> properties = column("properties", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public Metric() {
            super("metric");
        }
    }
}