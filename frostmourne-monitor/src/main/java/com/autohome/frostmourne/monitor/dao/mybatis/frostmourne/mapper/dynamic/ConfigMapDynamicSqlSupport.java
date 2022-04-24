package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ConfigMapDynamicSqlSupport {
    public static final ConfigMap configMap = new ConfigMap();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = configMap.id;

    /**
     * 配置key
     */
    public static final SqlColumn<String> configKey = configMap.configKey;

    /**
     * 配置value
     */
    public static final SqlColumn<String> configValue = configMap.configValue;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = configMap.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = configMap.createAt;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = configMap.modifyAt;

    /**
     * 最后修改人
     */
    public static final SqlColumn<String> modifier = configMap.modifier;

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