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
@ConfigurationProperties(prefix = "wechat")
@Getter
@Setter
public class WechatProperties {

    /**
     * 企业Id
     */
    private String corpId;

    /**
     * 应用Id
     */
    private String agentId;

    /**
     * 应用密钥
     */
    private String secret;
}
