package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ServiceInfoDynamicSqlSupport {
    public static final ServiceInfo serviceInfo = new ServiceInfo();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = serviceInfo.id;

    /**
     * 服务名称
     */
    public static final SqlColumn<String> serviceName = serviceInfo.serviceName;

    /**
     * 备注
     */
    public static final SqlColumn<String> remark = serviceInfo.remark;

    /**
     * 负责人
     */
    public static final SqlColumn<String> owner = serviceInfo.owner;

    /**
     * 创建人
     */
    public static final SqlColumn<String> creator = serviceInfo.creator;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = serviceInfo.createAt;

    /**
     * 修改人
     */
    public static final SqlColumn<String> modifier = serviceInfo.modifier;

    /**
     * 修改时间
     */
    public static final SqlColumn<Date> modifyAt = serviceInfo.modifyAt;

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