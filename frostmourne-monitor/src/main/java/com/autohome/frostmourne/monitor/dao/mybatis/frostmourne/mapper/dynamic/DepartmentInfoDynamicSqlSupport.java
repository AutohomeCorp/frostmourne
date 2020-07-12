package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DepartmentInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source Table: department_info")
    public static final DepartmentInfo departmentInfo = new DepartmentInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.id")
    public static final SqlColumn<Long> id = departmentInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.department_name")
    public static final SqlColumn<String> department_name = departmentInfo.department_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.full_name")
    public static final SqlColumn<String> full_name = departmentInfo.full_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.creator")
    public static final SqlColumn<String> creator = departmentInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.create_at")
    public static final SqlColumn<Date> create_at = departmentInfo.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modify_at")
    public static final SqlColumn<Date> modify_at = departmentInfo.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source field: department_info.modifier")
    public static final SqlColumn<String> modifier = departmentInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.813+08:00", comments="Source Table: department_info")
    public static final class DepartmentInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> department_name = column("department_name", JDBCType.VARCHAR);

        public final SqlColumn<String> full_name = column("full_name", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public DepartmentInfo() {
            super("department_info");
        }
    }
}