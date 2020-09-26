package com.autohome.frostmourne.monitor.dao.influxdb.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.influxdb.IInfluxdbDao;
import com.autohome.frostmourne.monitor.dao.influxdb.InfluxdbResponse;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class InfluxdbDao implements IInfluxdbDao {

    @Resource
    private RestTemplate restTemplate;

    public InfluxdbResponse query(String influxdbAddress, String db, String query) {
        ResponseEntity<InfluxdbResponse> responseEntity = restTemplate.getForEntity(influxdbAddress + "/query?db={db}&q={q}", InfluxdbResponse.class,
                ImmutableMap.of("db", db, "q", query));
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException(String.format("error when request influxdb. db: %s, query: %s, response code: %s", db, query, responseEntity.getStatusCode().value()));
        }
        return responseEntity.getBody();
    }
}
