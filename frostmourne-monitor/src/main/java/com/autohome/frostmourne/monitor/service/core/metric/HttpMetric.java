package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpMetric implements IMetric {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpMetric.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> responseEntity;
        try {
            Long start = System.currentTimeMillis();
            HttpHeaders headers = new HttpHeaders();
            if (metricContract.getProperties() != null && metricContract.getProperties().size() > 0) {
                for (Map.Entry<String, Object> entry : metricContract.getProperties().entrySet()) {
                    if (!Strings.isNullOrEmpty(entry.getKey()) && entry.getValue() != null) {
                        headers.set(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
            if (Strings.isNullOrEmpty(metricContract.getPost_data())) {
                HttpEntity requestGet = new HttpEntity(headers);
                responseEntity = restTemplate.exchange(metricContract.getQuery_string(), HttpMethod.GET, requestGet, String.class);
            } else {
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                HttpEntity<String> request = new HttpEntity<>(metricContract.getPost_data(), headers);
                responseEntity = restTemplate.postForEntity(metricContract.getQuery_string(), request, String.class);
            }
            Long end = System.currentTimeMillis();
            result.put("HTTP_STATUS", responseEntity.getStatusCodeValue());
            result.put("HTTP_COST", end - start);
            String json = responseEntity.getBody();
            if (!Strings.isNullOrEmpty(json)) {
                try {
                    Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
                    });
                    result.putAll(map);
                } catch (Exception ex) {
                    result.put("ResponseBody", json);
                }
            }

            return result;
        } catch (Exception ex) {
            throw new RuntimeException("error when pull http metric", ex);
        }
    }
}
