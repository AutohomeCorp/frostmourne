package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.common.jackson.JacksonUtil;

public class ElasticsearchSourceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchSourceManager.class);

    private ConcurrentHashMap<String, AbstractElasticClientContainer> containerMap;

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void init() {
        this.containerMap = new ConcurrentHashMap<>();
    }

    public AbstractElasticClientContainer findEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        if (containerMap.containsKey(elasticsearchInfo.getName())) {
            AbstractElasticClientContainer currentEsRestClientContainer = containerMap.get(elasticsearchInfo.getName());
            if (currentEsRestClientContainer.getInitTimestamp() >= elasticsearchInfo.getLastUpdateTime()) {
                return currentEsRestClientContainer;
            } else {
                LOGGER.warn("elasticsearch updated after init, start reload. info: {}", JacksonUtil.serialize(elasticsearchInfo));
                reloadEsRestClientContainer(elasticsearchInfo);
            }
        } else {
            addEsRestClientContainer(elasticsearchInfo);
        }

        return containerMap.get(elasticsearchInfo.getName());
    }

    public synchronized boolean reloadEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        if (!containerMap.containsKey(elasticsearchInfo.getName())) {
            return true;
        }
        EsRestClientContainer newEsRestClientContainer =
            new EsRestClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
        newEsRestClientContainer.init();
        if (!newEsRestClientContainer.health()) {
            LOGGER.warn("elasticsearch not health when reload. info: {}", JacksonUtil.serialize(elasticsearchInfo));
            return false;
        }

        AbstractElasticClientContainer oldEsRestClientContainer = containerMap.get(elasticsearchInfo.getName());
        containerMap.put(elasticsearchInfo.getName(), newEsRestClientContainer);
        Runnable task = oldEsRestClientContainer::close;
        executor.schedule(task, 5, TimeUnit.MINUTES);
        LOGGER.warn("elasticsearch reload success. info: {}", JacksonUtil.serialize(elasticsearchInfo));
        return true;
    }

    public synchronized void addEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        AbstractElasticClientContainer esRestClientContainer = null;
        if (elasticsearchInfo.getVersion().startsWith("6.") || elasticsearchInfo.getVersion().startsWith("7.")) {
            esRestClientContainer = new EsRestClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
        } else if (elasticsearchInfo.getVersion().startsWith("8.")) {
            esRestClientContainer =
                new Elasticsearch8ClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
        } else {
            throw new RuntimeException("not support es version: " + elasticsearchInfo.getVersion());
        }
        esRestClientContainer.init();
        containerMap.put(elasticsearchInfo.getName(), esRestClientContainer);
    }

    public void close() {
        executor.shutdown();
        for (Map.Entry<String, AbstractElasticClientContainer> entry : containerMap.entrySet()) {
            try {
                entry.getValue().close();
            } catch (Exception ex) {
                LOGGER.error("error when close es client", ex);
            }
        }
    }
}
