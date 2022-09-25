package com.autohome.frostmourne.monitor.service.admin;

import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataOption;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.model.contract.TreeDataOption;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;

public interface IDataAdminService {

    DataSourceContract findDatasourceById(Long id);

    boolean saveDataSource(String account, DataSourceContract dataSourceContract);

    boolean removeDataSource(Long id);

    boolean saveDataName(String account, DataNameContract dataNameContract);

    boolean removeDataName(Long datanameId);

    PagerContract<DataSourceContract> findDatasource(int pageIndex, int pageSize, DataSourceType datasourceType);

    List<DataSource> findDataSourceByType(DataSourceType datasourceType);

    Map<Long, DataSource> mapDataSourceByIds(List<Long> dataSourceIds);

    PagerContract<DataNameContract> findDataName(int pageIndex, int pageSize, DataSourceType datasourceType,
                                                 Long datasourceId, String nameHint);

    List<DataOption> dataOptions();

    List<TreeDataOption> listDataOptions();

    DataNameContract findDataNameByName(String name);

    Map<String, DataNameContract> mapDataNameByNames(List<String> names);

    List<DataNameContract> findDataNameByType(DataSourceType datasourceType);
}
