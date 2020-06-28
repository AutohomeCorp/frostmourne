package com.autohome.frostmourne.monitor.service.core.execute;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.contract.AlarmContract;
import com.autohome.frostmourne.monitor.contract.enums.DataSourceType;
import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GenerateShortLinkService implements IGenerateShortLinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateShortLinkService.class);

    @Resource
    private IFrostmourneSpiApi frostmourneSpiApi;

    @Value("${frostmourne.monitor.address}")
    private String frostmourneMonitorAddress;

    public String generate(AlarmProcessLogger alarmProcessLogger) {
        AlarmContract alarmContract = alarmProcessLogger.getAlarmContract();
        String dataName = alarmContract.getMetricContract().getData_name();
        if (dataName.equalsIgnoreCase("http")) {
            return null;
        }
        String datasourceType = alarmContract.getMetricContract().getDataNameContract().getDatasource_type();
        String url = null;
        List<String> queryParameters = new ArrayList<>();
        try {
            if (datasourceType.equalsIgnoreCase(DataSourceType.ELASTICSEARCH)) {
                url = frostmourneMonitorAddress + "/query/elasticsearch.view";
                queryParameters.add("esQuery=" + URLEncoder.encode(alarmContract.getMetricContract().getQuery_string(), "utf8"));
                queryParameters.add("startTime=" + URLEncoder.encode(alarmProcessLogger.getContext().get("startTime").toString(), "utf8"));
                queryParameters.add("endTime=" + URLEncoder.encode(alarmProcessLogger.getContext().get("endTime").toString(), "utf8"));
                queryParameters.add("dataName=" + URLEncoder.encode(alarmContract.getMetricContract().getDataNameContract().getData_name(), "utf8"));
            }
            String longUrl = url + "?" + String.join("&", queryParameters);
            Protocol<String> protocol = frostmourneSpiApi.shortenLink("frostmourne-monitor", URLEncoder.encode(longUrl, "utf8"));
            if (protocol.getReturncode() == 0 && !Strings.isNullOrEmpty(protocol.getResult())) {
                return protocol.getResult();
            }
            LOGGER.warn("error when generate short link. response: " + JacksonUtil.serialize(protocol));
            return longUrl;
        } catch (Exception ex) {
            LOGGER.error("error when generate short link", ex);
            return null;
        }
    }
}
