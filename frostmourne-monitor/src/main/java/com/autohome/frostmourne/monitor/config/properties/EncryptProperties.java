package com.autohome.frostmourne.monitor.config.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 加密配置类
 *
 * @author limbo
 * @since 2022/9/14 13:48
 */
@Configuration
public class EncryptProperties implements InitializingBean {

    private static EncryptProperties prop = null;

    public static EncryptProperties getInstance () {
        return prop;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        prop = this;
    }

    /**
     * 密钥
     */
    @Value("${encrypt.key:EX31$@*^ac1}")
    private String key;

    /**
     * 敏感字段列表
     *
     * 默认会对 data_source表 properties字段值进行加密
     * 对于username, password等配置等敏感字段也要进行加解密操作，避免在前端泄漏
     */
    @Value("#{'${encrypt.sensitives:username,password}'.split(',')}")
    private List<String> sensitiveFields;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getSensitiveFields() {
        return sensitiveFields;
    }

    public void setSensitiveFields(List<String> sensitiveFields) {
        this.sensitiveFields = sensitiveFields;
    }
}
