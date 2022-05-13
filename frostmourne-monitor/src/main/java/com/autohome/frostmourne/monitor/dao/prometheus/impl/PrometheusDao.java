package com.autohome.frostmourne.monitor.dao.prometheus.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.prometheus.IPrometheusDao;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.MetricValue;
import com.autohome.frostmourne.monitor.dao.prometheus.domain.PrometheusResponse;
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
        PrometheusResponse<MetricValue> prometheusResponse = JacksonUtil.deSerialize(responseJson, new TypeReference<PrometheusResponse<MetricValue>>() {});
        return prometheusResponse;
    }
}
