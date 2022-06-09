package com.autohome.frostmourne.monitor.service.core.metric.iotdb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractObjectMetric;
import com.autohome.frostmourne.monitor.service.core.query.IIotDbDataQuery;
import org.springframework.stereotype.Service;

@Service
public class IotDbObjectMetric extends AbstractObjectMetric {

    @Resource
    private IIotDbDataQuery iotDbDataQuery;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        Map<String, Object> result = new HashMap<>();
        MetricData metricData = iotDbDataQuery.querySql(metricContract);
        if (metricData.getLatestDocument() != null) {
            result.putAll(metricData.getLatestDocument());
        }
        return result;
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.iotdb.name());
    }
}
