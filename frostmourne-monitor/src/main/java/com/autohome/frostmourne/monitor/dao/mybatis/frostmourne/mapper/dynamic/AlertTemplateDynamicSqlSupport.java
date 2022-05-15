package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.TemplateType;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertTemplateDynamicSqlSupport {
    public static final AlertTemplate alertTemplate = new AlertTemplate();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alertTemplate.id;

    /**
     * 模板名称
     */
    public static final SqlColumn<String> templateName = alertTemplate.templateName;

    /**
     * 模板类型
     */
    public static final SqlColumn<TemplateType> templateType = alertTemplate.templateType;

    /**
     * 模板类型关联code，根据不同模板类型关联不同的源
     */
    public static final SqlColumn<String> templateUnionCode = alertTemplate.templateUnionCode;

    /**
     * 模板内容
     */
    public static final SqlColumn<String> content = alertTemplate.content;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = alertTemplate.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = alertTemplate.createAt;

    /**
     * 修改人
     */
    public static final SqlColumn<String> modifier = alertTemplate.modifier;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = alertTemplate.modifyAt;

    public static final class AlertTemplate extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> templateName = column("template_name", JDBCType.VARCHAR);

        public final SqlColumn<TemplateType> templateType = column("template_type", JDBCType.VARCHAR);

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