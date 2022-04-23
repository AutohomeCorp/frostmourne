package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Metric;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.MetricDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.MetricDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IMetricRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MetricRepository implements IMetricRepository {

    @Resource
    private MetricDynamicMapper metricDynamicMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return metricDynamicMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Metric record) {
        return metricDynamicMapper.insert(record);
    }

    @Override
    public int insertSelective(Metric record) {
        return metricDynamicMapper.insertSelective(record);
    }

    @Override
    public Optional<Metric> selectByPrimaryKey(Long id) {
        return metricDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Metric record) {
        return metricDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Metric record) {
        return metricDynamicMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByAlarm(Long alarmId) {
        return metricDynamicMapper.delete(query -> query.where().and(MetricDynamicSqlSupport.alarmId, isEqualTo(alarmId)));
    }

    @Override
    public Optional<Metric> findOneByAlarm(Long alarmId) {
        return metricDynamicMapper.selectOne(query -> query.where().and(MetricDynamicSqlSupport.alarmId, isEqualTo(alarmId)));
    }

    @Override
    public long datasourceCount(Long datasourceId) {
        return metricDynamicMapper.count(query -> query.where().and(MetricDynamicSqlSupport.dataSourceId, isEqualTo(datasourceId)));
    }

    @Override
    public long datanameCount(Long datanameId) {
        return metricDynamicMapper.count(query -> query.where().and(MetricDynamicSqlSupport.dataNameId, isEqualTo(datanameId)));
    }
}
