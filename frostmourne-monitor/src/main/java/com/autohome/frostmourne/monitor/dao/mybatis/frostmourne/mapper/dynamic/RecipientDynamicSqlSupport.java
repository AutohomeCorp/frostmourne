package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import com.autohome.frostmourne.monitor.model.enums.RecipientType;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RecipientDynamicSqlSupport {
    public static final Recipient recipient = new Recipient();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = recipient.id;

    /**
     * 监控ID
     */
    public static final SqlColumn<Long> alarmId = recipient.alarmId;

    /**
     * 报警ID
     */
    public static final SqlColumn<Long> alertId = recipient.alertId;

    /**
     * 接收人账号不带邮箱后缀
     */
    public static final SqlColumn<String> account = recipient.account;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = recipient.createAt;

    /**
     * 归属于 ALERT:报警, ALERT_UPGRADE:报警升级
     */
    public static final SqlColumn<RecipientType> type = recipient.type;

    public static final class Recipient extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> alarmId = column("alarm_id", JDBCType.BIGINT);

        public final SqlColumn<Long> alertId = column("alert_id", JDBCType.BIGINT);

        public final SqlColumn<String> account = column("account", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<RecipientType> type = column("type", JDBCType.VARCHAR);

        public Recipient() {
            super("recipient");
        }
    }
}