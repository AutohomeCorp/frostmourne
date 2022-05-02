package com.autohome.frostmourne.monitor.dao.elasticsearch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"local"})
@EnabledIf(value = "#{'IntegrationTest'.equals(systemProperties['test-profile'])}")
class EsRestClientContainerIntegrationTest {

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

    @Test
    void fetchMappingTest() throws IOException {
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setServiceAddress("127.0.0.1:9200");
        dataSourceContract.setCreateAt(new Date());
        dataSourceContract.setModifyAt(new Date());
        dataSourceContract.setDatasourceName("es");
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(dataSourceContract);
        AbstractElasticClientContainer esRestClientContainer = elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
        //MappingMetaData mappingMetaData = esRestClientContainer.fetchMapping("applog-index-*");
        //Map<String, Object> mapping = mappingMetaData.sourceAsMap();
    }
}