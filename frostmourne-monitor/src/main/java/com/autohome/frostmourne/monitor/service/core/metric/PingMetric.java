package com.autohome.frostmourne.monitor.service.core.metric;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.google.common.base.Splitter;

public class PingMetric implements IMetric {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingMetric.class);

    @Override
    public Map<String, Object> pullMetric(MetricContract metricContract, Map<String, String> ruleSettings) {
        Map<String, Object> result = new HashMap<>();
        result.put("SERVERS", metricContract.getQueryString());
        List<String> serverList = Splitter.on(',').splitToList(metricContract.getQueryString()).stream().map(String::trim).collect(Collectors.toList());
        result.put("TOTAL", serverList.size());
        result.put("SERVER_LIST", serverList);
        List<String> unReachableServers = new ArrayList<>();
        for (String server : serverList) {
            boolean reachable = ping(server);
            if (!reachable) {
                unReachableServers.add(server);
            }
        }
        result.put("FAIL_SERVER_LIST", unReachableServers);
        result.put("FAIL_SERVERS", String.join(",", unReachableServers));
        result.put("FAIL_COUNT", unReachableServers.size());
        return result;
    }

    private boolean ping(String server) {
        try {
            InetAddress geek = InetAddress.getByName(server);
            return geek.isReachable(1000);
        } catch (IOException ex) {
            LOGGER.error("error when ping server: " + server, ex);
            return false;
        }
    }
}
