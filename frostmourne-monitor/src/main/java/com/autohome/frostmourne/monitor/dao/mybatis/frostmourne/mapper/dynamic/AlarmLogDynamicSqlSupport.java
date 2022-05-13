package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.model.enums.VerifyResult;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlarmLogDynamicSqlSupport {
    public static final AlarmLog alarmLog = new AlarmLog();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alarmLog.id;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = alarmLog.alarmId;

    /**
     * 监控任务执行开始时间
     */
    public static final SqlColumn<Date> exeStart = alarmLog.exeStart;

    /**
     * 监控任务执行结束时间
     */
    public static final SqlColumn<Date> exeEnd = alarmLog.exeEnd;

    /**
     * 监控任务执行耗时，单位：毫秒
     */
    public static final SqlColumn<Integer> cost = alarmLog.cost;

    /**
     * 执行结果(SUCCESS,ERROR)
     */
    public static final SqlColumn<ExecuteStatus> executeResult = alarmLog.executeResult;

    /**
     * NONE,TRUE,FALSE
     */
    public static final SqlColumn<VerifyResult> verifyResult = alarmLog.verifyResult;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = alarmLog.createAt;

    /**
     * 是否报警
     */
    public static final SqlColumn<Boolean> alert = alarmLog.alert;

    /**
     * 日志消息
     */
    public static final SqlColumn<String> message = alarmLog.message;

    public static final class AlarmLog extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Date> exeStart = column("exe_start", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> exeEnd = column("exe_end", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> cost = column("cost", JDBCType.INTEGER);

        public final SqlColumn<ExecuteStatus> executeResult = column("execute_result", JDBCType.VARCHAR);

        public final SqlColumn<VerifyResult> verifyResult = column("verify_result", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Boolean> alert = column("alert", JDBCType.TINYINT);

        public final SqlColumn<String> message = column("message", JDBCType.LONGVARCHAR);

        public AlarmLog() {
            super("alarm_log");
        }
    }
}