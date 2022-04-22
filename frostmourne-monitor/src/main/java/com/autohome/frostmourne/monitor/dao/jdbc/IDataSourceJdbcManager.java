package com.autohome.frostmourne.monitor.dao.jdbc;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;

public interface IDataSourceJdbcManager {

    void init();

    void close();

    DataSource getDataSource(DataSourceContract dataSourceContract) throws SQLException;

    boolean putDataSource(DataSourceContract dataSourceContract);

    void removeDataSource(DataSourceContract dataSourceContract);

}
