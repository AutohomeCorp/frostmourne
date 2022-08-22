package com.autohome.frostmourne.monitor.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件配置类
 *
 * @author Aping
 * @since 2022/3/28 12:52
 */
@Component
@ConfigurationProperties(prefix = "wechat")
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

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
