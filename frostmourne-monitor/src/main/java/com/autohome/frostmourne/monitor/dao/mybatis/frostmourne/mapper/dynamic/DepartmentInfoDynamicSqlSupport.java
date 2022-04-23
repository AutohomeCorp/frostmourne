package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DepartmentInfoDynamicSqlSupport {
    public static final DepartmentInfo departmentInfo = new DepartmentInfo();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = departmentInfo.id;

    /**
     * 部门名称
     */
    public static final SqlColumn<String> departmentName = departmentInfo.departmentName;

    /**
     * 全称，一般是中文名字
     */
    public static final SqlColumn<String> fullName = departmentInfo.fullName;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = departmentInfo.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = departmentInfo.createAt;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = departmentInfo.modifyAt;

    /**
     * 最后修改人
     */
    public static final SqlColumn<String> modifier = departmentInfo.modifier;

    public static final class DepartmentInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> departmentName = column("department_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fullName = column("full_name", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public DepartmentInfo() {
            super("department_info");
        }
    }
}