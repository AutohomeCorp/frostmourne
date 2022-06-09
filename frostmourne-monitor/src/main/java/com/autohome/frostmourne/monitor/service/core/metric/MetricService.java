package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.List;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    @Autowired
    private List<IMetric> metricList;

    @Override
    public IMetric findMetric(DataSourceType dataSourceType, String metricType) {
        for (IMetric metric : metricList) {
            if (metric.matchDataSourceType(dataSourceType.name()) && metric.matchMetricType(metricType)) {
                return metric;
            }
        }

        throw new IllegalArgumentException(String.format("unknown dataSourceType:  %s, metricType: %s", dataSourceType, metricType));
    }

}
