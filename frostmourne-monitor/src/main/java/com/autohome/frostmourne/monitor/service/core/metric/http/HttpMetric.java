package com.autohome.frostmourne.monitor.service.core.metric.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractBaseMetric;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class HttpMetric extends AbstractBaseMetric {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Resource(name = "okHttp3Client")
    private OkHttpClient okHttp3Client;

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> settings) {
        Map<String, Object> result = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Long start = System.currentTimeMillis();
        try {
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
            try (Response response = okHttp3Client.newCall(request).execute(); ResponseBody responseBody = response.body()) {
                Long end = System.currentTimeMillis();
                result.put("HTTP_COST", end - start);
                result.put("HTTP_STATUS", response.code());
                // TODO 判空
                String responseBodyString = responseBody.string();
                String format = request.url().queryParameter("format");
                if (isJson(responseBody) || (!Strings.isNullOrEmpty(format) && "json".equalsIgnoreCase(format))) {
                    if (responseBodyString.startsWith("[")) {
                        List<Object> list = mapper.readValue(responseBodyString, new TypeReference<List<Object>>() {});
                        result.put("ResponseBody", list);
                    } else {
                        Map<String, Object> map = mapper.readValue(responseBodyString, new TypeReference<Map<String, Object>>() {});
                        result.putAll(map);
                    }
                } else {
                    result.put("ResponseBody", responseBodyString);
                }
            }
        } catch (Exception ex) {
            Long end = System.currentTimeMillis();
            result.put("HTTP_STATUS", -1);
            result.put("HTTP_COST", end - start);
            result.put("ERROR", ex.getMessage());
            result.put("EXCEPTION_TYPE", ex.getClass().getTypeName());
        }

        return result;
    }

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.object;
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.http.name());
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
