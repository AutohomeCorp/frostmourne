package com.autohome.frostmourne.spi.config;

import com.autohome.frostmourne.spi.service.impl.FileUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Value("${auth.user.jsonfile}")
    private String userJsonFile;

    @Value("${auth.team.jsonfile}")
    private String teamJsonFile;

    @Value("${auth.department.jsonfile}")
    private String departmentJsonFile;

    @Bean
    public FileUserService fileUserService() {
        return new FileUserService(userJsonFile, teamJsonFile, departmentJsonFile);
    }
}
