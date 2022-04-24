package com.autohome.frostmourne.monitor.dao.jdbc.impl;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.autohome.frostmourne.monitor.dao.jdbc.IDataSourceJdbcManager;
import com.autohome.frostmourne.monitor.dao.jdbc.IJdbcDao;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;

@Repository
public class JdbcDao implements IJdbcDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);

    @Autowired
    private IDataSourceJdbcManager dataSourceJdbcManager;

    @Override
    public List<Map<String, Object>> query(DataNameContract dataNameContract, DataSourceContract dataSourceContract, String sql, Object[] args) {
        try {
            DataSource dataSource = dataSourceJdbcManager.getDataSource(dataSourceContract);
            if (dataSource == null) {
                log.error("JdbcDataQuery.query error: dataSource={}, createFailed!", dataSourceContract);
                return Collections.emptyList();
            }
            return this.query(dataSource, sql, args);
        } catch (Exception e) {
            log.error("JdbcDataQuery.query error: dataSource={}, sql={}, {}", dataSourceContract, sql, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> query(DataSource dataSource, String sql, Object[] args) throws SQLException {
        ResultSet resultSet = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }
            log.debug("execute query: sql={}, args={}", sql, args);
            resultSet = statement.executeQuery();
            List<Map<String, Object>> list = new ArrayList<>(resultSet.getRow());
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<>(columnCount * 2);
                for (int i = 0; i < columnCount; i++) {
                    row.put(metaData.getColumnName(i + 1), resultSet.getObject(i + 1));
                }
                list.add(row);
            }
            return list;
        } finally {
            this.close(resultSet);
        }
    }

    private void close(AutoCloseable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            log.error("close error: {}", e.getMessage(), e);
        }
    }

}
