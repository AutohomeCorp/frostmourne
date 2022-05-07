package com.autohome.frostmourne.monitor.service.core.execute;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.model.contract.AlarmContract;
import com.autohome.frostmourne.monitor.model.enums.DataSourceType;
import com.autohome.frostmourne.monitor.service.core.service.IShortLinkService;
import com.google.common.base.Strings;

@Service
public class GenerateShortLinkService implements IGenerateShortLinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateShortLinkService.class);

    @Resource
    private IShortLinkService shortLinkService;

    @Value("${frostmourne.monitor.address}")
    private String frostmourneMonitorAddress;

    @Override
    public String generate(AlarmProcessLogger alarmProcessLogger) {
        AlarmContract alarmContract = alarmProcessLogger.getAlarmContract();
        if (alarmContract.getMetricContract().getDataNameContract() == null) {
            return null;
        }
        String datasourceType = alarmContract.getMetricContract().getDataNameContract().getDatasourceType();
        if (Strings.isNullOrEmpty(datasourceType) || !"elasticsearch".equalsIgnoreCase(datasourceType)) {
            return null;
        }

        Map<String, Object> metricProperties = alarmContract.getMetricContract().getProperties();
        if (metricProperties != null && metricProperties.containsKey("dataLink") && metricProperties.get("dataLink") != null
            && !Strings.isNullOrEmpty(metricProperties.get("dataLink").toString())) {
            return metricProperties.get("dataLink").toString();
        }
        String url = null;
        List<String> queryParameters = new ArrayList<>();
        try {
            if (datasourceType.equalsIgnoreCase(DataSourceType.ELASTICSEARCH)) {
                url = frostmourneMonitorAddress + "/query/elasticsearch.view";
                queryParameters.add("esQuery=" + URLEncoder.encode(alarmContract.getMetricContract().getQueryString(), "utf8"));
                queryParameters.add("startTime=" + URLEncoder.encode(alarmProcessLogger.getContext().get("startTime").toString(), "utf8"));
                queryParameters.add("endTime=" + URLEncoder.encode(alarmProcessLogger.getContext().get("endTime").toString(), "utf8"));
                queryParameters.add("dataName=" + URLEncoder.encode(alarmContract.getMetricContract().getDataNameContract().getDataName(), "utf8"));
            }
            String longUrl = url + "?" + String.join("&", queryParameters);
            return shortLinkService.shorten(longUrl);
        } catch (Exception ex) {
            LOGGER.error("error when generate short link", ex);
            return null;
        }
    }
}
