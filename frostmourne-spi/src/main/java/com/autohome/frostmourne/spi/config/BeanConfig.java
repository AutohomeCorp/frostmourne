package com.autohome.frostmourne.spi.config;

import com.autohome.frostmourne.spi.dao.IEmailSender;
import com.autohome.frostmourne.spi.dao.impl.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Value("${email.smtp.host}")
    private String smtpHost;

    @Value("${email.smtp.port}")
    private String smtpPort;

    @Value("${email.sender}")
    private String sender;

    @Value("${email.sender.password}")
    private String senderPassword;

    @Bean
    public IEmailSender emailSender() {
        return new EmailSender(smtpHost, smtpPort, sender, senderPassword);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
