package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.generate;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;

public interface AlarmLogMapper {
    AlarmLog selectByPrimaryKey(Long id);
}