package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlarmDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.234+08:00", comments="Source Table: alarm")
    public static final Alarm alarm = new Alarm();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.id")
    public static final SqlColumn<Long> id = alarm.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.alarm_name")
    public static final SqlColumn<String> alarm_name = alarm.alarm_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.alarm_type")
    public static final SqlColumn<String> alarm_type = alarm.alarm_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.description")
    public static final SqlColumn<String> description = alarm.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.owner_key")
    public static final SqlColumn<String> owner_key = alarm.owner_key;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.status")
    public static final SqlColumn<String> status = alarm.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source field: alarm.execute_result")
    public static final SqlColumn<String> execute_result = alarm.execute_result;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.execute_at")
    public static final SqlColumn<Date> execute_at = alarm.execute_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.job_id")
    public static final SqlColumn<Long> job_id = alarm.job_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.cron")
    public static final SqlColumn<String> cron = alarm.cron;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.creator")
    public static final SqlColumn<String> creator = alarm.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.create_at")
    public static final SqlColumn<Date> create_at = alarm.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.modifier")
    public static final SqlColumn<String> modifier = alarm.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.modify_at")
    public static final SqlColumn<Date> modify_at = alarm.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.team_name")
    public static final SqlColumn<String> team_name = alarm.team_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.236+08:00", comments="Source field: alarm.risk_level")
    public static final SqlColumn<String> risk_level = alarm.risk_level;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-27T00:08:52.235+08:00", comments="Source Table: alarm")
    public static final class Alarm extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> alarm_name = column("alarm_name", JDBCType.VARCHAR);

        public final SqlColumn<String> alarm_type = column("alarm_type", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> owner_key = column("owner_key", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<String> execute_result = column("execute_result", JDBCType.VARCHAR);

        public final SqlColumn<Date> execute_at = column("execute_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> job_id = column("job_id", JDBCType.BIGINT);

        public final SqlColumn<String> cron = column("cron", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> team_name = column("team_name", JDBCType.VARCHAR);

        public final SqlColumn<String> risk_level = column("risk_level", JDBCType.VARCHAR);

        public Alarm() {
            super("alarm");
        }
    }
}