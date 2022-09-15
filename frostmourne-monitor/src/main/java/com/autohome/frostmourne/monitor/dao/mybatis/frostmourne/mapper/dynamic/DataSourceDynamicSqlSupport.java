package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DataSourceDynamicSqlSupport {
    public static final DataSource dataSource = new DataSource();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = dataSource.id;

    /**
     * 数据源名称
     */
    public static final SqlColumn<String> datasourceName = dataSource.datasourceName;

    /**
     * 数据源类型。(Elasticsearch, Influxdb)
     */
    public static final SqlColumn<DataSourceType> datasourceType = dataSource.datasourceType;

    /**
     * 数据源服务地址
     */
    public static final SqlColumn<String> serviceAddress = dataSource.serviceAddress;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = dataSource.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = dataSource.createAt;

    /**
     * 修改人
     */
    public static final SqlColumn<String> modifier = dataSource.modifier;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = dataSource.modifyAt;

    /**
     * 附加属性。json格式
     */
    public static final SqlColumn<String> properties = dataSource.properties;

    public static final class DataSource extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> datasourceName = column("datasource_name", JDBCType.VARCHAR);

        public final SqlColumn<DataSourceType> datasourceType = column("datasource_type", JDBCType.VARCHAR);

        public final SqlColumn<String> serviceAddress = column("service_address", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> properties = column("properties", JDBCType.LONGVARCHAR, "com.autohome.frostmourne.monitor.handler.CryptoTypeHandler");

        public DataSource() {
            super("data_source");
        }
    }
}