package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RulePropertyDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33+08:00", comments="Source Table: rule_property")
    public static final RuleProperty ruleProperty = new RuleProperty();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.id")
    public static final SqlColumn<Long> id = ruleProperty.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.alarm_id")
    public static final SqlColumn<Long> alarmId = ruleProperty.alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.rule_id")
    public static final SqlColumn<Long> ruleId = ruleProperty.ruleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.prop_key")
    public static final SqlColumn<String> propKey = ruleProperty.propKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.prop_value")
    public static final SqlColumn<String> propValue = ruleProperty.propValue;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.creator")
    public static final SqlColumn<String> creator = ruleProperty.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source field: rule_property.create_at")
    public static final SqlColumn<Date> createAt = ruleProperty.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.001+08:00", comments="Source Table: rule_property")
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