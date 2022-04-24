package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DataNameDynamicSqlSupport {
    public static final DataName dataName = new DataName();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = dataName.id;

    /**
     * 名称描述
     */
    public static final SqlColumn<String> displayName = dataName.displayName;

    /**
     * 所属数据源id
     */
    public static final SqlColumn<Long> dataSourceId = dataName.dataSourceId;

    /**
     * 数据源类型。(Elasticsearch, Influxdb)
     */
    public static final SqlColumn<String> datasourceType = dataName.datasourceType;

    /**
     * 时间字段名
     */
    public static final SqlColumn<String> timestampField = dataName.timestampField;

    /**
     * 不同数据的附加属性
     */
    public static final SqlColumn<String> properties = dataName.properties;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = dataName.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = dataName.createAt;

    /**
     * 修改人
     */
    public static final SqlColumn<String> modifier = dataName.modifier;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = dataName.modifyAt;

    public static final class DataName extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> dataName = column("data_name", JDBCType.VARCHAR);

        public final SqlColumn<String> displayName = column("display_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> dataSourceId = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<String> datasourceType = column("datasource_type", JDBCType.VARCHAR);

        public final SqlColumn<String> timestampField = column("timestamp_field", JDBCType.VARCHAR);

        public final SqlColumn<String> properties = column("properties", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public DataName() {
            super("data_name");
        }
    }
}