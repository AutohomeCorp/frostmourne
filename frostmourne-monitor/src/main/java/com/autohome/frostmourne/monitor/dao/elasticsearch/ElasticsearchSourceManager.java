package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchSourceManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(ElasticsearchSourceManager.class);

    private ConcurrentHashMap<String, EsRestClientContainer> containerMap;

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void init() {
        this.containerMap = new ConcurrentHashMap<>();
    }

    public EsRestClientContainer findEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        if (containerMap.containsKey(elasticsearchInfo.getName())) {
            EsRestClientContainer currentEsRestClientContainer = containerMap.get(elasticsearchInfo.getName());
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
        EsRestClientContainer newEsRestClientContainer = new EsRestClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
        newEsRestClientContainer.init();
        if (!newEsRestClientContainer.health()) {
            LOGGER.warn("elasticsearch not health when reload. info: {}", JacksonUtil.serialize(elasticsearchInfo));
            return false;
        }

        EsRestClientContainer oldEsRestClientContainer = containerMap.get(elasticsearchInfo.getName());
        containerMap.put(elasticsearchInfo.getName(), newEsRestClientContainer);
        Runnable task = oldEsRestClientContainer::close;
        executor.schedule(task, 5, TimeUnit.MINUTES);
        LOGGER.warn("elasticsearch reload success. info: {}", JacksonUtil.serialize(elasticsearchInfo));
        return true;
    }

    public synchronized boolean addEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        EsRestClientContainer esRestClientContainer = new EsRestClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
        esRestClientContainer.init();
        containerMap.put(elasticsearchInfo.getName(), esRestClientContainer);
        return true;
    }

    public void close() {
        executor.shutdown();
        for (Map.Entry<String, EsRestClientContainer> entry : containerMap.entrySet()) {
            entry.getValue().close();
        }
    }
}
