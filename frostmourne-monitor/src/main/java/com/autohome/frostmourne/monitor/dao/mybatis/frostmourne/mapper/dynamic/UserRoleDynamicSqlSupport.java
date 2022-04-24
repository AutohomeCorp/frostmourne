package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserRoleDynamicSqlSupport {
    public static final UserRole userRole = new UserRole();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = userRole.id;

    /**
     * 账号
     */
    public static final SqlColumn<String> account = userRole.account;

    /**
     * 角色
     */
    public static final SqlColumn<String> role = userRole.role;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = userRole.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = userRole.createAt;

    public static final class UserRole extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public UserRole() {
            super("user_role");
        }
    }
}