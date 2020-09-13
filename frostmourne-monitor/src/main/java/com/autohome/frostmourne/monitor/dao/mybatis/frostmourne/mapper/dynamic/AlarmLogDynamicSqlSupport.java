package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlarmLogDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source Table: alarm_log")
    public static final AlarmLog alarmLog = new AlarmLog();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.id")
    public static final SqlColumn<Long> id = alarmLog.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.alarm_id")
    public static final SqlColumn<Long> alarmId = alarmLog.alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.exe_start")
    public static final SqlColumn<Date> exeStart = alarmLog.exeStart;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.exe_end")
    public static final SqlColumn<Date> exeEnd = alarmLog.exeEnd;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.cost")
    public static final SqlColumn<Integer> cost = alarmLog.cost;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.execute_result")
    public static final SqlColumn<String> executeResult = alarmLog.executeResult;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source field: alarm_log.verify_result")
    public static final SqlColumn<String> verifyResult = alarmLog.verifyResult;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.971+08:00", comments="Source field: alarm_log.create_at")
    public static final SqlColumn<Date> createAt = alarmLog.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.971+08:00", comments="Source field: alarm_log.message")
    public static final SqlColumn<String> message = alarmLog.message;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.97+08:00", comments="Source Table: alarm_log")
    public static final class AlarmLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Date> exeStart = column("exe_start", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> exeEnd = column("exe_end", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> cost = column("cost", JDBCType.INTEGER);

        public final SqlColumn<String> executeResult = column("execute_result", JDBCType.VARCHAR);

        public final SqlColumn<String> verifyResult = column("verify_result", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> message = column("message", JDBCType.LONGVARCHAR);

        public AlarmLog() {
            super("alarm_log");
        }
    }
}