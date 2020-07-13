package com.autohome.frostmourne.monitor.service.core.query;

import java.io.IOException;

import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.ElasticsearchMetric;
import org.joda.time.DateTime;

public interface IElasticsearchDataQuery {

    ElasticsearchDataResult query(DataNameContract dataNameContract, DataSourceContract dataSourceContract,
                                  DateTime start, DateTime end, String esQuery,
                                  String scrollId, String sortOrder, Integer intervalInSeconds);

    ElasticsearchMetric queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException;
}
