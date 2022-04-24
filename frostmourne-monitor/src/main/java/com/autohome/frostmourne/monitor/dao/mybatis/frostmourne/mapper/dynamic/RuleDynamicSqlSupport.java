package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RuleDynamicSqlSupport {
    public static final Rule rule = new Rule();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = rule.id;

    /**
     * 规则类型(numeric,percentage,expression)
     */
    public static final SqlColumn<String> ruleType = rule.ruleType;

    /**
     * 报警ID
     */
    public static final SqlColumn<Long> alarmId = rule.alarmId;

    /**
     * 报警内容模板
     */
    public static final SqlColumn<String> alertTemplate = rule.alertTemplate;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = rule.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = rule.createAt;

    /**
     * 报警消息类型(TEXT,MARKDOWN)
     */
    public static final SqlColumn<String> alertTemplateType = rule.alertTemplateType;

    public static final class Rule extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> ruleType = column("rule_type", JDBCType.VARCHAR);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<String> alertTemplate = column("alert_template", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> alertTemplateType = column("alert_template_type", JDBCType.VARCHAR);

        public Rule() {
            super("rule");
        }
    }
}