package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlarmDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.999+08:00", comments="Source Table: alarm")
    public static final Alarm alarm = new Alarm();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.id")
    public static final SqlColumn<Long> id = alarm.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.alarm_name")
    public static final SqlColumn<String> alarmName = alarm.alarmName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.alarm_type")
    public static final SqlColumn<String> alarmType = alarm.alarmType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.description")
    public static final SqlColumn<String> description = alarm.description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.owner_key")
    public static final SqlColumn<String> ownerKey = alarm.ownerKey;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.status")
    public static final SqlColumn<String> status = alarm.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.execute_result")
    public static final SqlColumn<String> executeResult = alarm.executeResult;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27+08:00", comments="Source field: alarm.execute_at")
    public static final SqlColumn<Date> executeAt = alarm.executeAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.job_id")
    public static final SqlColumn<Long> jobId = alarm.jobId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.cron")
    public static final SqlColumn<String> cron = alarm.cron;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.creator")
    public static final SqlColumn<String> creator = alarm.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.create_at")
    public static final SqlColumn<Date> createAt = alarm.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.modifier")
    public static final SqlColumn<String> modifier = alarm.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.modify_at")
    public static final SqlColumn<Date> modifyAt = alarm.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.team_name")
    public static final SqlColumn<String> teamName = alarm.teamName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.risk_level")
    public static final SqlColumn<String> riskLevel = alarm.riskLevel;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.service_id")
    public static final SqlColumn<Long> serviceId = alarm.serviceId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:27.001+08:00", comments="Source field: alarm.recover_notice_status")
    public static final SqlColumn<String> recoverNoticeStatus = alarm.recoverNoticeStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-11-29T20:12:26.999+08:00", comments="Source Table: alarm")
    public static final class Alarm extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> alarmName = column("alarm_name", JDBCType.VARCHAR);

        public final SqlColumn<String> alarmType = column("alarm_type", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> ownerKey = column("owner_key", JDBCType.VARCHAR);

        public final SqlColumn<String> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<String> executeResult = column("execute_result", JDBCType.VARCHAR);

        public final SqlColumn<Date> executeAt = column("execute_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> jobId = column("job_id", JDBCType.BIGINT);

        public final SqlColumn<String> cron = column("cron", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> teamName = column("team_name", JDBCType.VARCHAR);

        public final SqlColumn<String> riskLevel = column("risk_level", JDBCType.VARCHAR);

        public final SqlColumn<Long> serviceId = column("service_id", JDBCType.BIGINT);

        public final SqlColumn<String> recoverNoticeStatus = column("recover_notice_status", JDBCType.VARCHAR);

        public Alarm() {
            super("alarm");
        }
    }
}