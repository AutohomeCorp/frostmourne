package com.autohome.frostmourne.monitor.service.core.query;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchDataQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchDataQuery.class);

    @Resource
    private ElasticsearchSourceManager elasticsearchSourceManager;

}
