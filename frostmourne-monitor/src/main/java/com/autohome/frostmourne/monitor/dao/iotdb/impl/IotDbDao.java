package com.autohome.frostmourne.monitor.dao.iotdb.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.iotdb.IIotDbDao;
import com.autohome.frostmourne.monitor.dao.iotdb.domain.IotDbRestResponse;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class IotDbDao implements IIotDbDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(IotDbDao.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public IotDbRestResponse query(String user, String password, String iotDbAddr, String sql) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (!Strings.isNullOrEmpty(user)) {
            headers.add("Authorization", AuthTool.basicAuthValue(user, password));
        }
        Map<String, Object> body = new HashMap<>();
        body.put("sql", sql);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(iotDbAddr + "/rest/v1/query", request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when query iotdb, addr: {}, sql: {}", iotDbAddr, sql);
            throw new RuntimeException("error when query iotdb");
        }
        String responseJson = messageResponseEntity.getBody();
        IotDbRestResponse iotDbRestResponse = JacksonUtil.deSerialize(responseJson, IotDbRestResponse.class);
        if (iotDbRestResponse.getTimestamps() != null) {
            List<String> timestampsIos8601 = new ArrayList<>(iotDbRestResponse.getTimestamps().size());
            for (Long timestamp : iotDbRestResponse.getTimestamps()) {
                DateTime dateTime = new DateTime(timestamp);
                timestampsIos8601.add(dateTime.toDateTimeISO().toString());
            }
            iotDbRestResponse.setTimestampsIso8601(timestampsIos8601);
        }

        return iotDbRestResponse;
    }
}
