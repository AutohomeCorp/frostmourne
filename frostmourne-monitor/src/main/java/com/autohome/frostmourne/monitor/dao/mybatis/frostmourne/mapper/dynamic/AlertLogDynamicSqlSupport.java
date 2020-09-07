package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertLogDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source Table: alert_log")
    public static final AlertLog alertLog = new AlertLog();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.id")
    public static final SqlColumn<Long> id = alertLog.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.alarm_id")
    public static final SqlColumn<Long> alarmId = alertLog.alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.execute_id")
    public static final SqlColumn<Long> executeId = alertLog.executeId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.way")
    public static final SqlColumn<String> way = alertLog.way;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.recipient")
    public static final SqlColumn<String> recipient = alertLog.recipient;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.in_silence")
    public static final SqlColumn<String> inSilence = alertLog.inSilence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.send_status")
    public static final SqlColumn<String> sendStatus = alertLog.sendStatus;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.alert_type")
    public static final SqlColumn<String> alertType = alertLog.alertType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.create_at")
    public static final SqlColumn<Date> createAt = alertLog.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source field: alert_log.content")
    public static final SqlColumn<String> content = alertLog.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.978+08:00", comments="Source Table: alert_log")
    public static final class AlertLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> executeId = column("execute_id", JDBCType.BIGINT);

        public final SqlColumn<String> way = column("way", JDBCType.VARCHAR);

        public final SqlColumn<String> recipient = column("recipient", JDBCType.VARCHAR);

        public final SqlColumn<String> inSilence = column("in_silence", JDBCType.VARCHAR);

        public final SqlColumn<String> sendStatus = column("send_status", JDBCType.VARCHAR);

        public final SqlColumn<String> alertType = column("alert_type", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public AlertLog() {
            super("alert_log");
        }
    }
}