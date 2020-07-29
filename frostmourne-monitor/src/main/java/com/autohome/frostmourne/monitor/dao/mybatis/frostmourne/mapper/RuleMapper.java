package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Rule;

public interface RuleMapper {

    Rule selectByPrimaryKey(Long id);

}