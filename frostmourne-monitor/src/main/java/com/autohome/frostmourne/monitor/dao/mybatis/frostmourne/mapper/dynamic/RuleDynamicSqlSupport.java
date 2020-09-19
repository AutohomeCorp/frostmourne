package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RuleDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source Table: rule")
    public static final Rule rule = new Rule();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source field: rule.id")
    public static final SqlColumn<Long> id = rule.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source field: rule.rule_type")
    public static final SqlColumn<String> ruleType = rule.ruleType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source field: rule.alarm_id")
    public static final SqlColumn<Long> alarmId = rule.alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source field: rule.alert_template")
    public static final SqlColumn<String> alertTemplate = rule.alertTemplate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source field: rule.creator")
    public static final SqlColumn<String> creator = rule.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source field: rule.create_at")
    public static final SqlColumn<Date> createAt = rule.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.998+08:00", comments="Source Table: rule")
    public static final class Rule extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> ruleType = column("rule_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<String> alertTemplate = column("alert_template", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public Rule() {
            super("rule");
        }
    }
}