package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.MetricDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Metric;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface MetricDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    BasicColumn[] selectList = BasicColumn.columnList(id, aggregation_type, aggregation_field, metric_type, alarm_id, rule_id, data_source_id, data_name_id, data_name, query_string, properties, creator, create_at, post_data);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.798+08:00", comments="Source Table: metric")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.798+08:00", comments="Source Table: metric")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.798+08:00", comments="Source Table: metric")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Metric> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.798+08:00", comments="Source Table: metric")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Metric> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.798+08:00", comments="Source Table: metric")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MetricResult")
    Optional<Metric> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MetricResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="aggregation_type", property="aggregation_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="aggregation_field", property="aggregation_field", jdbcType=JdbcType.VARCHAR),
        @Result(column="metric_type", property="metric_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="rule_id", property="rule_id", jdbcType=JdbcType.BIGINT),
        @Result(column="data_source_id", property="data_source_id", jdbcType=JdbcType.BIGINT),
        @Result(column="data_name_id", property="data_name_id", jdbcType=JdbcType.BIGINT),
        @Result(column="data_name", property="data_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="query_string", property="query_string", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="post_data", property="post_data", jdbcType=JdbcType.VARCHAR)
    })
    List<Metric> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, metric, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, metric, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default int insert(Metric record) {
        return MyBatis3Utils.insert(this::insert, record, metric, c ->
            c.map(id).toProperty("id")
            .map(aggregation_type).toProperty("aggregation_type")
            .map(aggregation_field).toProperty("aggregation_field")
            .map(metric_type).toProperty("metric_type")
            .map(alarm_id).toProperty("alarm_id")
            .map(rule_id).toProperty("rule_id")
            .map(data_source_id).toProperty("data_source_id")
            .map(data_name_id).toProperty("data_name_id")
            .map(data_name).toProperty("data_name")
            .map(query_string).toProperty("query_string")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(post_data).toProperty("post_data")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default int insertMultiple(Collection<Metric> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, metric, c ->
            c.map(id).toProperty("id")
            .map(aggregation_type).toProperty("aggregation_type")
            .map(aggregation_field).toProperty("aggregation_field")
            .map(metric_type).toProperty("metric_type")
            .map(alarm_id).toProperty("alarm_id")
            .map(rule_id).toProperty("rule_id")
            .map(data_source_id).toProperty("data_source_id")
            .map(data_name_id).toProperty("data_name_id")
            .map(data_name).toProperty("data_name")
            .map(query_string).toProperty("query_string")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(post_data).toProperty("post_data")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default int insertSelective(Metric record) {
        return MyBatis3Utils.insert(this::insert, record, metric, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(aggregation_type).toPropertyWhenPresent("aggregation_type", record::getAggregation_type)
            .map(aggregation_field).toPropertyWhenPresent("aggregation_field", record::getAggregation_field)
            .map(metric_type).toPropertyWhenPresent("metric_type", record::getMetric_type)
            .map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(rule_id).toPropertyWhenPresent("rule_id", record::getRule_id)
            .map(data_source_id).toPropertyWhenPresent("data_source_id", record::getData_source_id)
            .map(data_name_id).toPropertyWhenPresent("data_name_id", record::getData_name_id)
            .map(data_name).toPropertyWhenPresent("data_name", record::getData_name)
            .map(query_string).toPropertyWhenPresent("query_string", record::getQuery_string)
            .map(properties).toPropertyWhenPresent("properties", record::getProperties)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(post_data).toPropertyWhenPresent("post_data", record::getPost_data)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default Optional<Metric> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, metric, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default List<Metric> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, metric, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default List<Metric> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, metric, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default Optional<Metric> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, metric, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    static UpdateDSL<UpdateModel> updateAllColumns(Metric record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(aggregation_type).equalTo(record::getAggregation_type)
                .set(aggregation_field).equalTo(record::getAggregation_field)
                .set(metric_type).equalTo(record::getMetric_type)
                .set(alarm_id).equalTo(record::getAlarm_id)
                .set(rule_id).equalTo(record::getRule_id)
                .set(data_source_id).equalTo(record::getData_source_id)
                .set(data_name_id).equalTo(record::getData_name_id)
                .set(data_name).equalTo(record::getData_name)
                .set(query_string).equalTo(record::getQuery_string)
                .set(properties).equalTo(record::getProperties)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(post_data).equalTo(record::getPost_data);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.799+08:00", comments="Source Table: metric")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Metric record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(aggregation_type).equalToWhenPresent(record::getAggregation_type)
                .set(aggregation_field).equalToWhenPresent(record::getAggregation_field)
                .set(metric_type).equalToWhenPresent(record::getMetric_type)
                .set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(rule_id).equalToWhenPresent(record::getRule_id)
                .set(data_source_id).equalToWhenPresent(record::getData_source_id)
                .set(data_name_id).equalToWhenPresent(record::getData_name_id)
                .set(data_name).equalToWhenPresent(record::getData_name)
                .set(query_string).equalToWhenPresent(record::getQuery_string)
                .set(properties).equalToWhenPresent(record::getProperties)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(post_data).equalToWhenPresent(record::getPost_data);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source Table: metric")
    default int updateByPrimaryKey(Metric record) {
        return update(c ->
            c.set(aggregation_type).equalTo(record::getAggregation_type)
            .set(aggregation_field).equalTo(record::getAggregation_field)
            .set(metric_type).equalTo(record::getMetric_type)
            .set(alarm_id).equalTo(record::getAlarm_id)
            .set(rule_id).equalTo(record::getRule_id)
            .set(data_source_id).equalTo(record::getData_source_id)
            .set(data_name_id).equalTo(record::getData_name_id)
            .set(data_name).equalTo(record::getData_name)
            .set(query_string).equalTo(record::getQuery_string)
            .set(properties).equalTo(record::getProperties)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(post_data).equalTo(record::getPost_data)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.8+08:00", comments="Source Table: metric")
    default int updateByPrimaryKeySelective(Metric record) {
        return update(c ->
            c.set(aggregation_type).equalToWhenPresent(record::getAggregation_type)
            .set(aggregation_field).equalToWhenPresent(record::getAggregation_field)
            .set(metric_type).equalToWhenPresent(record::getMetric_type)
            .set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(rule_id).equalToWhenPresent(record::getRule_id)
            .set(data_source_id).equalToWhenPresent(record::getData_source_id)
            .set(data_name_id).equalToWhenPresent(record::getData_name_id)
            .set(data_name).equalToWhenPresent(record::getData_name)
            .set(query_string).equalToWhenPresent(record::getQuery_string)
            .set(properties).equalToWhenPresent(record::getProperties)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(post_data).equalToWhenPresent(record::getPost_data)
            .where(id, isEqualTo(record::getId))
        );
    }
}