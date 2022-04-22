package com.autohome.frostmourne.monitor.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.common.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.monitor.model.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.service.core.query.IQueryService;
import com.autohome.frostmourne.monitor.service.core.service.IShortLinkService;

import au.com.bytecode.opencsv.CSVWriter;

@Controller
@RequestMapping(value = {"/query", "/api/monitor-api/query"})
public class DataQueryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataQueryController.class);

    @Resource
    private IQueryService queryService;

    @Resource
    private IShortLinkService shortLinkService;

    @ResponseBody
    @RequestMapping(value = "/elasticsearchData")
    public Protocol<ElasticsearchDataResult> elasticsearchData(@RequestParam(value = "dataName") String dataName,
        @RequestParam(value = "startTime") Date startTime, @RequestParam(value = "endTime") Date endTime,
        @RequestParam(value = "esQuery", required = false) String esQuery,
        @RequestParam(value = "scrollId", required = false) String scrollId,
        @RequestParam(value = "sortOrder") String sortOrder,
        @RequestParam(value = "intervalInSeconds", required = false) Integer intervalInSeconds) {
        if (Strings.isNullOrEmpty(esQuery)) {
            esQuery = "*";
        }
        ElasticsearchDataResult elasticsearchDataResult = queryService.elasticsearchQuery(dataName, startTime, endTime,
            esQuery, scrollId, sortOrder, intervalInSeconds);
        return new Protocol<>(elasticsearchDataResult);
    }

    @ResponseBody
    @RequestMapping(value = "/elasticsearchFields")
    public Protocol<List<String>> elasticsearchFields(@RequestParam(value = "dataName") String dataName) {
        try {
            List<String> fields = queryService.elasticsearchFields(dataName);
            return new Protocol<>(fields);
        } catch (Exception e) {
            LOGGER.error("获取es索引字段列表失败: dataName={}, error={}", dataName, e.getMessage(), e);
            return new Protocol<>(Collections.emptyList(), 0, "数据读取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/shortenLink", method = RequestMethod.GET)
    public Protocol<String> shortenLink(@RequestParam(value = "longUrl") String longUrl) {
        String shortLink = shortLinkService.shorten(longUrl);
        return new Protocol<>(shortLink);
    }

    @RequestMapping(value = "/downloadData")
    public void downloadData(HttpServletResponse response, @RequestParam(value = "dataName") String dataName,
        @RequestParam(value = "startTime") Date startTime, @RequestParam(value = "endTime") Date endTime,
        @RequestParam(value = "esQuery") String esQuery,
        @RequestParam(value = "scrollId", required = false) String scrollId,
        @RequestParam(value = "sortOrder") String sortOrder) throws IOException {
        response.setContentType("application/octet-stream;charset=utf-8");
        String fileName = dataName + "-" + DateTime.now().toString("yyyyMMddHHmmssSSS") + ".csv";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("attachment-filename", fileName);
        byte[] bom = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
        response.getOutputStream().write(bom);
        try (
            OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
            CSVWriter csvWriter = new CSVWriter(outputStreamWriter, ',')) {
            queryService.exportToCsv(csvWriter, dataName, new DateTime(startTime), new DateTime(endTime), esQuery, null,
                sortOrder);
        } finally {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

}
