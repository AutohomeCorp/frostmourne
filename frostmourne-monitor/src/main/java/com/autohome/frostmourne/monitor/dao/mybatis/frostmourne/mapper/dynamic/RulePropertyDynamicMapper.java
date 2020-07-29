package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RulePropertyDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.RuleProperty;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
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
public interface RulePropertyDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarm_id, rule_id, prop_key, prop_value, creator, create_at);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RuleProperty> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RulePropertyResult")
    Optional<RuleProperty> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RulePropertyResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="rule_id", property="rule_id", jdbcType=JdbcType.BIGINT),
        @Result(column="prop_key", property="prop_key", jdbcType=JdbcType.VARCHAR),
        @Result(column="prop_value", property="prop_value", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP)
    })
    List<RuleProperty> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, ruleProperty, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ruleProperty, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    default int insert(RuleProperty record) {
        return MyBatis3Utils.insert(this::insert, record, ruleProperty, c ->
            c.map(alarm_id).toProperty("alarm_id")
            .map(rule_id).toProperty("rule_id")
            .map(prop_key).toProperty("prop_key")
            .map(prop_value).toProperty("prop_value")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    default int insertSelective(RuleProperty record) {
        return MyBatis3Utils.insert(this::insert, record, ruleProperty, c ->
            c.map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(rule_id).toPropertyWhenPresent("rule_id", record::getRule_id)
            .map(prop_key).toPropertyWhenPresent("prop_key", record::getProp_key)
            .map(prop_value).toPropertyWhenPresent("prop_value", record::getProp_value)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.336+08:00", comments="Source Table: rule_property")
    default Optional<RuleProperty> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ruleProperty, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    default List<RuleProperty> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ruleProperty, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    default List<RuleProperty> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ruleProperty, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    default Optional<RuleProperty> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ruleProperty, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    static UpdateDSL<UpdateModel> updateAllColumns(RuleProperty record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_id).equalTo(record::getAlarm_id)
                .set(rule_id).equalTo(record::getRule_id)
                .set(prop_key).equalTo(record::getProp_key)
                .set(prop_value).equalTo(record::getProp_value)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RuleProperty record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(rule_id).equalToWhenPresent(record::getRule_id)
                .set(prop_key).equalToWhenPresent(record::getProp_key)
                .set(prop_value).equalToWhenPresent(record::getProp_value)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    default int updateByPrimaryKey(RuleProperty record) {
        return update(c ->
            c.set(alarm_id).equalTo(record::getAlarm_id)
            .set(rule_id).equalTo(record::getRule_id)
            .set(prop_key).equalTo(record::getProp_key)
            .set(prop_value).equalTo(record::getProp_value)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.337+08:00", comments="Source Table: rule_property")
    default int updateByPrimaryKeySelective(RuleProperty record) {
        return update(c ->
            c.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(rule_id).equalToWhenPresent(record::getRule_id)
            .set(prop_key).equalToWhenPresent(record::getProp_key)
            .set(prop_value).equalToWhenPresent(record::getProp_value)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .where(id, isEqualTo(record::getId))
        );
    }
}