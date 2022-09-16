package com.autohome.frostmourne.monitor.dao.jdbc;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;

public enum DataSourceJdbcType {

    /**
     * Mysql
     */
    MYSQL("com.mysql.cj.jdbc.Driver"),
    
    /**
     * ClickHouse
     */
    CLICKHOUSE("ru.yandex.clickhouse.ClickHouseDriver"),

    /**
     * SQL Server
     */
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    /**
     * Oracle
     */
    ORACLE("oracle.jdbc.driver.OracleDriver");

    private final String driverClassName;

    DataSourceJdbcType(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public static DataSourceJdbcType fromDataSourceType(DataSourceType dataSourceType) {
        return DataSourceJdbcType.valueOf(dataSourceType.name().toUpperCase());
    }

}
