package com.autohome.frostmourne.monitor.service.core.query;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.dao.influxdb.IInfluxdbDao;
import com.autohome.frostmourne.monitor.dao.influxdb.InfluxdbResponse;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InfluxdbDataQuery implements IInfluxdbDataQuery {

    private final static Logger LOGGER = LoggerFactory.getLogger(InfluxdbDataQuery.class);

    @Resource
    private IInfluxdbDao influxdbDao;

    @Override
    public MetricData queryMetricData(DateTime start, DateTime end, MetricContract metricContract) {
        String influxDbAddress = metricContract.getDataSourceContract().getServiceAddress();
        String db = metricContract.getDataNameContract().getSettings().get("INFLUX_DB");
        String query = metricContract.getQueryString();
        InfluxdbResponse influxdbResponse = influxdbDao.query(influxDbAddress, db, query);
        MetricData metricData = new MetricData();
        try {
            Object count = influxdbResponse.getResults().get(0).getSeries().get(0).getValues().get(0).get(1);
            metricData.setMetricValue(count);
        } catch (Exception ex) {
            LOGGER.error("error when get value from influxdb response", ex);
            throw ex;
        }
        return metricData;
    }
}
