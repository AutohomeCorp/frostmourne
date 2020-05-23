package com.autohome.frostmourne.monitor.config;

import com.autohome.frostmourne.monitor.dao.http.IXxlJob;
import com.autohome.frostmourne.monitor.dao.http.MockXxlJob;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignApiConfig {

    @Value("${xxl.job.admin.addresses}")
    private String xxlJobAdminHost;

    @Value("${xxl.job.mock}")
    private Boolean mock;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public IXxlJob xxlJobApi(Retryer retryer, Request.Options defaultOptions) {
        if (mock) {
            return new MockXxlJob();
        }
        return Feign.builder().options(defaultOptions)
                .encoder(new FormEncoder(new JacksonEncoder()))
                .decoder(new JacksonDecoder())
                .retryer(retryer)
                .client(okHttpClient())
                .target(IXxlJob.class, xxlJobAdminHost);
    }
}
