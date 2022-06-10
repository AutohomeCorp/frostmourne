package com.autohome.frostmourne.monitor.service.core.metric.telnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.model.enums.MetricEnumType;
import com.autohome.frostmourne.monitor.service.core.metric.AbstractPingMetric;
import com.google.common.base.Splitter;
import org.springframework.stereotype.Service;

@Service
public class TelnetMetric extends AbstractPingMetric {

    @Override
    public boolean ping(String server) {
        List<String> ipAndPort = Splitter.on(':').splitToList(server);
        String ip = ipAndPort.get(0);
        Integer port = Integer.parseInt(ipAndPort.get(1));
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port));
            return socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MetricEnumType metricType() {
        return MetricEnumType.telnet;
    }

    @Override
    public boolean matchDataSourceType(String dataSourceType) {
        return dataSourceType.equalsIgnoreCase(DataSourceType.telnet.name());
    }
}
