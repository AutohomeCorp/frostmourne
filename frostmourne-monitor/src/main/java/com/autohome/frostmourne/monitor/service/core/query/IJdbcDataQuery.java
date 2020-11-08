package com.autohome.frostmourne.monitor.service.core.query;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;

public interface IJdbcDataQuery {

    List<Map<String, Object>> query(DataNameContract dataNameContract,
                                    DataSourceContract dataSourceContract,
                                    String sql);

}
