package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TeamInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.009+08:00", comments="Source Table: team_info")
    public static final TeamInfo teamInfo = new TeamInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.009+08:00", comments="Source field: team_info.id")
    public static final SqlColumn<Long> id = teamInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.team_name")
    public static final SqlColumn<String> teamName = teamInfo.teamName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.full_name")
    public static final SqlColumn<String> fullName = teamInfo.fullName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.department_id")
    public static final SqlColumn<Long> departmentId = teamInfo.departmentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.creator")
    public static final SqlColumn<String> creator = teamInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.create_at")
    public static final SqlColumn<Date> createAt = teamInfo.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.modify_at")
    public static final SqlColumn<Date> modifyAt = teamInfo.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.01+08:00", comments="Source field: team_info.modifier")
    public static final SqlColumn<String> modifier = teamInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.009+08:00", comments="Source Table: team_info")
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