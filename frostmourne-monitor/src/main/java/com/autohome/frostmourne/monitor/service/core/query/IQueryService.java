package com.autohome.frostmourne.monitor.service.core.query;

import java.util.Date;

import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;

public interface IQueryService {

    ElasticsearchDataResult ElasticsearchQuery(String dataName, Date startTime, Date endTime, String esQuery,
                                               String scrollId, String sortOrder, Integer intervalInSeconds);
}
