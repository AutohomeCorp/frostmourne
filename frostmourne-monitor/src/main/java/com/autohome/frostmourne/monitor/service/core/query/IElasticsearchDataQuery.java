package com.autohome.frostmourne.monitor.service.core.query;

import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import org.joda.time.DateTime;

public interface IElasticsearchDataQuery {

    ElasticsearchDataResult query(DataNameContract dataNameContract, DataSourceContract dataSourceContract,
                                  DateTime start, DateTime end, String esQuery,
                                  String scrollId, String sortOrder, Integer intervalInSeconds);
}
