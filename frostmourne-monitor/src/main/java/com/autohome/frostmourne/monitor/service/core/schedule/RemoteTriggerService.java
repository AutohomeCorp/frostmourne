package com.autohome.frostmourne.monitor.service.core.schedule;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteTriggerService implements IRemoteTriggerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteTriggerService.class);

    @Resource
    private RestTemplate restTemplate;

    @Value("${frostmourne.monitor.address}")
    private String frostmourneMonitorAddress;

    @Override
    public void trigger(Long alarmId) {
        try {
            Protocol protocol = restTemplate.getForObject(String.format("%s/alarm/run?_appId=frostmourne-monitor&alarmId={alarmId}", frostmourneMonitorAddress),
                    Protocol.class, ImmutableMap.of("alarmId", alarmId));
            if(protocol.getReturncode() != 0) {
                LOGGER.error("error when trigger remote, id: {}, response: {}", alarmId, JacksonUtil.serialize(protocol));
            }
        } catch (Exception ex) {
            LOGGER.error("error when trigger remote, id: {}", alarmId, ex);
        }

    }
}
