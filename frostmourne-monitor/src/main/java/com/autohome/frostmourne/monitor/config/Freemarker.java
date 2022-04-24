package com.autohome.frostmourne.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.TemplateExceptionHandler;

@Configuration
public class Freemarker {

    @Bean
    public freemarker.template.Configuration dynamicConfig() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_25);
        configuration.setDateTimeFormat("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        configuration.setClassicCompatible(true);
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setTemplateLoader(new StringTemplateLoader());

        return configuration;
    }
}
