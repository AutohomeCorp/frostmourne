package com.autohome.frostmourne.monitor.service.core.query.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.prometheus.IPrometheusDao;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusData;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.query.IPrometheusDataQuery;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class PrometheusDataQuery implements IPrometheusDataQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrometheusData.class);

    @Resource
    private IPrometheusDao prometheusDao;

    public MetricData queryMetricData(DateTime start, DateTime end, MetricContract metricContract) {
        String prometheusEndpoint = metricContract.getDataNameContract().getSettings().get("prometheusEndpoint");
        String user = metricContract.getDataSourceContract().getSettings().get("username");
        String password = metricContract.getDataSourceContract().getSettings().get("password");
        String addr = metricContract.getDataSourceContract().getServiceAddress();
        MetricData metricData = new MetricData();
        if (prometheusEndpoint.equalsIgnoreCase("/api/v1/query")) {
            PrometheusResponse<MetricValue> prometheusResponse = prometheusDao.query(user, password, addr, metricContract.getQueryString());
            if (prometheusResponse.getStatus().equalsIgnoreCase("success")) {
                metricData.setMetricValue(prometheusResponse.getData().getResult().size());
            } else {
                metricData.setMetricValue("-1");
                LOGGER.error("error when query prometheus, metric: {}", JacksonUtil.serialize(metricContract));
            }
            metricData.setLatestDocument(JacksonUtil.mapper().convertValue(prometheusResponse, new TypeReference<Map<String, Object>>() {}));
            return metricData;
        }

        throw new RuntimeException("unknown prometheus endpoint: " + prometheusEndpoint);
    }
}
