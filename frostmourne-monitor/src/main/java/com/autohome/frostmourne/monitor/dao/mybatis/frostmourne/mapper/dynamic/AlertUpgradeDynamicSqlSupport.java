package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.AlarmUpgradeStatus;
import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertUpgradeDynamicSqlSupport {
    public static final AlertUpgrade alertUpgrade = new AlertUpgrade();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alertUpgrade.id;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = alertUpgrade.alarmId;

    /**
     * 状态开关 OPEN:打开  CLOSE:关闭
     */
    public static final SqlColumn<AlarmUpgradeStatus> status = alertUpgrade.status;

    /**
     * 连续报警n次后升级
     */
    public static final SqlColumn<Integer> timesToUpgrade = alertUpgrade.timesToUpgrade;

    /**
     * 报警方式(sms,dingding,email,http_post,wechat)
     */
    public static final SqlColumn<String> ways = alertUpgrade.ways;

    /**
     * 钉钉机器人hook地址
     */
    public static final SqlColumn<String> dingRobotHook = alertUpgrade.dingRobotHook;

    /**
     * http post报警方式地址
     */
    public static final SqlColumn<String> httpPostUrl = alertUpgrade.httpPostUrl;

    /**
     * 企业微信机器人hook地址
     */
    public static final SqlColumn<String> wechatRobotHook = alertUpgrade.wechatRobotHook;

    /**
     * 飞书机器人hook地址
     */
    public static final SqlColumn<String> feishuRobotHook = alertUpgrade.feishuRobotHook;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = alertUpgrade.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<LocalDateTime> createAt = alertUpgrade.createAt;

    public static final class AlertUpgrade extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<AlarmUpgradeStatus> status = column("status", JDBCType.VARCHAR);

        public final SqlColumn<Integer> timesToUpgrade = column("times_to_upgrade", JDBCType.INTEGER);

        public final SqlColumn<String> ways = column("ways", JDBCType.VARCHAR);

        public final SqlColumn<String> dingRobotHook = column("ding_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> httpPostUrl = column("http_post_url", JDBCType.VARCHAR);

        public final SqlColumn<String> wechatRobotHook = column("wechat_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> feishuRobotHook = column("feishu_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> createAt = column("create_at", JDBCType.TIMESTAMP);

        public AlertUpgrade() {
            super("alert_upgrade");
        }
    }
}