package com.autohome.frostmourne.monitor.dao.elasticsearch;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/4/30 15:13
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Splitter;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestExtendConverters;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * elasticsearch 8+ client
 */
public abstract class AbstractElasticClientContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractElasticClientContainer.class);

    public static final TimeValue DEFAULT_TIME_VALUE = new TimeValue(10, TimeUnit.MINUTES);

    private List<String> esHosts;

    private Map<String, String> settings;

    protected Long initTimestamp;

    protected RestClient restLowLevelClient;

    protected  AbstractElasticClientContainer(String esHostList, Map<String, String> settings) {
        esHosts = Splitter.on(",").splitToList(esHostList);
        this.settings = settings;
    }

    public abstract void init();

    public abstract boolean health();

    public abstract String number();

    public abstract Long getInitTimestamp();

    public abstract void close();

    public abstract ElasticsearchDataResult query(DataNameContract dataNameContract, DateTime start, DateTime end, String esQuery,
                                         String scrollId, String sortOrder, Integer intervalInSeconds);

    public abstract MetricData queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException;

    public RestClientBuilder initRestClientBuilder() {
        RestClientBuilder restClientBuilder = RestClient.builder(parseHttpHost(esHosts).toArray(new HttpHost[0]));

        if (this.settings != null && !this.settings.isEmpty()) {
            final CredentialsProvider credentialsProvider = this.createCredentialsProvider(restClientBuilder, this.settings);
            final SSLContext sslContext = this.createSslContext(restClientBuilder, this.settings);
            restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                if (credentialsProvider != null) {
                    httpAsyncClientBuilder.disableAuthCaching().setDefaultCredentialsProvider(credentialsProvider);
                }
                if (sslContext != null) {
                    httpAsyncClientBuilder.setSSLContext(sslContext);
                }
                return httpAsyncClientBuilder;
            });
        }

        return restClientBuilder;
    }

    public String[] buildIndices(DateTime from, DateTime to, String prefix, String datePattern) {
        DateTime now = DateTime.now();
        if (now.getMillis() < to.getMillis()) {
            to = now;
        }
        List<String> indiceList = new ArrayList<>();
        if (Strings.isNullOrEmpty(datePattern)) {
            indiceList.add(prefix);
            return indiceList.toArray(new String[0]);
        }

        if ("*".equals(datePattern)) {
            indiceList.add(prefix + "*");
            return indiceList.toArray(new String[0]);
        }

        DateTime cursor = DateTime.parse(from.minusDays(1).toString("yyyy-MM-dd"));

        while (cursor.getMillis() < to.getMillis()) {
            String index = prefix + cursor.toString(datePattern);
            if (!indiceList.contains(index)) {
                indiceList.add(index);
            }
            cursor = cursor.minusDays(-1);
        }
        return indiceList.toArray(new String[0]);
    }

    public List<String> fetchAllKeywordFields(String index) throws IOException {
        Map<String, Map<String, Object>> propertiesMap = findMappings(index);
        return propertiesMap.entrySet().stream().filter(x -> x.getValue().get("type").equals("keyword")).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public List<String> fetchAllNumberFields(String index) throws IOException {
        Map<String, Map<String, Object>> propertiesMap = findMappings(index);
        return propertiesMap.entrySet().stream().filter(x -> x.getValue().get("type").equals("integer") || x.getValue().get("type").equals("long"))
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public List<String> fetchAllMappingFields(String index) throws IOException {
        Map<String, Map<String, Object>> lastIndexMap = findMappings(index);
        return new ArrayList<>(lastIndexMap.keySet());
    }

    public Map<String, Map<String, Object>> findMappings(String index) throws IOException {
        Map<String, Map<String, Map<String, Map<String, Object>>>> mappings = this.fetchAllMappings(index);
        if (mappings == null || mappings.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Map<String, Object>> propertiesMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Map<String, Map<String, Object>>>> entry : mappings.entrySet()) {
            propertiesMap.putAll(entry.getValue().get("properties"));
        }
        return propertiesMap;
    }

    public Map<String, Map<String, Map<String, Map<String, Object>>>> fetchAllMappings(String index) throws IOException {
        GetMappingsRequest mappingsRequest = new GetMappingsRequest();
        mappingsRequest.indices(index);

        Request req = RequestExtendConverters.getMappings(mappingsRequest);
        req.setOptions(RequestOptions.DEFAULT);
        // 兼容低版本es服务端
        Response resp = restLowLevelClient.performRequest(req);
        return this.parseMappingsFromResponse(resp);
    }

    Map<String, Map<String, Map<String, Map<String, Object>>>> parseMappingsFromResponse(Response response) throws IOException {
        String value = EntityUtils.toString(response.getEntity());
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> result =
                JacksonUtil.deSerialize(value, new TypeReference<Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>>>() {});
        // 取"mappings"节点组合
        return result.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().get("mappings"), (v1, v2) -> v1));
    }

    public String[] findIndices(DataNameContract dataNameContract, DateTime start, DateTime end) {
        Map<String, String> dataNameProperties = dataNameContract.getSettings();
        String indexPrefix = dataNameProperties.get("indexPrefix");
        String datePattern = dataNameProperties.get("timePattern");
        String[] indices =buildIndices(start, end, indexPrefix, datePattern);
        return indices;
    }

    private CredentialsProvider createCredentialsProvider(RestClientBuilder restClientBuilder, Map<String, String> settings) {
        if (Strings.isNullOrEmpty(this.settings.get("username")) || Strings.isNullOrEmpty(this.settings.get("password"))) {
            return null;
        }
        String userName = settings.get("username");
        String password = settings.get("password");
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        return credentialsProvider;
    }

    private SSLContext createSslContext(RestClientBuilder restClientBuilder, Map<String, String> settings) {
        if (!isHttpsHost(settings)) {
            return null;
        }
        String certBase64 = settings.get("sslCert");
        if (Strings.isNullOrEmpty(certBase64)) {
            LOGGER.error("sslCert could not be null when use https");
            return null;
        }
        String sslFormat = settings.get("sslFormat");
        try {
            if (Strings.isNullOrEmpty(sslFormat)) {
                sslFormat = "jks";
            }
            KeyStore truststore = KeyStore.getInstance(sslFormat);
            try (InputStream is = new ByteArrayInputStream(Base64.decodeBase64(certBase64))) {
                truststore.load(is, Optional.ofNullable(settings.get("sslCertPassword")).map(String::toCharArray).orElse("".toCharArray()));
            }
            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
            return sslBuilder.build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private List<HttpHost> parseHttpHost(List<String> esHosts) {
        List<HttpHost> hostList = new ArrayList<>();
        String scheme = this.isHttpsHost(this.settings) ? "https" : "http";
        for (String hostString : esHosts) {
            List<String> hostAndPort = Splitter.on(":").trimResults().splitToList(hostString);
            hostList.add(new HttpHost(hostAndPort.get(0), Integer.parseInt(hostAndPort.get(1)), scheme));
        }
        return hostList;
    }

    private boolean isHttpsHost(Map<String, String> settings) {
        String httpsOption = Optional.ofNullable(settings).map(v -> v.get("https")).orElse(null);
        return "YES".equalsIgnoreCase(httpsOption);
    }

    protected List<String> findFields(Map<String, Object> doc, String parentField) {
        List<String> fields = new ArrayList<>();
        for (Map.Entry<String, Object> entry : doc.entrySet()) {
            String field = null;
            if (Strings.isNullOrEmpty(parentField)) {
                field = entry.getKey();
            } else {
                field = parentField + "." + entry.getKey();
            }
            if (entry.getValue() instanceof Map) {
                fields.addAll(findFields((Map<String, Object>)entry.getValue(), field));
            } else {
                fields.add(field);
            }
        }
        return fields;
    }

    static void fillFields(List<String> headFieldList, ElasticsearchDataResult dataResult, List<String> flatFields) {
        dataResult.setFlatFields(flatFields);
        if (headFieldList == null || headFieldList.size() == 0) {
            if (flatFields.size() > 7) {
                dataResult.setHeadFields(flatFields.subList(0, 6));
            } else {
                dataResult.setHeadFields(flatFields);
            }
        } else {
            dataResult.setHeadFields(headFieldList);
        }
    }

}
