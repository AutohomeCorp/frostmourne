package com.autohome.frostmourne.monitor.service.core.metric.jdbc;

import java.util.Map;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractSameTimeMetric;
import com.autohome.frostmourne.monitor.service.core.query.ISqlServerDataQuery;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlServerSameTimeMetric extends AbstractSameTimeMetric {

    @Autowired
    private ISqlServerDataQuery sqlServerDataQuery;

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return sqlServerDataQuery.queryMetricValue(start, end, metricContract);
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.sqlserver.name());
    }
}
