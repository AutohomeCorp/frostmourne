package com.autohome.frostmourne.spi.config;

import com.autohome.frostmourne.spi.service.impl.FileUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public FileUserService fileUserService() {
        return new FileUserService(null, null, null);
    }
}
