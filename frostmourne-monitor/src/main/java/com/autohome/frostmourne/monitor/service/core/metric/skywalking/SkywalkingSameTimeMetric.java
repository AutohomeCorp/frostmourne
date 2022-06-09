package com.autohome.frostmourne.monitor.service.core.metric.skywalking;

import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.core.metric.AbstractSameTimeMetric;
import org.joda.time.DateTime;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractNumericMetric;
import com.autohome.frostmourne.monitor.service.core.query.ISkywalkingDataQuery;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 15:03
 */
@Service
public class SkywalkingSameTimeMetric extends AbstractSameTimeMetric {

    @Resource
    private ISkywalkingDataQuery skywalkingDataQuery;

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return skywalkingDataQuery.queryMetricData(start, end, metricContract);
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.skywalking.name());
    }
}
