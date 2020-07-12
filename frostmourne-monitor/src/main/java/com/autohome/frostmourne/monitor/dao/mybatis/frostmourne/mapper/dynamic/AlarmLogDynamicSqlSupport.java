package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlarmLogDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.78+08:00", comments="Source Table: alarm_log")
    public static final AlarmLog alarmLog = new AlarmLog();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.id")
    public static final SqlColumn<Long> id = alarmLog.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.alarm_id")
    public static final SqlColumn<Long> alarm_id = alarmLog.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.exe_start")
    public static final SqlColumn<Date> exe_start = alarmLog.exe_start;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.exe_end")
    public static final SqlColumn<Date> exe_end = alarmLog.exe_end;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.cost")
    public static final SqlColumn<Integer> cost = alarmLog.cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.execute_result")
    public static final SqlColumn<String> execute_result = alarmLog.execute_result;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.verify_result")
    public static final SqlColumn<String> verify_result = alarmLog.verify_result;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.create_at")
    public static final SqlColumn<Date> create_at = alarmLog.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.781+08:00", comments="Source field: alarm_log.message")
    public static final SqlColumn<String> message = alarmLog.message;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.78+08:00", comments="Source Table: alarm_log")
    public static final class AlarmLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Date> exe_start = column("exe_start", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> exe_end = column("exe_end", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> cost = column("cost", JDBCType.INTEGER);

        public final SqlColumn<String> execute_result = column("execute_result", JDBCType.VARCHAR);

        public final SqlColumn<String> verify_result = column("verify_result", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> message = column("message", JDBCType.LONGVARCHAR);

        public AlarmLog() {
            super("alarm_log");
        }
    }
}