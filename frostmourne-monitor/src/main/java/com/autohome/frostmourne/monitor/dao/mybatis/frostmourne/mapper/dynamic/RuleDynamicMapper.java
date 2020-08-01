package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RuleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Rule;
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
public interface RuleDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.327+08:00", comments="Source Table: rule")
    BasicColumn[] selectList = BasicColumn.columnList(id, rule_type, alarm_id, alert_template, creator, create_at);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.317+08:00", comments="Source Table: rule")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.318+08:00", comments="Source Table: rule")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.319+08:00", comments="Source Table: rule")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Rule> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.321+08:00", comments="Source Table: rule")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RuleResult")
    Optional<Rule> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.322+08:00", comments="Source Table: rule")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RuleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="rule_type", property="rule_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="alert_template", property="alert_template", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Rule> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.323+08:00", comments="Source Table: rule")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.323+08:00", comments="Source Table: rule")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rule, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.324+08:00", comments="Source Table: rule")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rule, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.324+08:00", comments="Source Table: rule")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.324+08:00", comments="Source Table: rule")
    default int insert(Rule record) {
        return MyBatis3Utils.insert(this::insert, record, rule, c ->
            c.map(rule_type).toProperty("rule_type")
            .map(alarm_id).toProperty("alarm_id")
            .map(alert_template).toProperty("alert_template")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.326+08:00", comments="Source Table: rule")
    default int insertSelective(Rule record) {
        return MyBatis3Utils.insert(this::insert, record, rule, c ->
            c.map(rule_type).toPropertyWhenPresent("rule_type", record::getRule_type)
            .map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(alert_template).toPropertyWhenPresent("alert_template", record::getAlert_template)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.328+08:00", comments="Source Table: rule")
    default Optional<Rule> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rule, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.328+08:00", comments="Source Table: rule")
    default List<Rule> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rule, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.329+08:00", comments="Source Table: rule")
    default List<Rule> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rule, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.329+08:00", comments="Source Table: rule")
    default Optional<Rule> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.329+08:00", comments="Source Table: rule")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rule, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.33+08:00", comments="Source Table: rule")
    static UpdateDSL<UpdateModel> updateAllColumns(Rule record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(rule_type).equalTo(record::getRule_type)
                .set(alarm_id).equalTo(record::getAlarm_id)
                .set(alert_template).equalTo(record::getAlert_template)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.33+08:00", comments="Source Table: rule")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Rule record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(rule_type).equalToWhenPresent(record::getRule_type)
                .set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(alert_template).equalToWhenPresent(record::getAlert_template)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.331+08:00", comments="Source Table: rule")
    default int updateByPrimaryKey(Rule record) {
        return update(c ->
            c.set(rule_type).equalTo(record::getRule_type)
            .set(alarm_id).equalTo(record::getAlarm_id)
            .set(alert_template).equalTo(record::getAlert_template)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.332+08:00", comments="Source Table: rule")
    default int updateByPrimaryKeySelective(Rule record) {
        return update(c ->
            c.set(rule_type).equalToWhenPresent(record::getRule_type)
            .set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(alert_template).equalToWhenPresent(record::getAlert_template)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .where(id, isEqualTo(record::getId))
        );
    }
}