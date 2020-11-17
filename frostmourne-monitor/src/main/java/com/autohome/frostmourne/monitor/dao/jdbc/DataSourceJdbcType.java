package com.autohome.frostmourne.monitor.dao.jdbc;

public enum DataSourceJdbcType {

    MYSQL("com.mysql.cj.jdbc.Driver");

    private String driverClassName;

    DataSourceJdbcType(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

}
