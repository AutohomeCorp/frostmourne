package com.autohome.frostmourne.monitor.transform;

import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;

public class DataSourceTransformer {

    public static DataSourceContract model2Contract(DataSource dataSource) {
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setDatasource_name(dataSource.getDatasource_name());
        dataSourceContract.setDatasource_type(dataSource.getDatasource_type());
        dataSourceContract.setId(dataSource.getId());
        dataSourceContract.setService_address(dataSource.getService_address());
        return dataSourceContract;
    }
}
