package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.753+08:00", comments="Source Table: user_info")
    public static final UserInfo userInfo = new UserInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source field: user_info.id")
    public static final SqlColumn<Long> id = userInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source field: user_info.account")
    public static final SqlColumn<String> account = userInfo.account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source field: user_info.full_name")
    public static final SqlColumn<String> fullName = userInfo.fullName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source field: user_info.team_id")
    public static final SqlColumn<Long> teamId = userInfo.teamId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source field: user_info.mobile")
    public static final SqlColumn<String> mobile = userInfo.mobile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source field: user_info.email")
    public static final SqlColumn<String> email = userInfo.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.755+08:00", comments="Source field: user_info.wxid")
    public static final SqlColumn<String> wxid = userInfo.wxid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.755+08:00", comments="Source field: user_info.creator")
    public static final SqlColumn<String> creator = userInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.755+08:00", comments="Source field: user_info.create_at")
    public static final SqlColumn<Date> createAt = userInfo.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.755+08:00", comments="Source field: user_info.modify_at")
    public static final SqlColumn<Date> modifyAt = userInfo.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.755+08:00", comments="Source field: user_info.modifier")
    public static final SqlColumn<String> modifier = userInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.755+08:00", comments="Source field: user_info.password")
    public static final SqlColumn<String> password = userInfo.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-20T22:50:25.754+08:00", comments="Source Table: user_info")
    public static final class UserInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<String> fullName = column("full_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> teamId = column("team_id", JDBCType.BIGINT);

        public final SqlColumn<String> mobile = column("mobile", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> wxid = column("wxid", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public UserInfo() {
            super("user_info");
        }
    }
}