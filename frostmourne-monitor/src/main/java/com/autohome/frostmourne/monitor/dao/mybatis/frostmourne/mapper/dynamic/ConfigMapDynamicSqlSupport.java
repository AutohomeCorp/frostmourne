package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ConfigMapDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.847+08:00", comments="Source Table: config_map")
    public static final ConfigMap configMap = new ConfigMap();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.id")
    public static final SqlColumn<Long> id = configMap.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.config_key")
    public static final SqlColumn<String> configKey = configMap.configKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.config_value")
    public static final SqlColumn<String> configValue = configMap.configValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.creator")
    public static final SqlColumn<String> creator = configMap.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.create_at")
    public static final SqlColumn<Date> createAt = configMap.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.modify_at")
    public static final SqlColumn<Date> modifyAt = configMap.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.848+08:00", comments="Source field: config_map.modifier")
    public static final SqlColumn<String> modifier = configMap.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.847+08:00", comments="Source Table: config_map")
    public static final class ConfigMap extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> configKey = column("config_key", JDBCType.VARCHAR);

        public final SqlColumn<String> configValue = column("config_value", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public ConfigMap() {
            super("config_map");
        }
    }
}