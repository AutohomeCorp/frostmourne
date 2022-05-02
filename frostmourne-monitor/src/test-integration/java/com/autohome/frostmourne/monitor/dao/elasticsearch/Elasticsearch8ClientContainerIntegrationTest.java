package com.autohome.frostmourne.monitor.dao.elasticsearch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Resource;

import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.google.common.collect.ImmutableMap;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/1 23:57
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"local"})
@EnabledIf(value = "#{'IntegrationTest'.equals(systemProperties['test-profile'])}")
class Elasticsearch8ClientContainerIntegrationTest {

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

    @Test
    void numberTest() {
        Elasticsearch8ClientContainer elasticsearchClient = elasticsearchClient();
        String number = elasticsearchClient.number();
    }

    @Test
    void indexTest() throws IOException {
        Elasticsearch8ClientContainer elasticsearchClient = elasticsearchClient();
        IndexResponse indexResponse = elasticsearchClient.getClient().index(r -> r.document(new HashMap<String, Object>() {
            {
                put("Team", "dealer.arch");
                put("Department", "dealer");
                put("Cost", 20);
                put("LogAt", DateTime.now().toDateTimeISO().toString());
            }
        }).index("applog-index-20220502"));
    }

    @Test
    void es8SearchTest() throws IOException {
        Elasticsearch8ClientContainer elasticsearchClient = elasticsearchClient();
        SearchResponse<Object> searchResponse = elasticsearchClient.getClient().search(r -> r
                .index("applog-index-*")
                .query(q -> q
                        .bool(b -> b
                                .must(c -> c
                                        .queryString(d -> d.query("*")
                                        )
                                )
                        )
                ),
                Object.class);

        long total = searchResponse.hits().total().value();
    }

    Elasticsearch8ClientContainer elasticsearchClient() {
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setServiceAddress("127.0.0.1:9200");
        dataSourceContract.setCreateAt(new Date());
        dataSourceContract.setModifyAt(new Date());
        dataSourceContract.setDatasourceName("es");
        dataSourceContract.setSettings(ImmutableMap.of("version", "8.x"));
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(dataSourceContract);
        AbstractElasticClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
        return (Elasticsearch8ClientContainer) esRestClientContainer;
    }
}