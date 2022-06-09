package com.autohome.frostmourne.monitor.service.core.metric.elasticsearch;

import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchBucketNumericMetric extends ElasticsearchNumericMetric {

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.bucket_numeric;
    }
}
