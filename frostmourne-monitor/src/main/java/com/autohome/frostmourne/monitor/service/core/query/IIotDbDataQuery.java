package com.autohome.frostmourne.monitor.service.core.query;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;

public interface IIotDbDataQuery {

    MetricData querySql(MetricContract metricContract);
}
