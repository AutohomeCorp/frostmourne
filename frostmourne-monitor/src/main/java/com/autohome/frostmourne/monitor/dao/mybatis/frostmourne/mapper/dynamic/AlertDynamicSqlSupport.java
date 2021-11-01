package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.466+08:00", comments="Source Table: alert")
    public static final Alert alert = new Alert();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.466+08:00", comments="Source field: alert.id")
    public static final SqlColumn<Long> id = alert.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.466+08:00", comments="Source field: alert.alarm_id")
    public static final SqlColumn<Long> alarmId = alert.alarmId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.466+08:00", comments="Source field: alert.ways")
    public static final SqlColumn<String> ways = alert.ways;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.silence")
    public static final SqlColumn<Long> silence = alert.silence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.creator")
    public static final SqlColumn<String> creator = alert.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.create_at")
    public static final SqlColumn<Date> createAt = alert.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.allow_sms_from")
    public static final SqlColumn<Integer> allowSmsFrom = alert.allowSmsFrom;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.allow_sms_to")
    public static final SqlColumn<Integer> allowSmsTo = alert.allowSmsTo;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.ding_robot_hook")
    public static final SqlColumn<String> dingRobotHook = alert.dingRobotHook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.http_post_url")
    public static final SqlColumn<String> httpPostUrl = alert.httpPostUrl;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.wechat_robot_hook")
    public static final SqlColumn<String> wechatRobotHook = alert.wechatRobotHook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.467+08:00", comments="Source field: alert.feishu_robot_hook")
    public static final SqlColumn<String> feishuRobotHook = alert.feishuRobotHook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-10-30T15:40:15.466+08:00", comments="Source Table: alert")
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

        public Alert() {
            super("alert");
        }
    }
}