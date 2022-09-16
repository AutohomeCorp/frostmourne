package com.autohome.frostmourne.monitor.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.config.properties.EncryptProperties;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataSource;
import com.autohome.frostmourne.monitor.tool.AESUtils;
import org.elasticsearch.common.Strings;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.contract.DataSourceContract;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class DataSourceTransformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceTransformer.class);

    public static DataSourceContract model2Contract(DataSource dataSource) {
        return model2Contract(dataSource, false);
    }

    /**
     * Encrypt DataSource sensitive fields, like username, password.
     *
     * @param dataSource dataSource entity
     * @param encrypt whether to encrypt
     * @return {@link DataSourceContract}
     */
    public static DataSourceContract model2Contract(DataSource dataSource, boolean encrypt) {
        DataSourceContract dataSourceContract = new DataSourceContract();
        dataSourceContract.setDatasourceName(dataSource.getDatasourceName());
        dataSourceContract.setDatasourceType(dataSource.getDatasourceType());
        dataSourceContract.setId(dataSource.getId());
        dataSourceContract.setServiceAddress(dataSource.getServiceAddress());
        if (!Strings.isNullOrEmpty(dataSource.getProperties())) {
            Map<String, String> settings = new HashMap<>();
            try {
                settings = JacksonUtil.deSerialize(dataSource.getProperties(), new TypeReference<Map<String, String>>() {});
            } catch (Exception e) {
                // 当加密的key(配置 encrypt.key）发生变更后会出现原来已加密的字符串无法解密，原样输出则json解析失败
                // 此时设置一个空的 settings，能够在"数据源"页面重新设置账号密码等信息，新的key会重新加密
                LOGGER.error("dataSource properties json parse error, datasource: {}", dataSource, e);
            }
            // 加密敏感字段
            if (encrypt) {
                handleEncryptSettings(settings);
            }
            dataSourceContract.setSettings(settings);
        } else {
            dataSourceContract.setSettings(new HashMap<>());
        }
        dataSourceContract.setCreateAt(dataSource.getCreateAt());
        dataSourceContract.setCreator(dataSource.getCreator());
        dataSourceContract.setModifier(dataSource.getModifier());
        dataSourceContract.setModifyAt(dataSource.getModifyAt());
        return dataSourceContract;
    }

    private static void handleEncryptSettings(Map<String, String> settings) {
        List<String> sensitiveFields = EncryptProperties.getInstance().getSensitiveFields();
        if (!CollectionUtils.isEmpty(sensitiveFields)) {
            sensitiveFields.forEach(field -> settings.computeIfPresent(field, (k, v) -> AESUtils.encrypt(v)));
        }
    }
}
