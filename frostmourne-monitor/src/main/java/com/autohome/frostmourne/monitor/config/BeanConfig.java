package com.autohome.frostmourne.monitor.config;

import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public ElasticsearchSourceManager elasticsearchSourceManager() {
        return new ElasticsearchSourceManager();
    }
}
