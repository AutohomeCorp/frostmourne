package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ServiceInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.188+08:00", comments="Source Table: service_info")
    public static final ServiceInfo serviceInfo = new ServiceInfo();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.188+08:00", comments="Source field: service_info.id")
    public static final SqlColumn<Long> id = serviceInfo.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.188+08:00", comments="Source field: service_info.service_name")
    public static final SqlColumn<String> serviceName = serviceInfo.serviceName;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.189+08:00", comments="Source field: service_info.remark")
    public static final SqlColumn<String> remark = serviceInfo.remark;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.189+08:00", comments="Source field: service_info.owner")
    public static final SqlColumn<String> owner = serviceInfo.owner;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.189+08:00", comments="Source field: service_info.creator")
    public static final SqlColumn<String> creator = serviceInfo.creator;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.189+08:00", comments="Source field: service_info.create_at")
    public static final SqlColumn<Date> createAt = serviceInfo.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.189+08:00", comments="Source field: service_info.modifier")
    public static final SqlColumn<String> modifier = serviceInfo.modifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.189+08:00", comments="Source field: service_info.modify_at")
    public static final SqlColumn<Date> modifyAt = serviceInfo.modifyAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.188+08:00", comments="Source Table: service_info")
    public static final class ServiceInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> serviceName = column("service_name", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<String> owner = column("owner", JDBCType.VARCHAR);

        public final SqlColumn<String> creator = column("creator", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public final SqlColumn<String> modifier = column("modifier", JDBCType.VARCHAR);

        public final SqlColumn<Date> modifyAt = column("modify_at", JDBCType.TIMESTAMP);

        public ServiceInfo() {
            super("service_info");
        }
    }
}