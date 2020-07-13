package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlertDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source Table: alert")
    public static final Alert alert = new Alert();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source field: alert.id")
    public static final SqlColumn<Long> id = alert.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source field: alert.alarm_id")
    public static final SqlColumn<Long> alarm_id = alert.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source field: alert.ways")
    public static final SqlColumn<String> ways = alert.ways;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source field: alert.silence")
    public static final SqlColumn<Long> silence = alert.silence;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source field: alert.creator")
    public static final SqlColumn<String> creator = alert.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source field: alert.create_at")
    public static final SqlColumn<Date> create_at = alert.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source field: alert.allow_sms_from")
    public static final SqlColumn<Integer> allow_sms_from = alert.allow_sms_from;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source field: alert.allow_sms_to")
    public static final SqlColumn<Integer> allow_sms_to = alert.allow_sms_to;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source field: alert.ding_robot_hook")
    public static final SqlColumn<String> ding_robot_hook = alert.ding_robot_hook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source field: alert.http_post_url")
    public static final SqlColumn<String> http_post_url = alert.http_post_url;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source field: alert.wechat_robot_hook")
    public static final SqlColumn<String> wechat_robot_hook = alert.wechat_robot_hook;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.784+08:00", comments="Source Table: alert")
    public static final class Alert extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<String> ways = column("ways", JDBCType.VARCHAR);

        public final SqlColumn<Long> silence = column("silence", JDBCType.BIGINT);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> allow_sms_from = column("allow_sms_from", JDBCType.INTEGER);

        public final SqlColumn<Integer> allow_sms_to = column("allow_sms_to", JDBCType.INTEGER);

        public final SqlColumn<String> ding_robot_hook = column("ding_robot_hook", JDBCType.VARCHAR);

        public final SqlColumn<String> http_post_url = column("http_post_url", JDBCType.VARCHAR);

        public final SqlColumn<String> wechat_robot_hook = column("wechat_robot_hook", JDBCType.VARCHAR);

        public Alert() {
            super("alert");
        }
    }
}