package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RecipientDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source Table: recipient")
    public static final Recipient recipient = new Recipient();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source field: recipient.id")
    public static final SqlColumn<Long> id = recipient.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source field: recipient.alarm_id")
    public static final SqlColumn<Long> alarm_id = recipient.alarm_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source field: recipient.alert_id")
    public static final SqlColumn<Long> alert_id = recipient.alert_id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source field: recipient.account")
    public static final SqlColumn<String> account = recipient.account;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source field: recipient.create_at")
    public static final SqlColumn<Date> create_at = recipient.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.801+08:00", comments="Source Table: recipient")
    public static final class Recipient extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarm_id = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> alert_id = column("alert_id", JDBCType.BIGINT);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public Recipient() {
            super("recipient");
        }
    }
}