package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DataSourceDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source Table: data_source")
    public static final DataSource dataSource = new DataSource();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.id")
    public static final SqlColumn<Long> id = dataSource.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.datasource_name")
    public static final SqlColumn<String> datasourceName = dataSource.datasourceName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.datasource_type")
    public static final SqlColumn<String> datasourceType = dataSource.datasourceType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.service_address")
    public static final SqlColumn<String> serviceAddress = dataSource.serviceAddress;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.properties")
    public static final SqlColumn<String> properties = dataSource.properties;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.creator")
    public static final SqlColumn<String> creator = dataSource.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.create_at")
    public static final SqlColumn<Date> createAt = dataSource.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.modifier")
    public static final SqlColumn<String> modifier = dataSource.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source field: data_source.modify_at")
    public static final SqlColumn<Date> modifyAt = dataSource.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source Table: data_source")
    public static final class DataSource extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> datasourceName = column("datasource_name", JDBCType.VARCHAR);

        public final SqlColumn<String> datasourceType = column("datasource_type", JDBCType.VARCHAR);

        public final SqlColumn<String> serviceAddress = column("service_address", JDBCType.VARCHAR);

        public final SqlColumn<String> properties = column("properties", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public DataSource() {
            super("data_source");
        }
    }
}