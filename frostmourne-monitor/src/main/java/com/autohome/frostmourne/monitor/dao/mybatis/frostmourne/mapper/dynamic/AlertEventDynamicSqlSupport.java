package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.AlertType;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertEventDynamicSqlSupport {
    public static final AlertEvent alertEvent = new AlertEvent();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alertEvent.id;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = alertEvent.alarmId;

    /**
     * 消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)
     */
    public static final SqlColumn<AlertType> alertType = alertEvent.alertType;

    /**
     * 是否在静默期
     */
    public static final SqlColumn<Boolean> inSilence = alertEvent.inSilence;

    /**
     * 创建时间
     */
    public static final SqlColumn<LocalDateTime> createAt = alertEvent.createAt;

    /**
     * 摘要md5
     */
    public static final SqlColumn<String> eventMd5 = alertEvent.eventMd5;

    public static final class AlertEvent extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<AlertType> alertType = column("alert_type", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> inSilence = column("in_silence", JDBCType.TINYINT);

        public final SqlColumn<LocalDateTime> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> eventMd5 = column("event_md5", JDBCType.LONGVARCHAR);

        public AlertEvent() {
            super("alert_event");
        }
    }
}