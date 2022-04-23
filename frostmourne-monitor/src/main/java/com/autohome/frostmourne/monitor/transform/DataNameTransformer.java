package com.autohome.frostmourne.monitor.transform;

import java.util.Map;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataName;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataNameTransformer {

    public static DataNameContract model2Contract(DataName dataName) {
        DataNameContract dataNameContract = new DataNameContract();
        dataNameContract.setDataName(dataName.getDataName());
        dataNameContract.setDataSourceId(dataName.getDataSourceId());
        dataNameContract.setDisplayName(dataName.getDisplayName());
        dataNameContract.setDatasourceType(dataName.getDatasourceType());
        dataNameContract.setCreator(dataName.getCreator());
        dataNameContract.setSettings(JacksonUtil.deSerialize(dataName.getProperties(), new TypeReference<Map<String, String>>() {}));
        dataNameContract.setTimestampField(dataName.getTimestampField());
        return dataNameContract;
    }
}
