package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ShortLinkDynamicSqlSupport {
    public static final ShortLink shortLink = new ShortLink();

    /**
     * 自增主键
     */
    public static final SqlColumn<Long> id = shortLink.id;

    /**
     * 监控数据类型。(http: http监控；其他值: 关联data_name表)
     */
    public static final SqlColumn<String> longLink = shortLink.longLink;

    /**
     * 创建时间
     */
    public static final SqlColumn<Date> createAt = shortLink.createAt;

    public static final class ShortLink extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> longLink = column("long_link", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public ShortLink() {
            super("short_link");
        }
    }
}