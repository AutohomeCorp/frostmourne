package com.autohome.frostmourne.monitor.service.core.query;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/9 23:08
 */
public interface ISkywalkingDataQuery {

    MetricData queryMetricData(DateTime start, DateTime end, MetricContract metricContract);
}
