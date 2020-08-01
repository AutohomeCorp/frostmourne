package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertTemplateDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.605+08:00", comments="Source Table: alert_template")
    public static final AlertTemplate alertTemplate = new AlertTemplate();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.605+08:00", comments="Source field: alert_template.id")
    public static final SqlColumn<Long> id = alertTemplate.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.template_name")
    public static final SqlColumn<String> template_name = alertTemplate.template_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.template_type")
    public static final SqlColumn<String> template_type = alertTemplate.template_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.template_union_code")
    public static final SqlColumn<String> template_union_code = alertTemplate.template_union_code;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.content")
    public static final SqlColumn<String> content = alertTemplate.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.creator")
    public static final SqlColumn<String> creator = alertTemplate.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.create_at")
    public static final SqlColumn<Date> create_at = alertTemplate.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.modifier")
    public static final SqlColumn<String> modifier = alertTemplate.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.606+08:00", comments="Source field: alert_template.modify_at")
    public static final SqlColumn<Date> modify_at = alertTemplate.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.605+08:00", comments="Source Table: alert_template")
    public static final class AlertTemplate extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> template_name = column("template_name", JDBCType.VARCHAR);

        public final SqlColumn<String> template_type = column("template_type", JDBCType.VARCHAR);

        public final SqlColumn<String> template_union_code = column("template_union_code", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public AlertTemplate() {
            super("alert_template");
        }
    }
}