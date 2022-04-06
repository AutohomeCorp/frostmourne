package com.autohome.frostmourne.monitor.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 邮件配置类
 *
 * @author Aping
 * @since 2022/3/28 12:52
 */

@Component
@ConfigurationProperties(prefix = "email")
@Getter
@Setter
public class MailConfigProperties {

    private String smtpHost;

    private String smtpPort;

    private String smtpAuth;

    private String sender;

    private String senderPassword;

    private String tlsEnable;

}
