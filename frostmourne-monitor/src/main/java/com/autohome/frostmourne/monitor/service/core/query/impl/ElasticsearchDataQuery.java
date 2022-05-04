package com.autohome.frostmourne.monitor.service.core.query.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.elasticsearch.AbstractElasticClientContainer;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchInfo;
import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.model.contract.DataNameContract;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.autohome.frostmourne.monitor.model.contract.ElasticsearchDataResult;
import com.autohome.frostmourne.monitor.model.contract.MetricContract;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.query.IElasticsearchDataQuery;

@Service
public class ElasticsearchDataQuery implements IElasticsearchDataQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchDataQuery.class);

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

    AbstractElasticClientContainer findEsRestClientContainer(DataSourceContract dataSourceContract) {
        ElasticsearchInfo elasticsearchInfo = new ElasticsearchInfo(dataSourceContract);
        return elasticsearchSourceManager.findEsRestClientContainer(elasticsearchInfo);
    }

    @Override
    public ElasticsearchDataResult query(DataNameContract dataNameContract, DataSourceContract dataSourceContract, DateTime start, DateTime end, String esQuery,
        String scrollId, String sortOrder, Integer intervalInSeconds) {
        AbstractElasticClientContainer esRestClientContainer = this.findEsRestClientContainer(dataSourceContract);
        return esRestClientContainer.query(dataNameContract, start, end, esQuery, scrollId, sortOrder, intervalInSeconds);
    }

    @Override
    public List<String> queryMappingFileds(DataNameContract dataNameContract, DataSourceContract dataSourceContract) {
        AbstractElasticClientContainer esRestClientContainer = this.findEsRestClientContainer(dataSourceContract);
        try {
            String index = dataNameContract.getSettings().get("indexPrefix") + "*";
            return esRestClientContainer.fetchAllMappingFields(index);
        } catch (Exception e) {
            throw new RuntimeException("getMapping error: " + e.getMessage(), e);
        }
    }

    @Override
    public MetricData queryElasticsearchMetricValue(DateTime start, DateTime end, MetricContract metricContract) throws IOException {
        AbstractElasticClientContainer esRestClientContainer = this.findEsRestClientContainer(metricContract.getDataSourceContract());
        return esRestClientContainer.queryElasticsearchMetricValue(start, end, metricContract);
    }
}
