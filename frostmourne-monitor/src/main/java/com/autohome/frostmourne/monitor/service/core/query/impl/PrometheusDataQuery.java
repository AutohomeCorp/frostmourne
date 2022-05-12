package com.autohome.frostmourne.monitor.service.core.query.impl;

import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.prometheus.IPrometheusDao;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.query.IPrometheusDataQuery;
import com.fasterxml.jackson.core.type.TypeReference;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
public class PrometheusDataQuery implements IPrometheusDataQuery {

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
                metricData.setMetricValue(prometheusResponse.getData().getResult().get(0).getValue().get(1));
            } else {
                metricData.setMetricValue("-1");
            }
            metricData.setLatestDocument(JacksonUtil.mapper().convertValue(prometheusResponse, new TypeReference<Map<String, Object>>() {}));
            return metricData;
        }

        throw new RuntimeException("unknown prometheus endpoint: " + prometheusEndpoint);
    }
}
