package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RuleDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.803+08:00", comments="Source Table: rule")
    public static final Rule rule = new Rule();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.803+08:00", comments="Source field: rule.id")
    public static final SqlColumn<Long> id = rule.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.803+08:00", comments="Source field: rule.rule_type")
    public static final SqlColumn<String> rule_type = rule.rule_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.803+08:00", comments="Source field: rule.alarm_id")
    public static final SqlColumn<Long> alarm_id = rule.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.804+08:00", comments="Source field: rule.alert_template")
    public static final SqlColumn<String> alert_template = rule.alert_template;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.804+08:00", comments="Source field: rule.creator")
    public static final SqlColumn<String> creator = rule.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.804+08:00", comments="Source field: rule.create_at")
    public static final SqlColumn<Date> create_at = rule.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.803+08:00", comments="Source Table: rule")
    public static final class Rule extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> rule_type = column("rule_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<String> alert_template = column("alert_template", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public Rule() {
            super("rule");
        }
    }
}