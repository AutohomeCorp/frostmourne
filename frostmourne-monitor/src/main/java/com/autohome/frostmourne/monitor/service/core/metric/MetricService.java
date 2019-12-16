package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    @Resource
    private Map<String, IMetric> elasticsearchMetricMap;

    public IMetric findMetric(String dataSourceType, String metricType) {
        if (dataSourceType.equalsIgnoreCase("elasticsearch")) {
            if (!elasticsearchMetricMap.containsKey(metricType)) {
                throw new RuntimeException("not support elasticsearch metricType: " + metricType);
            }
            return elasticsearchMetricMap.get(metricType);
        }

        throw new IllegalArgumentException(String.format("unknown dataSourceType:  %s, metricType: %s", dataSourceType, metricType));
    }
}
