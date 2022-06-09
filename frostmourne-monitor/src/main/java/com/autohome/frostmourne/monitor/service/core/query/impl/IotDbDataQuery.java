package com.autohome.frostmourne.monitor.service.core.query.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.iotdb.IIotDbDao;
import com.autohome.frostmourne.monitor.dao.iotdb.domain.IotDbRestResponse;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.query.IIotDbDataQuery;
import org.springframework.stereotype.Service;

@Service
public class IotDbDataQuery implements IIotDbDataQuery {

    @Resource
    private IIotDbDao iotDbDao;

    @Override
    public MetricData querySql(MetricContract metricContract) {
        String user = metricContract.getDataSourceContract().getSettings().get("username");
        String password = metricContract.getDataSourceContract().getSettings().get("password");
        IotDbRestResponse iotDbRestResponse =
            iotDbDao.query(user, password, metricContract.getDataSourceContract().getServiceAddress(), metricContract.getQueryString());
        MetricData metricData = new MetricData();
        metricData.setLatestDocument(JacksonUtil.toMap(iotDbRestResponse));
        return metricData;
    }
}
