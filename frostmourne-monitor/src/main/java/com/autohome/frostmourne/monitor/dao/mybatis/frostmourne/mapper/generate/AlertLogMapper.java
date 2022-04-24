package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.generate;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;

public interface AlertLogMapper {
    AlertLog selectByPrimaryKey(Long id);
}