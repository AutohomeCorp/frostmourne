package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DepartmentInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source Table: department_info")
    public static final DepartmentInfo departmentInfo = new DepartmentInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.id")
    public static final SqlColumn<Long> id = departmentInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.department_name")
    public static final SqlColumn<String> departmentName = departmentInfo.departmentName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.full_name")
    public static final SqlColumn<String> fullName = departmentInfo.fullName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.creator")
    public static final SqlColumn<String> creator = departmentInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.create_at")
    public static final SqlColumn<Date> createAt = departmentInfo.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modify_at")
    public static final SqlColumn<Date> modifyAt = departmentInfo.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source field: department_info.modifier")
    public static final SqlColumn<String> modifier = departmentInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.012+08:00", comments="Source Table: department_info")
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