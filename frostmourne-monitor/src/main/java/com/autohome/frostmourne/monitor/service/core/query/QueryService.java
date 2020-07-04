package com.autohome.frostmourne.monitor.service.core.query;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;

import au.com.bytecode.opencsv.CSVWriter;
import com.autohome.frostmourne.core.contract.ProtocolException;
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

    public ElasticsearchDataResult elasticsearchQuery(String dataName, Date startTime, Date endTime, String esQuery,
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

    @Override
    public void exportToCsv(CSVWriter csvWriter, String dataName, DateTime startTime, DateTime endTime, String esQuery,
                            String scrollId, String sortOrder) {
        DataNameContract dataNameContract = dataAdminService.findDataNameByName(dataName);
        DataSourceContract dataSourceContract = dataAdminService.findDatasourceById(dataNameContract.getData_source_id());
        ElasticsearchDataResult elasticsearchDataResult = elasticsearchDataQuery.query(dataNameContract, dataSourceContract,
                startTime, endTime, esQuery, scrollId, sortOrder, null);
        String[] heads = elasticsearchDataResult.getFields().toArray(new String[0]);
        csvWriter.writeNext(heads);
        while (true) {
            if (elasticsearchDataResult.getTotal() > 10 * 10000) {
                throw new ProtocolException(500, "数量总数超过10万,无法下载");
            }
            if (elasticsearchDataResult.getLogs().size() == 0) {
                break;
            }
            for (Map<String, Object> log : elasticsearchDataResult.getLogs()) {
                String[] data = Arrays.stream(heads).map(h -> log.get(h) == null ? null : log.get(h).toString()).toArray(String[]::new);
                csvWriter.writeNext(data);
            }
            scrollId = elasticsearchDataResult.getScrollId();
            elasticsearchDataResult = elasticsearchDataQuery.query(dataNameContract, dataSourceContract,
                    startTime, endTime, esQuery, scrollId, sortOrder, null);
        }
    }
}
