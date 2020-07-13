package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TeamInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.81+08:00", comments="Source Table: team_info")
    public static final TeamInfo teamInfo = new TeamInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.id")
    public static final SqlColumn<Long> id = teamInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.team_name")
    public static final SqlColumn<String> team_name = teamInfo.team_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.full_name")
    public static final SqlColumn<String> full_name = teamInfo.full_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.department_id")
    public static final SqlColumn<Long> department_id = teamInfo.department_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.creator")
    public static final SqlColumn<String> creator = teamInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.create_at")
    public static final SqlColumn<Date> create_at = teamInfo.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.modify_at")
    public static final SqlColumn<Date> modify_at = teamInfo.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source field: team_info.modifier")
    public static final SqlColumn<String> modifier = teamInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    public static final class TeamInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> team_name = column("team_name", JDBCType.VARCHAR);

        public final SqlColumn<String> full_name = column("full_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> department_id = column("department_id", JDBCType.BIGINT);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public TeamInfo() {
            super("team_info");
        }
    }
}