package com.autohome.frostmourne.monitor.service.core.query;

import java.util.Date;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import org.joda.time.DateTime;

public interface IQueryService {

    ElasticsearchDataResult elasticsearchQuery(String dataName, Date startTime, Date endTime, String esQuery,
                                               String scrollId, String sortOrder, Integer intervalInSeconds);

    List<String> elasticsearchFields(String dataName);

    void exportToCsv(CSVWriter csvWriter, String dataName, DateTime startTime, DateTime endTime, String esQuery,
                     String scrollId, String sortOrder);
}
