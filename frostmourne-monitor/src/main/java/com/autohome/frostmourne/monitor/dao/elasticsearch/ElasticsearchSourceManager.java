package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ElasticsearchSourceManager {

    private ConcurrentHashMap<String, EsRestClientContainer> containerMap;

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public void init() {
        this.containerMap = new ConcurrentHashMap<>();
    }

    public EsRestClientContainer findEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        if (containerMap.containsKey(elasticsearchInfo.getName())) {
            return containerMap.get(elasticsearchInfo.getName());
        }

        addEsRestClientContainer(elasticsearchInfo);
        return containerMap.get(elasticsearchInfo.getName());
    }

    public boolean reloadEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        if (!containerMap.containsKey(elasticsearchInfo.getName())) {
            return true;
        }
        EsRestClientContainer newEsRestClientContainer = new EsRestClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
        newEsRestClientContainer.init();
        if (!newEsRestClientContainer.health()) {
            return false;
        }

        EsRestClientContainer oldEsRestClientContainer = containerMap.get(elasticsearchInfo.getName());
        containerMap.put(elasticsearchInfo.getName(), newEsRestClientContainer);
        Runnable task = oldEsRestClientContainer::close;
        executor.schedule(task, 5, TimeUnit.MINUTES);
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
