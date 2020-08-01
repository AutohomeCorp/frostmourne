package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertTemplateDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertTemplate;
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
public interface AlertTemplateDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.616+08:00", comments="Source Table: alert_template")
    BasicColumn[] selectList = BasicColumn.columnList(id, template_name, template_type, template_union_code, content, creator, create_at, modifier, modify_at);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.607+08:00", comments="Source Table: alert_template")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.608+08:00", comments="Source Table: alert_template")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.608+08:00", comments="Source Table: alert_template")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlertTemplate> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.61+08:00", comments="Source Table: alert_template")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertTemplateResult")
    Optional<AlertTemplate> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.611+08:00", comments="Source Table: alert_template")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertTemplateResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="template_name", property="template_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_type", property="template_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_union_code", property="template_union_code", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AlertTemplate> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.612+08:00", comments="Source Table: alert_template")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.612+08:00", comments="Source Table: alert_template")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alertTemplate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.612+08:00", comments="Source Table: alert_template")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alertTemplate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.613+08:00", comments="Source Table: alert_template")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.613+08:00", comments="Source Table: alert_template")
    default int insert(AlertTemplate record) {
        return MyBatis3Utils.insert(this::insert, record, alertTemplate, c ->
            c.map(template_name).toProperty("template_name")
            .map(template_type).toProperty("template_type")
            .map(template_union_code).toProperty("template_union_code")
            .map(content).toProperty("content")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modifier).toProperty("modifier")
            .map(modify_at).toProperty("modify_at")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.614+08:00", comments="Source Table: alert_template")
    default int insertSelective(AlertTemplate record) {
        return MyBatis3Utils.insert(this::insert, record, alertTemplate, c ->
            c.map(template_name).toPropertyWhenPresent("template_name", record::getTemplate_name)
            .map(template_type).toPropertyWhenPresent("template_type", record::getTemplate_type)
            .map(template_union_code).toPropertyWhenPresent("template_union_code", record::getTemplate_union_code)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.616+08:00", comments="Source Table: alert_template")
    default Optional<AlertTemplate> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alertTemplate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.617+08:00", comments="Source Table: alert_template")
    default List<AlertTemplate> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alertTemplate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.617+08:00", comments="Source Table: alert_template")
    default List<AlertTemplate> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alertTemplate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.617+08:00", comments="Source Table: alert_template")
    default Optional<AlertTemplate> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.618+08:00", comments="Source Table: alert_template")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alertTemplate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.618+08:00", comments="Source Table: alert_template")
    static UpdateDSL<UpdateModel> updateAllColumns(AlertTemplate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(template_name).equalTo(record::getTemplate_name)
                .set(template_type).equalTo(record::getTemplate_type)
                .set(template_union_code).equalTo(record::getTemplate_union_code)
                .set(content).equalTo(record::getContent)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modifier).equalTo(record::getModifier)
                .set(modify_at).equalTo(record::getModify_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.619+08:00", comments="Source Table: alert_template")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlertTemplate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(template_name).equalToWhenPresent(record::getTemplate_name)
                .set(template_type).equalToWhenPresent(record::getTemplate_type)
                .set(template_union_code).equalToWhenPresent(record::getTemplate_union_code)
                .set(content).equalToWhenPresent(record::getContent)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modify_at).equalToWhenPresent(record::getModify_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.619+08:00", comments="Source Table: alert_template")
    default int updateByPrimaryKey(AlertTemplate record) {
        return update(c ->
            c.set(template_name).equalTo(record::getTemplate_name)
            .set(template_type).equalTo(record::getTemplate_type)
            .set(template_union_code).equalTo(record::getTemplate_union_code)
            .set(content).equalTo(record::getContent)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modifier).equalTo(record::getModifier)
            .set(modify_at).equalTo(record::getModify_at)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-31T21:47:40.62+08:00", comments="Source Table: alert_template")
    default int updateByPrimaryKeySelective(AlertTemplate record) {
        return update(c ->
            c.set(template_name).equalToWhenPresent(record::getTemplate_name)
            .set(template_type).equalToWhenPresent(record::getTemplate_type)
            .set(template_union_code).equalToWhenPresent(record::getTemplate_union_code)
            .set(content).equalToWhenPresent(record::getContent)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .where(id, isEqualTo(record::getId))
        );
    }
}