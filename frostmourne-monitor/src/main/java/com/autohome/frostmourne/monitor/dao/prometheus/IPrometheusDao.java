package com.autohome.frostmourne.monitor.dao.prometheus;

import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;

public interface IPrometheusDao {

    PrometheusResponse<MetricValue> query(String user, String password, String addr, String query);
}
