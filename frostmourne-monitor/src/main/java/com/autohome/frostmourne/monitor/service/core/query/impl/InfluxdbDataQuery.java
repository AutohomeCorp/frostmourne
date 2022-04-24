package com.autohome.frostmourne.monitor.service.core.query.impl;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.dao.influxdb.IInfluxdbDao;
import com.autohome.frostmourne.monitor.dao.influxdb.InfluxdbResponse;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.query.IInfluxdbDataQuery;

@Service
public class InfluxdbDataQuery implements IInfluxdbDataQuery {

    private final static Logger LOGGER = LoggerFactory.getLogger(InfluxdbDataQuery.class);

    @Resource
    private IInfluxdbDao influxdbDao;

    @Override
    public MetricData queryMetricData(DateTime start, DateTime end, MetricContract metricContract) {
        String influxDbAddress = metricContract.getDataSourceContract().getServiceAddress();
        String db = metricContract.getDataNameContract().getSettings().get("database");
        String user = metricContract.getDataSourceContract().getSettings().get("username");
        String password = metricContract.getDataSourceContract().getSettings().get("password");
        String query = buildQuery(start, end, metricContract);
        InfluxdbResponse influxdbResponse = influxdbDao.query(influxDbAddress, db, query, user, password);
        MetricData metricData = new MetricData();
        try {
            Object count = influxdbResponse.getResults().get(0).getSeries().get(0).getValues().get(0).get(1);
            metricData.setMetricValue(count);
        } catch (Exception ex) {
            LOGGER.warn("influxdb response has no data", ex);
            metricData.setMetricValue(0);
        }
        return metricData;
    }

    public String buildQuery(DateTime start, DateTime end, MetricContract metricContract) {
        String queryWithoutTime = metricContract.getQueryString();
        String completeQuery;
        if (queryWithoutTime.toLowerCase().contains("where")) {
            completeQuery = String.format("%s and time >= '%s' and time <= '%s'", queryWithoutTime, start.toDateTime(DateTimeZone.UTC).toString(),
                end.toDateTime(DateTimeZone.UTC).toString());
        } else {
            completeQuery = String.format("%s where time >= '%s' and time <= '%s'", queryWithoutTime, start.toDateTime(DateTimeZone.UTC).toString(),
                end.toDateTime(DateTimeZone.UTC).toString());
        }
        return completeQuery;
    }
}
