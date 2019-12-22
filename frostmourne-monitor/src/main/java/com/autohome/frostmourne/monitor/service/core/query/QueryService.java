package com.autohome.frostmourne.monitor.service.core.query;

import java.util.Date;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.DataNameContract;
import com.autohome.frostmourne.monitor.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.service.admin.IDataAdminService;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
public class QueryService implements IQueryService {

    private static RangeMap<Long, Integer> rangeMap = TreeRangeMap.create();

    static {
        rangeMap.put(Range.closed(0L, 3600 * 1000L), 5 * 60);
        rangeMap.put(Range.closed(3600 * 1000L + 1, 3 * 3600 * 1000L), 10 * 60);
        rangeMap.put(Range.closed(3 * 3600 * 1000L + 1, 6 * 3600 * 1000L), 15 * 60);
        rangeMap.put(Range.closed(6 * 3600 * 1000L + 1, 12 * 3600 * 1000L), 30 * 60);
        rangeMap.put(Range.closed(12 * 3600 * 1000L + 1, 24 * 3600 * 1000L), 60 * 60);
        rangeMap.put(Range.closed(24 * 3600 * 1000L + 1, 3 * 24 * 3600 * 1000L), 2 * 60 * 60);
        rangeMap.put(Range.closed(3 * 24 * 3600 * 1000L + 1, 5 * 24 * 3600 * 1000L), 6 * 60 * 60);
        rangeMap.put(Range.closed(5 * 24 * 3600 * 1000L + 1, 7 * 24 * 3600 * 1000L), 24 * 60 * 60);
        rangeMap.put(Range.greaterThan(7 * 24 * 3600 * 1000L), 24 * 60 * 60);
    }


    @Resource
    private IElasticsearchDataQuery elasticsearchDataQuery;

    @Resource
    private IDataAdminService dataAdminService;

    public ElasticsearchDataResult ElasticsearchQuery(String dataName, Date startTime, Date endTime, String esQuery,
                                                      String scrollId, String sortOrder, Integer intervalInSeconds) {
        if (intervalInSeconds == null || intervalInSeconds == 0) {
            Long timeGap = endTime.getTime() - startTime.getTime();
            intervalInSeconds = rangeMap.get(timeGap);
        }
        DataNameContract dataNameContract = dataAdminService.findDataNameByName(dataName);
        DataSourceContract dataSourceContract = dataAdminService.findDatasourceById(dataNameContract.getData_source_id());
        ElasticsearchDataResult elasticsearchDataResult = elasticsearchDataQuery.query(dataNameContract, dataSourceContract,
                new DateTime(startTime), new DateTime(endTime), esQuery, scrollId, sortOrder, intervalInSeconds);
        return elasticsearchDataResult;
    }
}
