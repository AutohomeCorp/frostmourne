package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EsRestClientContainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsRestClientContainer.class);

    private RestHighLevelClient restHighLevelClient;

    private RestClient restLowLevelClient;

    private List<String> esHosts;

    private boolean sniff;

    private Sniffer sniffer;

    private String name;

    private Map<String, String> settings;

    public EsRestClientContainer(String esHostList, boolean sniff, Map<String, String> settings) {
        esHosts = Splitter.on(",").splitToList(esHostList);
        this.sniff = sniff;
        this.settings = settings;
    }

    public void init() {
        RestClientBuilder restClientBuilder = RestClient.builder(parseHttpHost(esHosts)
                .toArray(new HttpHost[0]));

        if (this.settings != null && this.settings.size() > 0 &&
                !Strings.isNullOrEmpty(this.settings.get("username"))
                && !Strings.isNullOrEmpty(this.settings.get("password"))) {
            String userName = this.settings.get("username");
            String password = this.settings.get("password");
            final CredentialsProvider credentialsProvider =
                    new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(userName, password));
            restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                httpAsyncClientBuilder.disableAuthCaching();
                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            });
        }
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        this.restLowLevelClient = restHighLevelClient.getLowLevelClient();
        if (sniff) {
            sniffer = Sniffer.builder(restLowLevelClient).setSniffIntervalMillis(5 * 60 * 1000).build();
        }
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
        return checkIndexExists(index)
                && checkIndexOpen(index);
    }

    public boolean checkIndexExists(String index) {
        try {
            /*GetIndexRequest request = new GetIndexRequest(index);
            request.local(false);
            request.humanReadable(true);
            request.includeDefaults(false);
            return this.restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);*/
            return true;
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

        if (datePattern.equals("*")) {
            indiceList.add(prefix + "*");
            return indiceList.toArray(new String[0]);
        }

        DateTime cursor = DateTime.parse(from.minusDays(1).toString("yyyy-MM-dd"));

        while (cursor.getMillis() < to.getMillis()) {
            String index = prefix + cursor.toString(datePattern);
            if (prefix.contains("*")) {
                if (!indiceList.contains(index)) {
                    indiceList.add(index);
                }
            } else if (checkIndexExists(index)) {
                if (!indiceList.contains(index)) {
                    indiceList.add(index);
                }
            }
            cursor = cursor.minusDays(-1);
        }
        return indiceList.toArray(new String[0]);
    }

    public long totalCount(BoolQueryBuilder boolQueryBuilder, String[] indices) throws IOException {
        CountRequest countRequest = new CountRequest(indices);
        SearchSourceBuilder countSourceBuilder = new SearchSourceBuilder();
        countSourceBuilder.query(boolQueryBuilder);
        countRequest.source(countSourceBuilder);

        CountResponse countResponse = this.fetchHighLevelClient().count(countRequest, RequestOptions.DEFAULT);
        return countResponse.getCount();
    }

    private List<HttpHost> parseHttpHost(List<String> esHosts) {
        List<HttpHost> hostList = new ArrayList<>();
        for (String hostString : esHosts) {
            List<String> hostAndPort = Splitter.on(":").trimResults().splitToList(hostString);
            hostList.add(new HttpHost(hostAndPort.get(0), Integer.parseInt(hostAndPort.get(1)), "http"));
        }
        return hostList;
    }
}
