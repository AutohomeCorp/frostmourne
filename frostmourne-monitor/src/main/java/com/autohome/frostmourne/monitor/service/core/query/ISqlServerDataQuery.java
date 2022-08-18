package com.autohome.frostmourne.monitor.service.core.query;

import com.autohome.frostmourne.common.exception.DataQueryException;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import org.joda.time.DateTime;

public interface ISqlServerDataQuery {

    MetricData queryMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws DataQueryException;

    MetricData querySql(MetricContract metricContract) throws DataQueryException;
}
