package com.autohome.frostmourne.monitor.config;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.account.impl.DefaultAccountService;
import com.autohome.frostmourne.monitor.service.account.impl.FrostmourneSpiAccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    public ElasticsearchSourceManager elasticsearchSourceManager() {
        return new ElasticsearchSourceManager();
    }

    @Resource
    private DefaultAccountService defaultAccountService;

    @Resource
    private FrostmourneSpiAccountService frostmourneSpiAccountService;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public IAccountService accountService(@Value("${frostmourne.account.type}") String accountType) {
        if (accountType.equalsIgnoreCase("default")) {
            return defaultAccountService;
        }
        return frostmourneSpiAccountService;
    }
}
