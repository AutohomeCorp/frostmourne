package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.AlarmStatus;
import com.autohome.frostmourne.monitor.model.enums.ExecuteStatus;
import com.autohome.frostmourne.monitor.model.enums.RecoverNoticeStatus;
import com.autohome.frostmourne.monitor.model.enums.RiskLevel;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlarmDynamicSqlSupport {
    public static final Alarm alarm = new Alarm();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alarm.id;

    /**
     * 监控名称
     */
    public static final SqlColumn<String> alarmName = alarm.alarmName;

    /**
     * 监控数据类型。(http: http监控；其他值: 关联data_name表)
     */
    public static final SqlColumn<String> alarmType = alarm.alarmType;

    /**
     * 监控描述
     */
    public static final SqlColumn<String> description = alarm.description;

    /**
     * 所属对象关键字
     */
    public static final SqlColumn<String> ownerKey = alarm.ownerKey;

    /**
     * 开关状态（OPEN,CLOSE）
     */
    public static final SqlColumn<AlarmStatus> status = alarm.status;

    /**
     * 最近一次执行结果
     */
    public static final SqlColumn<ExecuteStatus> executeResult = alarm.executeResult;

    /**
     * 最近一次执行时间
     */
    public static final SqlColumn<Date> executeAt = alarm.executeAt;

    /**
     * 调度任务id
     */
    public static final SqlColumn<Long> jobId = alarm.jobId;

    /**
     * cron表达式
     */
    public static final SqlColumn<String> cron = alarm.cron;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = alarm.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = alarm.createAt;

    /**
     * 修改人
     */
    public static final SqlColumn<String> modifier = alarm.modifier;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = alarm.modifyAt;

    /**
     * 监控所属团队
     */
    public static final SqlColumn<String> teamName = alarm.teamName;

    /**
     * 风险等级。info: 提示；important: 重要；emergency: 紧急； crash: 我崩了
     */
    public static final SqlColumn<RiskLevel> riskLevel = alarm.riskLevel;

    /**
     * 服务ID
     */
    public static final SqlColumn<Long> serviceId = alarm.serviceId;

    /**
     * 恢复通知开关（OPEN,CLOSE）
     */
    public static final SqlColumn<RecoverNoticeStatus> recoverNoticeStatus = alarm.recoverNoticeStatus;

    /**
     * 上次调度时间
     */
    public static final SqlColumn<Long> triggerLastTime = alarm.triggerLastTime;

    /**
     * 下次调度时间
     */
    public static final SqlColumn<Long> triggerNextTime = alarm.triggerNextTime;

    public static final class Alarm extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> alarmName = column("alarm_name", JDBCType.VARCHAR);

        public final SqlColumn<String> alarmType = column("alarm_type", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> ownerKey = column("owner_key", JDBCType.VARCHAR);

        public final SqlColumn<AlarmStatus> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<ExecuteStatus> executeResult = column("execute_result", JDBCType.VARCHAR);

        public final SqlColumn<Date> executeAt = column("execute_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> jobId = column("job_id", JDBCType.BIGINT);

        public final SqlColumn<String> cron = column("cron", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> teamName = column("team_name", JDBCType.VARCHAR);

        public final SqlColumn<RiskLevel> riskLevel = column("risk_level", JDBCType.VARCHAR);

        public final SqlColumn<Long> serviceId = column("service_id", JDBCType.BIGINT);

        public final SqlColumn<RecoverNoticeStatus> recoverNoticeStatus = column("recover_notice_status", JDBCType.VARCHAR);

        public final SqlColumn<Long> triggerLastTime = column("trigger_last_time", JDBCType.BIGINT);

        public final SqlColumn<Long> triggerNextTime = column("trigger_next_time", JDBCType.BIGINT);

        public Alarm() {
            super("alarm");
        }
    }
}