package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserRoleDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.152+08:00", comments="Source Table: user_role")
    public static final UserRole userRole = new UserRole();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.152+08:00", comments="Source field: user_role.id")
    public static final SqlColumn<Long> id = userRole.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.153+08:00", comments="Source field: user_role.account")
    public static final SqlColumn<String> account = userRole.account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.153+08:00", comments="Source field: user_role.role")
    public static final SqlColumn<String> role = userRole.role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.153+08:00", comments="Source field: user_role.creator")
    public static final SqlColumn<String> creator = userRole.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.153+08:00", comments="Source field: user_role.create_at")
    public static final SqlColumn<Date> create_at = userRole.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-07T19:08:32.152+08:00", comments="Source Table: user_role")
    public static final class UserRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public UserRole() {
            super("user_role");
        }
    }
}