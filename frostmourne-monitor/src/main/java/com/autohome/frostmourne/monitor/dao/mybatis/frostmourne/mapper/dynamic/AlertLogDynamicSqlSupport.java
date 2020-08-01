package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertLogDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.233+08:00", comments="Source Table: alert_log")
    public static final AlertLog alertLog = new AlertLog();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.233+08:00", comments="Source field: alert_log.id")
    public static final SqlColumn<Long> id = alertLog.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.233+08:00", comments="Source field: alert_log.alarm_id")
    public static final SqlColumn<Long> alarm_id = alertLog.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.execute_id")
    public static final SqlColumn<Long> execute_id = alertLog.execute_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.way")
    public static final SqlColumn<String> way = alertLog.way;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.recipient")
    public static final SqlColumn<String> recipient = alertLog.recipient;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.in_silence")
    public static final SqlColumn<String> in_silence = alertLog.in_silence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.send_status")
    public static final SqlColumn<String> send_status = alertLog.send_status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.alert_type")
    public static final SqlColumn<String> alert_type = alertLog.alert_type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.create_at")
    public static final SqlColumn<Date> create_at = alertLog.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.234+08:00", comments="Source field: alert_log.content")
    public static final SqlColumn<String> content = alertLog.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-28T20:37:43.233+08:00", comments="Source Table: alert_log")
    public static final class AlertLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> execute_id = column("execute_id", JDBCType.BIGINT);

        public final SqlColumn<String> way = column("way", JDBCType.VARCHAR);

        public final SqlColumn<String> recipient = column("recipient", JDBCType.VARCHAR);

        public final SqlColumn<String> in_silence = column("in_silence", JDBCType.VARCHAR);

        public final SqlColumn<String> send_status = column("send_status", JDBCType.VARCHAR);

        public final SqlColumn<String> alert_type = column("alert_type", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public AlertLog() {
            super("alert_log");
        }
    }
}