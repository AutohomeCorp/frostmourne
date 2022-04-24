package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertTemplateDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertTemplate;
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
public interface AlertTemplateDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, templateName, templateType, templateUnionCode, content, creator, createAt, modifier, modifyAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlertTemplate> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertTemplateResult")
    Optional<AlertTemplate> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertTemplateResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="template_name", property="templateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_type", property="templateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_union_code", property="templateUnionCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AlertTemplate> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alertTemplate, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alertTemplate, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(AlertTemplate record) {
        return MyBatis3Utils.insert(this::insert, record, alertTemplate, c ->
            c.map(templateName).toProperty("templateName")
            .map(templateType).toProperty("templateType")
            .map(templateUnionCode).toProperty("templateUnionCode")
            .map(content).toProperty("content")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifier).toProperty("modifier")
            .map(modifyAt).toProperty("modifyAt")
        );
    }

    default int insertSelective(AlertTemplate record) {
        return MyBatis3Utils.insert(this::insert, record, alertTemplate, c ->
            c.map(templateName).toPropertyWhenPresent("templateName", record::getTemplateName)
            .map(templateType).toPropertyWhenPresent("templateType", record::getTemplateType)
            .map(templateUnionCode).toPropertyWhenPresent("templateUnionCode", record::getTemplateUnionCode)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
        );
    }

    default Optional<AlertTemplate> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alertTemplate, completer);
    }

    default List<AlertTemplate> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alertTemplate, completer);
    }

    default List<AlertTemplate> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alertTemplate, completer);
    }

    default Optional<AlertTemplate> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alertTemplate, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(AlertTemplate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(templateName).equalTo(record::getTemplateName)
                .set(templateType).equalTo(record::getTemplateType)
                .set(templateUnionCode).equalTo(record::getTemplateUnionCode)
                .set(content).equalTo(record::getContent)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifier).equalTo(record::getModifier)
                .set(modifyAt).equalTo(record::getModifyAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlertTemplate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(templateName).equalToWhenPresent(record::getTemplateName)
                .set(templateType).equalToWhenPresent(record::getTemplateType)
                .set(templateUnionCode).equalToWhenPresent(record::getTemplateUnionCode)
                .set(content).equalToWhenPresent(record::getContent)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt);
    }

    default int updateByPrimaryKey(AlertTemplate record) {
        return update(c ->
            c.set(templateName).equalTo(record::getTemplateName)
            .set(templateType).equalTo(record::getTemplateType)
            .set(templateUnionCode).equalTo(record::getTemplateUnionCode)
            .set(content).equalTo(record::getContent)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifier).equalTo(record::getModifier)
            .set(modifyAt).equalTo(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(AlertTemplate record) {
        return update(c ->
            c.set(templateName).equalToWhenPresent(record::getTemplateName)
            .set(templateType).equalToWhenPresent(record::getTemplateType)
            .set(templateUnionCode).equalToWhenPresent(record::getTemplateUnionCode)
            .set(content).equalToWhenPresent(record::getContent)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}