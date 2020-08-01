package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DataNameDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.493+08:00", comments="Source Table: data_name")
    public static final DataName dataName = new DataName();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.493+08:00", comments="Source field: data_name.id")
    public static final SqlColumn<Long> id = dataName.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.494+08:00", comments="Source field: data_name.data_name")
    public static final SqlColumn<String> data_name = dataName.data_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.494+08:00", comments="Source field: data_name.display_name")
    public static final SqlColumn<String> display_name = dataName.display_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.494+08:00", comments="Source field: data_name.data_source_id")
    public static final SqlColumn<Long> data_source_id = dataName.data_source_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.494+08:00", comments="Source field: data_name.datasource_type")
    public static final SqlColumn<String> datasource_type = dataName.datasource_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.494+08:00", comments="Source field: data_name.timestamp_field")
    public static final SqlColumn<String> timestamp_field = dataName.timestamp_field;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.495+08:00", comments="Source field: data_name.properties")
    public static final SqlColumn<String> properties = dataName.properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.495+08:00", comments="Source field: data_name.creator")
    public static final SqlColumn<String> creator = dataName.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.495+08:00", comments="Source field: data_name.create_at")
    public static final SqlColumn<Date> create_at = dataName.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.495+08:00", comments="Source field: data_name.modifier")
    public static final SqlColumn<String> modifier = dataName.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.495+08:00", comments="Source field: data_name.modify_at")
    public static final SqlColumn<Date> modify_at = dataName.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:23:25.493+08:00", comments="Source Table: data_name")
    public static final class DataName extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> data_name = column("data_name", JDBCType.VARCHAR);

        public final SqlColumn<String> display_name = column("display_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> data_source_id = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<String> datasource_type = column("datasource_type", JDBCType.VARCHAR);

        public final SqlColumn<String> timestamp_field = column("timestamp_field", JDBCType.VARCHAR);

        public final SqlColumn<String> properties = column("properties", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public DataName() {
            super("data_name");
        }
    }
}