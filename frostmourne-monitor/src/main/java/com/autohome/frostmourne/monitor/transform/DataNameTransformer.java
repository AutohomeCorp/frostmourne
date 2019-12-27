package com.autohome.frostmourne.monitor.transform;

import java.util.Map;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;
import com.fasterxml.jackson.core.type.TypeReference;

public class DataNameTransformer {

    public static DataNameContract model2Contract(DataName dataName) {
        DataNameContract dataNameContract = new DataNameContract();
        dataNameContract.setData_name(dataName.getData_name());
        dataNameContract.setData_source_id(dataName.getData_source_id());
        dataNameContract.setDisplay_name(dataName.getDisplay_name());
        dataNameContract.setDatasource_type(dataName.getDatasource_type());
        dataNameContract.setCreator(dataName.getCreator());
        dataNameContract.setSettings(JacksonUtil.deSerialize(dataName.getProperties(), new TypeReference<Map<String, String>>() {
        }));
        dataNameContract.setTimestamp_field(dataName.getTimestamp_field());
        return dataNameContract;
    }
}
