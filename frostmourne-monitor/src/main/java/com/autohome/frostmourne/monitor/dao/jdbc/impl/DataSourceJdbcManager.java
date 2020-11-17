package com.autohome.frostmourne.monitor.dao.jdbc.impl;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.dao.jdbc.DataSourceJdbcType;
import com.autohome.frostmourne.monitor.dao.jdbc.IDataSourceJdbcManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceJdbcManager implements IDataSourceJdbcManager {

    private static final Logger log = LoggerFactory.getLogger(DataSourceJdbcManager.class);

    private static final String DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";

    private ConcurrentHashMap<String, DruidDataSource> dataSourceCache;

    @Override
    public void init() {
        if (this.dataSourceCache == null) {
            synchronized (DataSourceJdbcManager.class) {
                if (this.dataSourceCache == null) {
                    this.dataSourceCache = new ConcurrentHashMap<>();
                }
            }
        }
    }

    @Override
    public void close() {
        if (this.dataSourceCache == null) {
            return;
        }
        this.dataSourceCache.forEach((k, v) -> {
            if (v != null) {
                this.closeDataSource(v);
            }
        });
    }

    private String parseCacheKey(DataSourceContract dataSourceContract) {
        return dataSourceContract.getDatasourceName();
    }

    @Override
    public synchronized DataSource getDataSource(DataSourceContract dataSourceContract) throws SQLException {
        log.debug("DataSourceJdbcManager.getDataSource: {}", dataSourceContract);
        String key = this.parseCacheKey(dataSourceContract);
        DruidDataSource dataSource = dataSourceCache.get(key);
        if (dataSource == null) {
            dataSource = this.createDataSource(dataSourceContract);
            dataSourceCache.put(key, dataSource);
        }
        return dataSource;
    }

    @Override
    public synchronized boolean putDataSource(DataSourceContract dataSourceContract) {
        // TODO 修改dataSourceName时会有问题，旧的key会一直保留
        log.debug("DataSourceJdbcManager.putDataSource: {}", dataSourceContract);
        try {
            String key = this.parseCacheKey(dataSourceContract);
            DruidDataSource dataSource = dataSourceCache.remove(key);
            if (dataSource != null) {
                this.closeDataSource(dataSource);
            }
            dataSourceCache.put(key, this.createDataSource(dataSourceContract));
            return true;
        } catch (Exception e) {
            log.error("DataSourceJdbcManager.addDataSource failed: dataSource={}, error={}",
                    dataSourceContract, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public synchronized void removeDataSource(DataSourceContract dataSourceContract) {
        log.debug("DataSourceJdbcManager.removeDataSource: {}", dataSourceContract);
        DruidDataSource dataSource = dataSourceCache.remove(this.parseCacheKey(dataSourceContract));
        if (dataSource != null) {
            this.closeDataSource(dataSource);
        }
    }

    private void closeDataSource(DruidDataSource dataSource) {
        try {
            dataSource.clone();
        } catch (Exception e) {
            log.error("DataSourceJdbcManager.closeDataSource failed: {}", e.getMessage(), e);
        }
    }

    private DruidDataSource createDataSource(DataSourceContract dataSourceContract) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DataSourceJdbcType.MYSQL.getDriverClassName());
        dataSource.setUrl(dataSourceContract.getServiceAddress());
        dataSource.setUsername(dataSourceContract.getSettings().get("username"));
        dataSource.setPassword(dataSourceContract.getSettings().get("password"));
        // 初始化连接
        dataSource.init();
        return dataSource;
    }

}
