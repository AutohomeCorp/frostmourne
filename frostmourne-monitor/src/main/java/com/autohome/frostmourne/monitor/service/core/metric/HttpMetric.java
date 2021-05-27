package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpMetric implements IMetric {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpMetric.class);

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    /*@Resource
    private RestTemplate restTemplate;*/

    @Resource(name = "okHttp3Client")
    private OkHttpClient okHttp3Client;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Long start = System.currentTimeMillis();
            Request.Builder requestBuilder = new Request.Builder().url(metricContract.getQueryString());
            if (!Strings.isNullOrEmpty(metricContract.getPostData())) {
                requestBuilder.method("POST", RequestBody.create(JSON, metricContract.getPostData()));
            }
            if (metricContract.getProperties() != null && metricContract.getProperties().size() > 0) {
                for (Map.Entry<String, Object> entry : metricContract.getProperties().entrySet()) {
                    if (!Strings.isNullOrEmpty(entry.getKey()) && entry.getValue() != null) {
                        requestBuilder.addHeader(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
            Request request = requestBuilder.build();
            try (Response response = okHttp3Client.newCall(request).execute();
                 ResponseBody responseBody = response.body()) {
                Long end = System.currentTimeMillis();
                result.put("HTTP_COST", end - start);
                result.put("HTTP_STATUS", response.code());
                if (response.isSuccessful()) {
                    String responseBodyString = responseBody.string();
                    String format = request.url().queryParameter("format");
                    if (isJson(responseBody) || (!Strings.isNullOrEmpty(format) && format.equalsIgnoreCase("json"))) {
                        if (responseBodyString.startsWith("[")) {
                            List<Object> list = mapper.readValue(responseBodyString, new TypeReference<List<Object>>() {
                            });
                            result.put("ResponseBody", list);
                        } else {
                            Map<String, Object> map = mapper.readValue(responseBodyString, new TypeReference<Map<String, Object>>() {
                            });
                            result.putAll(map);
                        }
                    } else {
                        result.put("ResponseBody", responseBodyString);
                    }
                }
            }
            /*HttpHeaders headers = new HttpHeaders();
            if (metricContract.getProperties() != null && metricContract.getProperties().size() > 0) {
                for (Map.Entry<String, Object> entry : metricContract.getProperties().entrySet()) {
                    if (!Strings.isNullOrEmpty(entry.getKey()) && entry.getValue() != null) {
                        headers.set(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
            if (Strings.isNullOrEmpty(metricContract.getPostData())) {
                HttpEntity requestGet = new HttpEntity(headers);
                responseEntity = restTemplate.exchange(metricContract.getQueryString(), HttpMethod.GET, requestGet, String.class);
            } else {
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                HttpEntity<String> request = new HttpEntity<>(metricContract.getPostData(), headers);
                responseEntity = restTemplate.postForEntity(metricContract.getQueryString(), request, String.class);
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
            }*/
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("error when pull http metric", ex);
        }
    }

    private boolean isJson(ResponseBody responseBody) {
        if (responseBody == null) {
            return false;
        }

        MediaType contentType = responseBody.contentType();
        if (contentType == null) {
            return false;
        }

        String subType = contentType.subtype();
        if (Strings.isNullOrEmpty(subType)) {
            return false;
        }
        return subType.contains("json");
    }
}
