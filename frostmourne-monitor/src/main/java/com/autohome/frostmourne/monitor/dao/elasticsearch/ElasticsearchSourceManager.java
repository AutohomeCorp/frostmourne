package com.autohome.frostmourne.monitor.dao.elasticsearch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ElasticsearchSourceManager {

    private ConcurrentHashMap<String, EsRestClientContainer> containerMap;

    public void init() {
        this.containerMap = new ConcurrentHashMap<>();
    }

    public EsRestClientContainer findEsRestClientContainer(ElasticsearchInfo elasticsearchInfo) {
        if (containerMap.containsKey(elasticsearchInfo.getName())) {
            return containerMap.get(elasticsearchInfo.getName());
        }

        synchronized (ElasticsearchSourceManager.class) {
            EsRestClientContainer esRestClientContainer = new EsRestClientContainer(elasticsearchInfo.getEsHostList(), elasticsearchInfo.getSniff(), elasticsearchInfo.getSettings());
            esRestClientContainer.init();

            containerMap.put(elasticsearchInfo.getName(), esRestClientContainer);

            return esRestClientContainer;
        }
    }

    public void close() {
        for (Map.Entry<String, EsRestClientContainer> entry : containerMap.entrySet()) {
            entry.getValue().close();
        }
    }
}
