package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.ConfigMap;

public interface IConfigMapRepository {

    Optional<ConfigMap> find(String configKey);
}
