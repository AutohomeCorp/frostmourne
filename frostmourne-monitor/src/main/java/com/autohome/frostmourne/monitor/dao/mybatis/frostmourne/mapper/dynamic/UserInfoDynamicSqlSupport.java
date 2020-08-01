package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source Table: user_info")
    public static final UserInfo userInfo = new UserInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source field: user_info.id")
    public static final SqlColumn<Long> id = userInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source field: user_info.account")
    public static final SqlColumn<String> account = userInfo.account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source field: user_info.full_name")
    public static final SqlColumn<String> full_name = userInfo.full_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source field: user_info.team_id")
    public static final SqlColumn<Long> team_id = userInfo.team_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source field: user_info.mobile")
    public static final SqlColumn<String> mobile = userInfo.mobile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source field: user_info.email")
    public static final SqlColumn<String> email = userInfo.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source field: user_info.wxid")
    public static final SqlColumn<String> wxid = userInfo.wxid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source field: user_info.creator")
    public static final SqlColumn<String> creator = userInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source field: user_info.create_at")
    public static final SqlColumn<Date> create_at = userInfo.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source field: user_info.modify_at")
    public static final SqlColumn<Date> modify_at = userInfo.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source field: user_info.modifier")
    public static final SqlColumn<String> modifier = userInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.339+08:00", comments="Source Table: user_info")
    public static final class UserInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<String> full_name = column("full_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> team_id = column("team_id", JDBCType.BIGINT);

        public final SqlColumn<String> mobile = column("mobile", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> wxid = column("wxid", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public UserInfo() {
            super("user_info");
        }
    }
}