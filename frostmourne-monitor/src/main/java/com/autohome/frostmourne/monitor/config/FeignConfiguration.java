package com.autohome.frostmourne.monitor.config;

import java.util.concurrent.TimeUnit;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    Request.Options defaultOptions() {
        return new Request.Options(1, TimeUnit.SECONDS, 4, TimeUnit.SECONDS, true);
    }
}
