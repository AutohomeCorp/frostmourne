package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.ConfigMap;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ConfigMapDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ConfigMapDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IConfigMapRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigMapRepository implements IConfigMapRepository {

    @Resource
    private ConfigMapDynamicMapper configMapDynamicMapper;

    @Override
    public Optional<ConfigMap> find(String configKey) {
        Optional<ConfigMap> configMapOptional =
            configMapDynamicMapper.selectOne(query -> query.where().and(ConfigMapDynamicSqlSupport.configKey, isEqualTo(configKey)));
        return configMapOptional;
    }
}
