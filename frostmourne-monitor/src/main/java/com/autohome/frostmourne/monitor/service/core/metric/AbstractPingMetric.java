package com.autohome.frostmourne.monitor.service.core.metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.google.common.base.Splitter;

public abstract class AbstractPingMetric extends AbstractBaseMetric {

    public abstract boolean ping(String server);

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

}
