package com.autohome.frostmourne.monitor.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVWriter;
import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.service.core.query.IQueryService;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/query", "/api/monitor-api/query"})
public class DataQueryController {

    @Resource
    private IQueryService queryService;

    @Resource
    private IFrostmourneSpiApi frostmourneSpiApi;

    @ResponseBody
    @RequestMapping(value = "/elasticsearchData", method = RequestMethod.GET)
    public Protocol<ElasticsearchDataResult> elasticsearchData(@RequestParam(value = "_appId", required = true) String _appId,
                                                               @RequestParam(value = "dataName", required = true) String dataName,
                                                               @RequestParam(value = "startTime", required = true)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date startTime,
                                                               @RequestParam(value = "endTime", required = true)
                                                               @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date endTime,
                                                               @RequestParam(value = "esQuery", required = false) String esQuery,
                                                               @RequestParam(value = "scrollId", required = false) String scrollId,
                                                               @RequestParam(value = "sortOrder", required = true) String sortOrder,
                                                               @RequestParam(value = "intervalInSeconds", required = false) Integer intervalInSeconds) {
        if(Strings.isNullOrEmpty(esQuery)) {
            esQuery = "*";
        }
        ElasticsearchDataResult elasticsearchDataResult = queryService.elasticsearchQuery(dataName, startTime, endTime, esQuery, scrollId, sortOrder, intervalInSeconds);
        return new Protocol<>(elasticsearchDataResult);
    }

    @ResponseBody
    @RequestMapping(value = "/shortenLink", method = RequestMethod.GET)
    public Protocol<String> shortenLink(@RequestParam(value = "_appId", required = true) String _appId,
                                        @RequestParam(value = "longUrl", required = true) String longUrl) {
        return frostmourneSpiApi.shortenLink("frostmourne-monitor", longUrl);
    }

    @RequestMapping(value = "/downloadData", method = RequestMethod.GET)
    public void downloadData(HttpServletResponse response, @RequestParam(value = "_appId", required = true) String _appId,
                             @RequestParam(value = "dataName", required = true) String dataName,
                             @RequestParam(value = "startTime", required = true)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date startTime,
                             @RequestParam(value = "endTime", required = true)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ") Date endTime,
                             @RequestParam(value = "esQuery", required = true) String esQuery,
                             @RequestParam(value = "scrollId", required = false) String scrollId,
                             @RequestParam(value = "sortOrder", required = true) String sortOrder) throws IOException {

        response.setContentType("application/octet-stream");
        String fileName = dataName + "-" + DateTime.now().toString("yyyyMMddHHmmssSSS") + ".csv";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        response.getOutputStream().write(bom);
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
             CSVWriter csvWriter = new CSVWriter(outputStreamWriter, ',')) {
            ElasticsearchDataResult elasticsearchDataResult = queryService.elasticsearchQuery(dataName, startTime, endTime, esQuery, scrollId, sortOrder, null);
            csvWriter.writeNext(elasticsearchDataResult.getFields().toArray(new String[0]));
            while (true) {
                if (elasticsearchDataResult.getTotal() > 10 * 10000) {
                    throw new RuntimeException("export data length too big, surpass 10W.");
                }
                if (elasticsearchDataResult.getLogs().size() == 0) {
                    break;
                }
                for (Map<String, Object> log : elasticsearchDataResult.getLogs()) {
                    String[] data = log.entrySet().stream()
                            .map(entry -> entry.getValue().toString())
                            .toArray(String[]::new);
                    csvWriter.writeNext(data);
                }
                scrollId = elasticsearchDataResult.getScrollId();
                elasticsearchDataResult = queryService.elasticsearchQuery(dataName, startTime, endTime, esQuery, scrollId, sortOrder, null);
            }
        } finally {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }

    }

}
