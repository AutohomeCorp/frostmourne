package com.autohome.frostmourne.monitor.dao.skywalking.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.skywalking.daomain.GetAlarmsResponse;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.QueryLogsResponse;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingAlarmQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingAlarms;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingQuery;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.dao.skywalking.ISkywalkingDao;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogQueryCondition;
import com.autohome.frostmourne.monitor.dao.skywalking.daomain.SkywalkingLogs;
import com.google.common.base.Strings;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 0:24
 */

/**
 * https://github.com/apache/skywalking-query-protocol
 */
@Repository
public class SkywalkingDao implements ISkywalkingDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkywalkingDao.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public SkywalkingLogs queryLogs(String user, String password, String skywalkingAddr, SkywalkingLogQueryCondition condition) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (!Strings.isNullOrEmpty(user)) {
            headers.add("Authorization", AuthTool.basicAuthValue(user, password));
        }
        String json = SkywalkingQuery.toLogsQuery(condition);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(skywalkingAddr + "/graphql", request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when query skywalking logs, addr: {}, condition: {}", skywalkingAddr, JacksonUtil.serialize(condition));
            throw new RuntimeException("error when query skywalking logs");
        }
        String responseJson = messageResponseEntity.getBody();
        QueryLogsResponse queryLogsResponse = JacksonUtil.deSerialize(responseJson, QueryLogsResponse.class);
        return queryLogsResponse.getData().getQueryLogs();
    }

    @Override
    public SkywalkingAlarms queryAlarms(String user, String password, String skywalkingAddr, SkywalkingAlarmQueryCondition condition) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        if (!Strings.isNullOrEmpty(user)) {
            headers.add("Authorization", AuthTool.basicAuthValue(user, password));
        }
        String json = SkywalkingQuery.toAlarmsQuery(condition);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> messageResponseEntity = restTemplate.postForEntity(skywalkingAddr + "/graphql", request, String.class);
        if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("error when query skywalking logs, addr: {}, condition: {}", skywalkingAddr, JacksonUtil.serialize(condition));
            throw new RuntimeException("error when query skywalking logs");
        }
        String responseJson = messageResponseEntity.getBody();
        GetAlarmsResponse getAlarmsResponse = JacksonUtil.deSerialize(responseJson, GetAlarmsResponse.class);
        return getAlarmsResponse.getData().getGetAlarm();
    }
}
