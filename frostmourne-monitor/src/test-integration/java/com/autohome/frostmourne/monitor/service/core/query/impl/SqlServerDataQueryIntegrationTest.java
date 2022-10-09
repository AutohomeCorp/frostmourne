package com.autohome.frostmourne.monitor.service.core.query.impl;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import com.autohome.frostmourne.common.exception.DataQueryException;
import com.autohome.frostmourne.monitor.IntegrationTest;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.google.common.collect.ImmutableMap;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

class SqlServerDataQueryIntegrationTest extends IntegrationTest {

    @Resource
    private SqlServerDataQuery sqlServerDataQuery;

    @Test
    public void metricDataTest() throws DataQueryException {
        MetricContract metricContract = new MetricContract();
        metricContract.setQueryString("select * from t");
        DataNameContract dataNameContract = new DataNameContract();
        dataNameContract.setTimestampField("time");
        metricContract.setDataNameContract(dataNameContract);
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setServiceAddress("jdbc:sqlserver://127.0.0.1:1433;database=dd");
        dataSourceContract.setDatasourceName("TEST");
        dataSourceContract.setSettings(ImmutableMap.of("username", "root", "password", "123456"));
        metricContract.setDataSourceContract(dataSourceContract);
        MetricData metricData = sqlServerDataQuery.queryMetricValue(DateTime.parse("1900-06-29"), DateTime.parse("2022-06-30"), metricContract);
    }

}
