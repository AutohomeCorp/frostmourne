package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MetricDynamicSqlSupport {
    public static final Metric metric = new Metric();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = metric.id;

    /**
     * 数据源为http类型时无效。指标聚合类型. (count, spike, sum, avg)
     */
    public static final SqlColumn<String> aggregationType = metric.aggregationType;

    /**
     * 聚合字段
     */
    public static final SqlColumn<String> aggregationField = metric.aggregationField;

    /**
     * 指标类型(numeric：数值; ring_than: 环比; same_time: 同比; object: 对象)
     */
    public static final SqlColumn<String> metricType = metric.metricType;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = metric.alarmId;

    /**
     * 规则ID
     */
    public static final SqlColumn<Long> ruleId = metric.ruleId;

    /**
     * 数据源id
     */
    public static final SqlColumn<Long> dataSourceId = metric.dataSourceId;

    /**
     * 数据名id
     */
    public static final SqlColumn<Long> dataNameId = metric.dataNameId;

    /**
     * 监控数据名。(http：表示静态http数据; 其他data_name关联data_name表)
     */
    public static final SqlColumn<String> dataName = metric.dataName;

    /**
     * 查询语句
     */
    public static final SqlColumn<String> queryString = metric.queryString;

    /**
     * http数据监控，post数据内容
     */
    public static final SqlColumn<String> postData = metric.postData;

    /**
     * 附加属性JSON格式
     */
    public static final SqlColumn<String> properties = metric.properties;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = metric.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = metric.createAt;

    /**
     * 分桶类型。terms: 字段值分组; date_histogram: 时间分组
     */
    public static final SqlColumn<String> bucketType = metric.bucketType;

    /**
     * 分桶字段
     */
    public static final SqlColumn<String> bucketField = metric.bucketField;

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

        public final SqlColumn<String> bucketType = column("bucket_type", JDBCType.VARCHAR);

        public final SqlColumn<String> bucketField = column("bucket_field", JDBCType.VARCHAR);

        public Metric() {
            super("metric");
        }
    }
}