package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RulePropertyDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source Table: rule_property")
    public static final RuleProperty ruleProperty = new RuleProperty();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.id")
    public static final SqlColumn<Long> id = ruleProperty.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.alarm_id")
    public static final SqlColumn<Long> alarm_id = ruleProperty.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.rule_id")
    public static final SqlColumn<Long> rule_id = ruleProperty.rule_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.prop_key")
    public static final SqlColumn<String> prop_key = ruleProperty.prop_key;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.prop_value")
    public static final SqlColumn<String> prop_value = ruleProperty.prop_value;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.creator")
    public static final SqlColumn<String> creator = ruleProperty.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source field: rule_property.create_at")
    public static final SqlColumn<Date> create_at = ruleProperty.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.806+08:00", comments="Source Table: rule_property")
    public static final class RuleProperty extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> rule_id = column("rule_id", JDBCType.BIGINT);

        public final SqlColumn<String> prop_key = column("prop_key", JDBCType.VARCHAR);

        public final SqlColumn<String> prop_value = column("prop_value", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public RuleProperty() {
            super("rule_property");
        }
    }
}