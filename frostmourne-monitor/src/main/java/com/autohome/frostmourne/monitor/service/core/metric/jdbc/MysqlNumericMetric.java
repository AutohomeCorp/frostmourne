package com.autohome.frostmourne.monitor.service.core.metric.jdbc;

import java.util.Map;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractNumericMetric;
import com.autohome.frostmourne.monitor.service.core.query.IMysqlDataQuery;

@Service
public class MysqlNumericMetric extends AbstractNumericMetric {

    @Autowired
    protected IMysqlDataQuery mysqlDataQuery;

    @Override
    public MetricData pullMetricData(DateTime start, DateTime end, MetricContract metricContract, Map<String, String> ruleSettings) {
        return mysqlDataQuery.queryMetricValue(start, end, metricContract);
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.mysql.name());
    }
}
