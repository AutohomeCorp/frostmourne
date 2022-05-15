package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    @Resource
    private Map<String, IMetric> elasticsearchMetricMap;

    @Resource
    private Map<String, IMetric> influxdbMetricMap;

    @Resource
    private Map<String, IMetric> mysqlMetricMap;

    @Resource
    private Map<String, IMetric> clickhouseMetricMap;

    @Resource
    private Map<String, IMetric> skywalkingMetricMap;

    @Resource
    private Map<String, IMetric> prometheusMetricMap;

    @Resource
    private HttpMetric httpMetric;

    @Resource
    private PingMetric pingMetric;

    @Override
    public IMetric findMetric(DataSourceType dataSourceType, String metricType) {

        if (DataSourceType.elasticsearch.equals(dataSourceType)) {
            if (!elasticsearchMetricMap.containsKey(metricType)) {
                throw new RuntimeException("not support elasticsearch metricType: " + metricType);
            }
            return elasticsearchMetricMap.get(metricType);
        }
        if (DataSourceType.http.equals(dataSourceType)) {
            return httpMetric;
        }
        if (DataSourceType.ping.equals(dataSourceType)) {
            return pingMetric;
        }
        if (DataSourceType.influxdb.equals(dataSourceType)) {
            if (!influxdbMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported influxdb metricType: " + metricType);
            }
            return influxdbMetricMap.get(metricType);
        }
        if (DataSourceType.mysql.equals(dataSourceType)) {
            if (!mysqlMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported mysql metricType: " + metricType);
            }
            return mysqlMetricMap.get(metricType);
        }
        if (DataSourceType.clickhouse.equals(dataSourceType)) {
            if (!clickhouseMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported clickhouse metricType: " + metricType);
            }
            return clickhouseMetricMap.get(metricType);
        }
        if (DataSourceType.skywalking.equals(dataSourceType)) {
            if (!skywalkingMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported skywalking metricType: " + metricType);
            }
            return skywalkingMetricMap.get(metricType);
        }
        if (DataSourceType.prometheus.equals(dataSourceType)) {
            if (!prometheusMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported prometheus metricType: " + metricType);
            }
            return prometheusMetricMap.get(metricType);
        }

        throw new IllegalArgumentException(String.format("unknown dataSourceType:  %s, metricType: %s", dataSourceType, metricType));
    }
}
