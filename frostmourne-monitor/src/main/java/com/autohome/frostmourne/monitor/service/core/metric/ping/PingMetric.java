package com.autohome.frostmourne.monitor.service.core.metric.ping;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractBaseMetric;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractPingMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

@Service
public class PingMetric extends AbstractPingMetric {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingMetric.class);

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.ping;
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.ping.name());
    }

    @Override
    public boolean ping(String server) {
        try {
            InetAddress geek = InetAddress.getByName(server);
            return geek.isReachable(1000);
        } catch (IOException ex) {
            LOGGER.error("error when ping server: " + server, ex);
            return false;
        }
    }
}
