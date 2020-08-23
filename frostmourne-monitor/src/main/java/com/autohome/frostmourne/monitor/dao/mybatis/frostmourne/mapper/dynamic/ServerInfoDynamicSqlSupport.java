package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ServerInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.992+08:00", comments="Source Table: server_info")
    public static final ServerInfo serverInfo = new ServerInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.992+08:00", comments="Source field: server_info.id")
    public static final SqlColumn<Long> id = serverInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.993+08:00", comments="Source field: server_info.server_name")
    public static final SqlColumn<String> server_name = serverInfo.server_name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.993+08:00", comments="Source field: server_info.remark")
    public static final SqlColumn<String> remark = serverInfo.remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.993+08:00", comments="Source field: server_info.creator")
    public static final SqlColumn<String> creator = serverInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.993+08:00", comments="Source field: server_info.create_at")
    public static final SqlColumn<Date> create_at = serverInfo.create_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.993+08:00", comments="Source field: server_info.modifier")
    public static final SqlColumn<String> modifier = serverInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.993+08:00", comments="Source field: server_info.modify_at")
    public static final SqlColumn<Date> modify_at = serverInfo.modify_at;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-22T23:05:20.992+08:00", comments="Source Table: server_info")
    public static final class ServerInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> server_name = column("server_name", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> create_at = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modify_at = column("modify_at", JDBCType.TIMESTAMP);

        public ServerInfo() {
            super("server_info");
        }
    }
}