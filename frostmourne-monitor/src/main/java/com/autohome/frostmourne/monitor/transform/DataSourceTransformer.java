package com.autohome.frostmourne.monitor.transform;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import org.elasticsearch.common.Strings;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataSourceTransformer {

    public static DataSourceContract model2Contract(DataSource dataSource) {
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setDatasourceName(dataSource.getDatasourceName());
        dataSourceContract.setDatasourceType(dataSource.getDatasourceType());
        dataSourceContract.setId(dataSource.getId());
        dataSourceContract.setServiceAddress(dataSource.getServiceAddress());
        if (!Strings.isNullOrEmpty(dataSource.getProperties())) {
            dataSourceContract.setSettings(JacksonUtil.deSerialize(dataSource.getProperties(), new TypeReference<Map<String, String>>() {}));
        } else {
            dataSourceContract.setSettings(new HashMap<>());
        }
        dataSourceContract.setCreateAt(dataSource.getCreateAt());
        dataSourceContract.setCreator(dataSource.getCreator());
        dataSourceContract.setModifier(dataSource.getModifier());
        dataSourceContract.setModifyAt(dataSource.getModifyAt());
        return dataSourceContract;
    }
}
