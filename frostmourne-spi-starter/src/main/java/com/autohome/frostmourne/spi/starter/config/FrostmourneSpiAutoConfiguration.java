package com.autohome.frostmourne.spi.starter.config;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.starter.api.IFrostmourneSpiApi;
import com.autohome.frostmourne.spi.starter.mock.MockFrostmourneSpi;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(FrostmourneSpiProperties.class)
@ConditionalOnProperty(prefix = "frostmourne.spi", name = "service-addr")
public class FrostmourneSpiAutoConfiguration {

    private FrostmourneSpiProperties frostmourneSpiProperties;

    @Resource
    private OkHttpClient okHttpClient;

    public FrostmourneSpiAutoConfiguration(FrostmourneSpiProperties frostmourneSpiProperties) {
        this.frostmourneSpiProperties = frostmourneSpiProperties;
    }

    private Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    private Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }

    private Request.Options defaultOptions() {
        return new Request.Options(10000, TimeUnit.MILLISECONDS, 10000, TimeUnit.MILLISECONDS, true);
    }

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public IFrostmourneSpiApi frostmourneSpiApi() {

        if (frostmourneSpiProperties.getMock()) {
            return new MockFrostmourneSpi();
        }

        return Feign.builder().options(defaultOptions())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .retryer(feignRetryer())
                .client(okHttpClient)
                .logLevel(feignLoggerLevel())
                .target(IFrostmourneSpiApi.class, frostmourneSpiProperties.getServiceAddr());
    }
}
