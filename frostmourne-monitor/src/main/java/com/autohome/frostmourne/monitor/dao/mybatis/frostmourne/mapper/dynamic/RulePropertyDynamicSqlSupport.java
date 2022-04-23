package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RulePropertyDynamicSqlSupport {
    public static final RuleProperty ruleProperty = new RuleProperty();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = ruleProperty.id;

    /**
     * 监控Id
     */
    public static final SqlColumn<Long> alarmId = ruleProperty.alarmId;

    /**
     * 报警规则ID
     */
    public static final SqlColumn<Long> ruleId = ruleProperty.ruleId;

    /**
     * 属性key
     */
    public static final SqlColumn<String> propKey = ruleProperty.propKey;

    /**
     * 属性value
     */
    public static final SqlColumn<String> propValue = ruleProperty.propValue;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = ruleProperty.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = ruleProperty.createAt;

    public static final class RuleProperty extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> ruleId = column("rule_id", JDBCType.BIGINT);

        public final SqlColumn<String> propKey = column("prop_key", JDBCType.VARCHAR);

        public final SqlColumn<String> propValue = column("prop_value", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public RuleProperty() {
            super("rule_property");
        }
    }
}