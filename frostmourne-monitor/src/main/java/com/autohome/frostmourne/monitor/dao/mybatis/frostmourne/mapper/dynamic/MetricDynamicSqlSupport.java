package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MetricDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.673+08:00", comments="Source Table: metric")
    public static final Metric metric = new Metric();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.673+08:00", comments="Source field: metric.id")
    public static final SqlColumn<Long> id = metric.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.aggregation_type")
    public static final SqlColumn<String> aggregation_type = metric.aggregation_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.aggregation_field")
    public static final SqlColumn<String> aggregation_field = metric.aggregation_field;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.metric_type")
    public static final SqlColumn<String> metric_type = metric.metric_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.alarm_id")
    public static final SqlColumn<Long> alarm_id = metric.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.rule_id")
    public static final SqlColumn<Long> rule_id = metric.rule_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.data_source_id")
    public static final SqlColumn<Long> data_source_id = metric.data_source_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.data_name_id")
    public static final SqlColumn<Long> data_name_id = metric.data_name_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.674+08:00", comments="Source field: metric.data_name")
    public static final SqlColumn<String> data_name = metric.data_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.675+08:00", comments="Source field: metric.query_string")
    public static final SqlColumn<String> query_string = metric.query_string;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.675+08:00", comments="Source field: metric.post_data")
    public static final SqlColumn<String> post_data = metric.post_data;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.675+08:00", comments="Source field: metric.properties")
    public static final SqlColumn<String> properties = metric.properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.675+08:00", comments="Source field: metric.creator")
    public static final SqlColumn<String> creator = metric.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.675+08:00", comments="Source field: metric.create_at")
    public static final SqlColumn<Date> create_at = metric.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:04:51.673+08:00", comments="Source Table: metric")
    public static final class Metric extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> aggregation_type = column("aggregation_type", JDBCType.VARCHAR);

        public final SqlColumn<String> aggregation_field = column("aggregation_field", JDBCType.VARCHAR);

        public final SqlColumn<String> metric_type = column("metric_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> rule_id = column("rule_id", JDBCType.BIGINT);

        public final SqlColumn<Long> data_source_id = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<Long> data_name_id = column("data_name_id", JDBCType.BIGINT);

        public final SqlColumn<String> data_name = column("data_name", JDBCType.VARCHAR);

        public final SqlColumn<String> query_string = column("query_string", JDBCType.VARCHAR);

        public final SqlColumn<String> post_data = column("post_data", JDBCType.VARCHAR);

        public final SqlColumn<String> properties = column("properties", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public Metric() {
            super("metric");
        }
    }
}