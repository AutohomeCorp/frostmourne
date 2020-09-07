package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertTemplateDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source Table: alert_template")
    public static final AlertTemplate alertTemplate = new AlertTemplate();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.id")
    public static final SqlColumn<Long> id = alertTemplate.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_name")
    public static final SqlColumn<String> templateName = alertTemplate.templateName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_type")
    public static final SqlColumn<String> templateType = alertTemplate.templateType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.template_union_code")
    public static final SqlColumn<String> templateUnionCode = alertTemplate.templateUnionCode;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.content")
    public static final SqlColumn<String> content = alertTemplate.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.creator")
    public static final SqlColumn<String> creator = alertTemplate.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.create_at")
    public static final SqlColumn<Date> createAt = alertTemplate.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modifier")
    public static final SqlColumn<String> modifier = alertTemplate.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source field: alert_template.modify_at")
    public static final SqlColumn<Date> modifyAt = alertTemplate.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.013+08:00", comments="Source Table: alert_template")
    public static final class AlertTemplate extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> templateName = column("template_name", JDBCType.VARCHAR);

        public final SqlColumn<String> templateType = column("template_type", JDBCType.VARCHAR);

        public final SqlColumn<String> templateUnionCode = column("template_union_code", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public AlertTemplate() {
            super("alert_template");
        }
    }
}