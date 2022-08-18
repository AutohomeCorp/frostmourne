package com.autohome.frostmourne.monitor.dao.jdbc;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.common.exception.DataQueryException;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;

public interface IJdbcDao {

    List<Map<String, Object>> query(DataNameContract dataNameContract,
                                    DataSourceContract dataSourceContract,
                                    String sql,
                                    Object[] args) throws DataQueryException;

}
