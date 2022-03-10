package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ShortLinkDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.204+08:00", comments="Source Table: short_link")
    public static final ShortLink shortLink = new ShortLink();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.204+08:00", comments="Source field: short_link.id")
    public static final SqlColumn<Long> id = shortLink.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.205+08:00", comments="Source field: short_link.long_link")
    public static final SqlColumn<String> longLink = shortLink.longLink;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.205+08:00", comments="Source field: short_link.create_at")
    public static final SqlColumn<Date> createAt = shortLink.createAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.204+08:00", comments="Source Table: short_link")
    public static final class ShortLink extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> longLink = column("long_link", JDBCType.VARCHAR);

        public final SqlColumn<Date> createAt = column("create_at", JDBCType.TIMESTAMP);

        public ShortLink() {
            super("short_link");
        }
    }
}