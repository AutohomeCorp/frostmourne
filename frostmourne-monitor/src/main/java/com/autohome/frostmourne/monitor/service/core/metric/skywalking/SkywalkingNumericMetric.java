package com.autohome.frostmourne.monitor.service.core.metric.skywalking;

import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.service.core.query.ISkywalkingDataQuery;
import org.joda.time.DateTime;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractNumericMetric;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 15:03
 */
public class SkywalkingNumericMetric extends AbstractNumericMetric {

    @Resource
    private ISkywalkingDataQuery skywalkingDataQuery;


    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return skywalkingDataQuery.queryMetricData(start, end, metricContract);
    }
}
