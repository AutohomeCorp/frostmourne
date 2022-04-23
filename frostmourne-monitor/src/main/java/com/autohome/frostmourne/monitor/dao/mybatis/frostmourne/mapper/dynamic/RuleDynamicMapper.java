package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RuleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Rule;
import java.util.List;
import java.util.Optional;
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
    BasicColumn[] selectList = BasicColumn.columnList(id, ruleType, alarmId, alertTemplate, creator, createAt, alertTemplateType);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Rule> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RuleResult")
    Optional<Rule> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RuleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="rule_type", property="ruleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="alert_template", property="alertTemplate", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="alert_template_type", property="alertTemplateType", jdbcType=JdbcType.VARCHAR)
    })
    List<Rule> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rule, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rule, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(Rule record) {
        return MyBatis3Utils.insert(this::insert, record, rule, c ->
            c.map(ruleType).toProperty("ruleType")
            .map(alarmId).toProperty("alarmId")
            .map(alertTemplate).toProperty("alertTemplate")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(alertTemplateType).toProperty("alertTemplateType")
        );
    }

    default int insertSelective(Rule record) {
        return MyBatis3Utils.insert(this::insert, record, rule, c ->
            c.map(ruleType).toPropertyWhenPresent("ruleType", record::getRuleType)
            .map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(alertTemplate).toPropertyWhenPresent("alertTemplate", record::getAlertTemplate)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(alertTemplateType).toPropertyWhenPresent("alertTemplateType", record::getAlertTemplateType)
        );
    }

    default Optional<Rule> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rule, completer);
    }

    default List<Rule> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rule, completer);
    }

    default List<Rule> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rule, completer);
    }

    default Optional<Rule> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rule, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(Rule record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(ruleType).equalTo(record::getRuleType)
                .set(alarmId).equalTo(record::getAlarmId)
                .set(alertTemplate).equalTo(record::getAlertTemplate)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(alertTemplateType).equalTo(record::getAlertTemplateType);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(Rule record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(ruleType).equalToWhenPresent(record::getRuleType)
                .set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(alertTemplate).equalToWhenPresent(record::getAlertTemplate)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(alertTemplateType).equalToWhenPresent(record::getAlertTemplateType);
    }

    default int updateByPrimaryKey(Rule record) {
        return update(c ->
            c.set(ruleType).equalTo(record::getRuleType)
            .set(alarmId).equalTo(record::getAlarmId)
            .set(alertTemplate).equalTo(record::getAlertTemplate)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(alertTemplateType).equalTo(record::getAlertTemplateType)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(Rule record) {
        return update(c ->
            c.set(ruleType).equalToWhenPresent(record::getRuleType)
            .set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(alertTemplate).equalToWhenPresent(record::getAlertTemplate)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(alertTemplateType).equalToWhenPresent(record::getAlertTemplateType)
            .where(id, isEqualTo(record::getId))
        );
    }
}