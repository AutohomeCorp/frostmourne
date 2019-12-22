package com.autohome.frostmourne.monitor.controller;

import java.util.Date;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.service.core.query.IQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/query", "/api/monitor-api/query"})
public class DataQueryController {

    @Resource
    private IQueryService queryService;

    @RequestMapping(value = "/elasticsearchData", method = RequestMethod.GET)
    public Protocol<ElasticsearchDataResult> elasticsearchData(@RequestParam(value = "_appId", required = true) String _appId,
                                                               @RequestParam(value = "dataName", required = true) String dataName,
                                                               @RequestParam(value = "startTime", required = true)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date startTime,
                                                               @RequestParam(value = "endTime", required = true)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date endTime,
                                                               @RequestParam(value = "esQuery", required = true) String esQuery,
                                                               @RequestParam(value = "scrollId", required = false) String scrollId,
                                                               @RequestParam(value = "sortOrder", required = true) String sortOrder,
                                                               @RequestParam(value = "intervalInSeconds", required = false) Integer intervalInSeconds) {
        ElasticsearchDataResult elasticsearchDataResult = queryService.ElasticsearchQuery(dataName, startTime, endTime, esQuery, scrollId, sortOrder, intervalInSeconds);
        return new Protocol<>(elasticsearchDataResult);
    }
}
