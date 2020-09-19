package com.autohome.frostmourne.monitor.dao.influxdb;

public interface IInfluxdbDao {
    InfluxdbResponse query(String influxdbAddress, String db, String query);
}
