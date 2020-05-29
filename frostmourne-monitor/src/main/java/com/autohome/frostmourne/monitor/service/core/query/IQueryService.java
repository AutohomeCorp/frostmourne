package com.autohome.frostmourne.monitor.service.core.query;

import java.util.Date;

import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import org.joda.time.DateTime;

public interface IQueryService {

    ElasticsearchDataResult elasticsearchQuery(String dataName, Date startTime, Date endTime, String esQuery,
                                               String scrollId, String sortOrder, Integer intervalInSeconds);

    ElasticsearchDataResult elasticsearchQuery(DataNameContract dataNameContract, DataSourceContract dataSourceContract,
                                               DateTime startTime, DateTime endTime, String esQuery, String scrollId,
                                               String sortOrder);
}
