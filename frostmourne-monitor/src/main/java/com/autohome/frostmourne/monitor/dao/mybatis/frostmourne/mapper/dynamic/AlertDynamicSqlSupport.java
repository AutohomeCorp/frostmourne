package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertDynamicSqlSupport {
    public static final Alert alert = new Alert();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = alert.id;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = alert.alarmId;

    /**
     * 报警方式(sms,dingding,email,http_post,wechat)
     */
    public static final SqlColumn<String> ways = alert.ways;

    /**
     * 静默时间，单位：分钟
     */
    public static final SqlColumn<Long> silence = alert.silence;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = alert.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = alert.createAt;

    /**
     * 短信允许发送开始时间，[0,23]
     */
    public static final SqlColumn<Integer> allowSmsFrom = alert.allowSmsFrom;

    /**
     * 短信允许发送结束时间，[0,23]
     */
    public static final SqlColumn<Integer> allowSmsTo = alert.allowSmsTo;

    /**
     * 钉钉机器人hook地址
     */
    public static final SqlColumn<String> dingRobotHook = alert.dingRobotHook;

    /**
     * http post报警方式地址
     */
    public static final SqlColumn<String> httpPostUrl = alert.httpPostUrl;

    /**
     * 企业微信机器人hook地址
     */
    public static final SqlColumn<String> wechatRobotHook = alert.wechatRobotHook;

    /**
     * 飞书机器人hook地址
     */
    public static final SqlColumn<String> feishuRobotHook = alert.feishuRobotHook;

    /**
     * 静默判断表达式
     */
    public static final SqlColumn<String> silenceExpression = alert.silenceExpression;

    public static final class Alert extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<String> ways = column("ways", JDBCType.VARCHAR);

        public final SqlColumn<Long> silence = column("silence", JDBCType.BIGINT);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> allowSmsFrom = column("allow_sms_from", JDBCType.INTEGER);

        public final SqlColumn<Integer> allowSmsTo = column("allow_sms_to", JDBCType.INTEGER);

        public final SqlColumn<String> dingRobotHook = column("ding_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> httpPostUrl = column("http_post_url", JDBCType.VARCHAR);

        public final SqlColumn<String> wechatRobotHook = column("wechat_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> feishuRobotHook = column("feishu_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> silenceExpression = column("silence_expression", JDBCType.VARCHAR);

        public Alert() {
            super("alert");
        }
    }
}