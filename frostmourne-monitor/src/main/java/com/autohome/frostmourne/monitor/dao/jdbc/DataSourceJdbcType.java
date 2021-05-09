package com.autohome.frostmourne.monitor.dao.jdbc;

public enum DataSourceJdbcType {

    /**
     * Mysql
     */
    MYSQL("com.mysql.cj.jdbc.Driver"),
    /**
     * ClickHouse
     */
    CLICKHOUSE("ru.yandex.clickhouse.ClickHouseDriver");

    private String driverClassName;

    DataSourceJdbcType(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

}
