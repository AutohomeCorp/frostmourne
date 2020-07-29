package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Metric;

public interface IMetricRepository {

    int deleteByPrimaryKey(Long id);

    int insert(Metric record);

    int insertSelective(Metric record);

    Optional<Metric> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Metric record);

    int updateByPrimaryKey(Metric record);

    int deleteByAlarm(Long alarmId);

    Optional<Metric> findOneByAlarm(Long alarmId);

    long datasourceCount(Long datasourceId);

    long datanameCount(Long datanameId);
}
