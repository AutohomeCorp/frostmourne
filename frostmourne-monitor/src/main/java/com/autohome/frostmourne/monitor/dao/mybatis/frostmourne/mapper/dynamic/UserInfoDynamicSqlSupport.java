package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserInfoDynamicSqlSupport {
    public static final UserInfo userInfo = new UserInfo();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = userInfo.id;

    /**
     * 账号
     */
    public static final SqlColumn<String> account = userInfo.account;

    /**
     * 全称，一般是中文名字
     */
    public static final SqlColumn<String> fullName = userInfo.fullName;

    /**
     * 所属团队id
     */
    public static final SqlColumn<Long> teamId = userInfo.teamId;

    /**
     * 号码
     */
    public static final SqlColumn<String> mobile = userInfo.mobile;

    /**
     * 邮箱
     */
    public static final SqlColumn<String> email = userInfo.email;

    /**
     * 企业微信id
     */
    public static final SqlColumn<String> wxid = userInfo.wxid;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = userInfo.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = userInfo.createAt;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = userInfo.modifyAt;

    /**
     * 最后修改人
     */
    public static final SqlColumn<String> modifier = userInfo.modifier;

    /**
     * 密码
     */
    public static final SqlColumn<String> password = userInfo.password;

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