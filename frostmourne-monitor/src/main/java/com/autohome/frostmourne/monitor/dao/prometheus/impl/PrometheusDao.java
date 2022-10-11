package com.autohome.frostmourne.monitor.dao.prometheus.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.common.time.TimeParser;
import com.autohome.frostmourne.common.time.TimeParserUtil;
import com.autohome.frostmourne.monitor.dao.prometheus.IPrometheusDao;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValues;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.RangeQuery;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PrometheusDao implements IPrometheusDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrometheusDao.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public PrometheusResponse<MetricValue> query(String user, String password, String addr, String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (!Strings.isNullOrEmpty(user)) {
            headers.add("Authorization", AuthTool.basicAuthValue(user, password));
        }
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> messageResponseEntity =
            restTemplate.exchange(addr + "/api/v1/query?query={query}", HttpMethod.GET, request, String.class, ImmutableMap.of("query", query));
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when query prometheus, addr: {}, query: {}", addr, query);
            throw new RuntimeException("error when query prometheus");
        }
        String responseJson = messageResponseEntity.getBody();
        return JacksonUtil.deSerialize(responseJson, new TypeReference<PrometheusResponse<MetricValue>>() {});
    }

    @Override
    public PrometheusResponse<MetricValues> queryRange(String user, String password, String addr, String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        if (!Strings.isNullOrEmpty(user)) {
            headers.add("Authorization", AuthTool.basicAuthValue(user, password));
        }
        Map<String, String> paramMap = new HashMap<>(4);
        RangeQuery rangeQuery = JacksonUtil.deSerialize(query, RangeQuery.class);
        paramMap.put("query", rangeQuery.getQuery());
        paramMap.put("timeout", "45s");
        if (!Strings.isNullOrEmpty(rangeQuery.getStep())) {
            paramMap.put("step", rangeQuery.getStep());
        }
        long nowInSeconds = TimeParser.getTime();
        if (!Strings.isNullOrEmpty(rangeQuery.getStart())) {
            paramMap.put("start", TimeParserUtil.parse(rangeQuery.getStart(), nowInSeconds) + "");
        }
        if (!Strings.isNullOrEmpty(rangeQuery.getEnd())) {
            paramMap.put("end", TimeParserUtil.parse(rangeQuery.getEnd(), nowInSeconds) + "");
        }
        HttpEntity<Map<String, String>> request = new HttpEntity<>(paramMap, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.exchange(
                addr + "/api/v1/query_range?query={query}&start={start}&end={end}&step={step}&timeout={timeout}",
                HttpMethod.GET, request, String.class, paramMap);
        // ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(addr + "/api/v1/query_range", request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when query prometheus, addr: {}, query: {}", addr, query);
            throw new RuntimeException("error when query prometheus");
        }
        String responseJson = messageResponseEntity.getBody();
        return JacksonUtil.deSerialize(responseJson, new TypeReference<PrometheusResponse<MetricValues>>() {});
    }
}
