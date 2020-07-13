package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    @Resource
    private Map<String, IMetric> elasticsearchMetricMap;

    @Resource
    private HttpMetric httpMetric;

    public IMetric findMetric(String dataSourceType, String metricType) {
        if (dataSourceType.equalsIgnoreCase("elasticsearch")) {
            if (!elasticsearchMetricMap.containsKey(metricType)) {
                throw new RuntimeException("not support elasticsearch metricType: " + metricType);
            }
            return elasticsearchMetricMap.get(metricType);
        } else if (dataSourceType.equalsIgnoreCase("http")) {
            return httpMetric;
        }

        throw new IllegalArgumentException(String.format("unknown dataSourceType:  %s, metricType: %s", dataSourceType, metricType));
    }
}
