package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DataNameDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source Table: data_name")
    public static final DataName dataName = new DataName();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.id")
    public static final SqlColumn<Long> id = dataName.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.display_name")
    public static final SqlColumn<String> displayName = dataName.displayName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.data_source_id")
    public static final SqlColumn<Long> dataSourceId = dataName.dataSourceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.datasource_type")
    public static final SqlColumn<String> datasourceType = dataName.datasourceType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.timestamp_field")
    public static final SqlColumn<String> timestampField = dataName.timestampField;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.properties")
    public static final SqlColumn<String> properties = dataName.properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.creator")
    public static final SqlColumn<String> creator = dataName.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.create_at")
    public static final SqlColumn<Date> createAt = dataName.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.modifier")
    public static final SqlColumn<String> modifier = dataName.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source field: data_name.modify_at")
    public static final SqlColumn<Date> modifyAt = dataName.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.981+08:00", comments="Source Table: data_name")
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