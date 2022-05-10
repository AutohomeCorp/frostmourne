package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;

import javax.annotation.Resource;

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
    private HttpMetric httpMetric;

    @Resource
    private PingMetric pingMetric;

    @Override
    public IMetric findMetric(String dataSourceType, String metricType) {
        // TODO 改为枚举
        if ("elasticsearch".equalsIgnoreCase(dataSourceType)) {
            if (!elasticsearchMetricMap.containsKey(metricType)) {
                throw new RuntimeException("not support elasticsearch metricType: " + metricType);
            }
            return elasticsearchMetricMap.get(metricType);
        }
        if ("http".equalsIgnoreCase(dataSourceType)) {
            return httpMetric;
        }
        if ("ping".equalsIgnoreCase(dataSourceType)) {
            return pingMetric;
        }
        if ("influxdb".equalsIgnoreCase(dataSourceType)) {
            if (!influxdbMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported influxdb metricType: " + metricType);
            }
            return influxdbMetricMap.get(metricType);
        }
        if ("mysql".equalsIgnoreCase(dataSourceType)) {
            if (!mysqlMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported mysql metricType: " + metricType);
            }
            return mysqlMetricMap.get(metricType);
        }
        if ("clickhouse".equalsIgnoreCase(dataSourceType)) {
            if (!clickhouseMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported clickhouse metricType: " + metricType);
            }
            return clickhouseMetricMap.get(metricType);
        }
        if("skywalking".equalsIgnoreCase(dataSourceType)) {
            if(!skywalkingMetricMap.containsKey(metricType)) {
                throw new IllegalArgumentException("not supported skywalking metricType: " + metricType);
            }
            return skywalkingMetricMap.get(metricType);
        }

        throw new IllegalArgumentException(String.format("unknown dataSourceType:  %s, metricType: %s", dataSourceType, metricType));
    }
}
