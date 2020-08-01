package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Metric;

public interface MetricMapper {

    Metric selectByPrimaryKey(Long id);
}