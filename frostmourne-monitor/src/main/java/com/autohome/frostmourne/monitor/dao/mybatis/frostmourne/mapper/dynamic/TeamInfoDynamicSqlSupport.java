package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TeamInfoDynamicSqlSupport {
    public static final TeamInfo teamInfo = new TeamInfo();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = teamInfo.id;

    /**
     * 团队名称
     */
    public static final SqlColumn<String> teamName = teamInfo.teamName;

    /**
     * 全称，一般是中文名字
     */
    public static final SqlColumn<String> fullName = teamInfo.fullName;

    /**
     * 部门ID
     */
    public static final SqlColumn<Long> departmentId = teamInfo.departmentId;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = teamInfo.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = teamInfo.createAt;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = teamInfo.modifyAt;

    /**
     * 最后修改人
     */
    public static final SqlColumn<String> modifier = teamInfo.modifier;

    public static final class TeamInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> teamName = column("team_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fullName = column("full_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> departmentId = column("department_id", JDBCType.BIGINT);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public TeamInfo() {
            super("team_info");
        }
    }
}