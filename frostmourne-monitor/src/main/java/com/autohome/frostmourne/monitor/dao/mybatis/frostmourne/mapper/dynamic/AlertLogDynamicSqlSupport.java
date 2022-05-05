package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.AlertType;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertLogDynamicSqlSupport {
    public static final AlertLog alertLog = new AlertLog();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alertLog.id;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = alertLog.alarmId;

    /**
     * 监控执行ID
     */
    public static final SqlColumn<Long> executeId = alertLog.executeId;

    /**
     * 报警方式
     */
    public static final SqlColumn<String> way = alertLog.way;

    /**
     * 报警接收人
     */
    public static final SqlColumn<String> recipient = alertLog.recipient;

    /**
     * 是否在静默期(YES,NO)
     */
    public static final SqlColumn<String> inSilence = alertLog.inSilence;

    /**
     * 发送状态(NONE,SUCCESS,FAIL,FORBID)
     */
    public static final SqlColumn<String> sendStatus = alertLog.sendStatus;

    /**
     * 消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)
     */
    public static final SqlColumn<AlertType> alertType = alertLog.alertType;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = alertLog.createAt;

    /**
     * 报警内容
     */
    public static final SqlColumn<String> content = alertLog.content;

    public static final class AlertLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> executeId = column("execute_id", JDBCType.BIGINT);

        public final SqlColumn<String> way = column("way", JDBCType.VARCHAR);

        public final SqlColumn<String> recipient = column("recipient", JDBCType.VARCHAR);

        public final SqlColumn<String> inSilence = column("in_silence", JDBCType.VARCHAR);

        public final SqlColumn<String> sendStatus = column("send_status", JDBCType.VARCHAR);

        public final SqlColumn<AlertType> alertType = column("alert_type", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> content = column("content", JDBCType.LONGVARCHAR);

        public AlertLog() {
            super("alert_log");
        }
    }
}