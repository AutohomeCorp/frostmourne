package com.autohome.frostmourne.monitor.service.admin;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataOption;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.TreeDataOption;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;

public interface IDataAdminService {

    DataSourceContract findDatasourceById(Long id);

    boolean saveDataSource(String account, DataSourceContract dataSourceContract);

    boolean removeDataSource(Long id);

    boolean saveDataName(String account, DataNameContract dataNameContract);

    boolean removeDataName(Long datanameId);

    PagerContract<DataSourceContract> findDatasource(int pageIndex, int pageSize, String datasourceType);

    List<DataSource> findDataSourceByType(String datasourceType);

    Map<Long, DataSource> mapDataSourceByIds(List<Long> dataSourceIds);

    PagerContract<DataNameContract> findDataName(int pageIndex, int pageSize, String datasourceType, Long datasourceId);

    List<DataOption> dataOptions();

    List<TreeDataOption> listDataOptions();

    DataNameContract findDataNameByName(String name);

    Map<String, DataNameContract> mapDataNameByNames(List<String> names);

    List<DataNameContract> findDataNameByType(String datasourceType);
}
