package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ConfigMapDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ConfigMap;
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
public interface ConfigMapDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.857+08:00", comments="Source Table: config_map")
    BasicColumn[] selectList = BasicColumn.columnList(id, configKey, configValue, creator, createAt, modifyAt, modifier);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.849+08:00", comments="Source Table: config_map")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.85+08:00", comments="Source Table: config_map")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.851+08:00", comments="Source Table: config_map")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<ConfigMap> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.853+08:00", comments="Source Table: config_map")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ConfigMapResult")
    Optional<ConfigMap> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.853+08:00", comments="Source Table: config_map")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ConfigMapResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="config_key", property="configKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="config_value", property="configValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    List<ConfigMap> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.853+08:00", comments="Source Table: config_map")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.854+08:00", comments="Source Table: config_map")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, configMap, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.854+08:00", comments="Source Table: config_map")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, configMap, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.854+08:00", comments="Source Table: config_map")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.854+08:00", comments="Source Table: config_map")
    default int insert(ConfigMap record) {
        return MyBatis3Utils.insert(this::insert, record, configMap, c ->
            c.map(configKey).toProperty("configKey")
            .map(configValue).toProperty("configValue")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifyAt).toProperty("modifyAt")
            .map(modifier).toProperty("modifier")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.856+08:00", comments="Source Table: config_map")
    default int insertSelective(ConfigMap record) {
        return MyBatis3Utils.insert(this::insert, record, configMap, c ->
            c.map(configKey).toPropertyWhenPresent("configKey", record::getConfigKey)
            .map(configValue).toPropertyWhenPresent("configValue", record::getConfigValue)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.858+08:00", comments="Source Table: config_map")
    default Optional<ConfigMap> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, configMap, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.859+08:00", comments="Source Table: config_map")
    default List<ConfigMap> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, configMap, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.859+08:00", comments="Source Table: config_map")
    default List<ConfigMap> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, configMap, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.859+08:00", comments="Source Table: config_map")
    default Optional<ConfigMap> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.86+08:00", comments="Source Table: config_map")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, configMap, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.86+08:00", comments="Source Table: config_map")
    static UpdateDSL<UpdateModel> updateAllColumns(ConfigMap record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(configKey).equalTo(record::getConfigKey)
                .set(configValue).equalTo(record::getConfigValue)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifyAt).equalTo(record::getModifyAt)
                .set(modifier).equalTo(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.86+08:00", comments="Source Table: config_map")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ConfigMap record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(configKey).equalToWhenPresent(record::getConfigKey)
                .set(configValue).equalToWhenPresent(record::getConfigValue)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt)
                .set(modifier).equalToWhenPresent(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.861+08:00", comments="Source Table: config_map")
    default int updateByPrimaryKey(ConfigMap record) {
        return update(c ->
            c.set(configKey).equalTo(record::getConfigKey)
            .set(configValue).equalTo(record::getConfigValue)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifyAt).equalTo(record::getModifyAt)
            .set(modifier).equalTo(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-10T11:15:14.862+08:00", comments="Source Table: config_map")
    default int updateByPrimaryKeySelective(ConfigMap record) {
        return update(c ->
            c.set(configKey).equalToWhenPresent(record::getConfigKey)
            .set(configValue).equalToWhenPresent(record::getConfigValue)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }
}