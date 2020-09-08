package com.autohome.frostmourne.monitor.transform;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;
import com.fasterxml.jackson.core.type.TypeReference;
import org.elasticsearch.common.Strings;

public class DataSourceTransformer {

    public static DataSourceContract model2Contract(DataSource dataSource) {
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setDatasourceName(dataSource.getDatasource_name());
        dataSourceContract.setDatasourceType(dataSource.getDatasource_type());
        dataSourceContract.setId(dataSource.getId());
        dataSourceContract.setServiceAddress(dataSource.getService_address());
        if (!Strings.isNullOrEmpty(dataSource.getProperties())) {
            dataSourceContract.setSettings(JacksonUtil.deSerialize(dataSource.getProperties(), new TypeReference<Map<String, String>>() {
            }));
        } else {
            dataSourceContract.setSettings(new HashMap<>());
        }
        dataSourceContract.setCreate_at(dataSource.getCreate_at());
        dataSourceContract.setCreator(dataSource.getCreator());
        dataSourceContract.setModifier(dataSource.getModifier());
        dataSourceContract.setModify_at(dataSource.getModify_at());
        return dataSourceContract;
    }
}
