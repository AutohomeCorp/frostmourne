package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.*;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Splitter;

public class EsRestClientContainer {

    public static final IndicesOptions DEFAULT_INDICE_OPTIONS = IndicesOptions.fromOptions(true, false, true, false);

    private static final Logger LOGGER = LoggerFactory.getLogger(EsRestClientContainer.class);

    private RestHighLevelClient restHighLevelClient;

    private RestClient restLowLevelClient;

    private List<String> esHosts;

    private boolean sniff;

    private Sniffer sniffer;

    private String name;

    private Map<String, String> settings;

    private Long initTimestamp;

    public EsRestClientContainer(String esHostList, boolean sniff, Map<String, String> settings) {
        esHosts = Splitter.on(",").splitToList(esHostList);
        this.sniff = sniff;
        this.settings = settings;
    }

    public void init() {
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
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        this.restLowLevelClient = restHighLevelClient.getLowLevelClient();
        if (sniff) {
            sniffer = Sniffer.builder(restLowLevelClient).setSniffIntervalMillis(5 * 60 * 1000).build();
        }

        this.initTimestamp = System.currentTimeMillis();
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
                truststore.load(is, Optional.ofNullable(settings.get("sslCertPassword")).map(String::toCharArray).orElse(null));
            }
            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
            return sslBuilder.build();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean health() {
        ClusterHealthRequest request = new ClusterHealthRequest();
        request.timeout(TimeValue.timeValueSeconds(5));
        ClusterHealthResponse response;
        try {
            response = restHighLevelClient.cluster().health(request, RequestOptions.DEFAULT);
        } catch (IOException ex) {
            LOGGER.error("error when check cluster health", ex);
            return false;
        }
        return response.getStatus() == ClusterHealthStatus.GREEN;
    }

    public List<String> fetchAllMappingFields(String index) throws IOException {
        Map<String, Map<String, Map<String, Map<String, Object>>>> mappings = this.fetchAllMappings(index);
        if (mappings == null || mappings.isEmpty()) {
            return Collections.emptyList();
        }
        return mappings.entrySet().stream().flatMap(e -> e.getValue().values().stream()).flatMap(e -> e.get("properties").keySet().stream()).distinct().sorted()
            .collect(Collectors.toList());
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

    public MappingMetaData fetchMapping(String index) throws IOException {
        GetMappingsRequest mappingsRequest = new GetMappingsRequest();
        mappingsRequest.indices(index);
        // 低版本es服务端可能不支持
        GetMappingsResponse response = restHighLevelClient.indices().getMapping(mappingsRequest, RequestOptions.DEFAULT);
        return response.mappings().values().iterator().next().value.values().iterator().next().value;
    }

    public void close() {
        if (this.sniffer != null) {
            try {
                this.sniffer.close();
            } catch (Exception ex) {
                LOGGER.error("error when close elasticsearch sniffer", ex);
            }
        }

        if (this.restHighLevelClient != null) {
            try {
                this.restHighLevelClient.close();
            } catch (IOException ex) {
                LOGGER.error("error when close elasticsearch rest client", ex);
            }
        }
    }

    public RestHighLevelClient fetchHighLevelClient() {
        return this.restHighLevelClient;
    }

    public boolean checkIndexOpenExists(String index) {
        return checkIndexExists(index) && checkIndexOpen(index);
    }

    public boolean checkIndexExists(String index) {
        try {
            GetIndexRequest request = new GetIndexRequest();
            request.local(false);
            request.indices(index);
            request.humanReadable(true);
            request.includeDefaults(false);
            return this.restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean checkIndexOpen(String index) {
        try {
            Response response = this.restLowLevelClient.performRequest("GET", String.format("/_cat/indices/%s?v", index));
            response.getEntity().getContent();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {
                List<String> lines = bufferedReader.lines().collect(Collectors.toList());
                if (lines.size() <= 1) {
                    return false;
                }
                List<String> list = Splitter.on(" ").splitToList(lines.get(1));
                return list.contains("open");
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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

    public long totalCount(BoolQueryBuilder boolQueryBuilder, String[] indices) throws IOException {
        CountRequest countRequest = new CountRequest(indices);
        countRequest.indicesOptions(EsRestClientContainer.DEFAULT_INDICE_OPTIONS);
        SearchSourceBuilder countSourceBuilder = new SearchSourceBuilder();
        countSourceBuilder.query(boolQueryBuilder);
        countRequest.source(countSourceBuilder);

        CountResponse countResponse = this.fetchHighLevelClient().count(countRequest, RequestOptions.DEFAULT);
        return countResponse.getCount();
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

    public Long getInitTimestamp() {
        return initTimestamp;
    }
}
