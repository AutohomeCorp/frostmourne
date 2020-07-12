package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataNameDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataName;
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
public interface DataNameDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    BasicColumn[] selectList = BasicColumn.columnList(id, data_name, display_name, data_source_id, timestamp_field, properties, creator, create_at, modifier, modify_at, datasource_type);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DataName> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DataName> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DataNameResult")
    Optional<DataName> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DataNameResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="data_name", property="data_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="display_name", property="display_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_source_id", property="data_source_id", jdbcType=JdbcType.BIGINT),
        @Result(column="timestamp_field", property="timestamp_field", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datasource_type", property="datasource_type", jdbcType=JdbcType.VARCHAR)
    })
    List<DataName> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, dataName, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, dataName, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    default int insert(DataName record) {
        return MyBatis3Utils.insert(this::insert, record, dataName, c ->
            c.map(id).toProperty("id")
            .map(data_name).toProperty("data_name")
            .map(display_name).toProperty("display_name")
            .map(data_source_id).toProperty("data_source_id")
            .map(timestamp_field).toProperty("timestamp_field")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modifier).toProperty("modifier")
            .map(modify_at).toProperty("modify_at")
            .map(datasource_type).toProperty("datasource_type")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    default int insertMultiple(Collection<DataName> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, dataName, c ->
            c.map(id).toProperty("id")
            .map(data_name).toProperty("data_name")
            .map(display_name).toProperty("display_name")
            .map(data_source_id).toProperty("data_source_id")
            .map(timestamp_field).toProperty("timestamp_field")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modifier).toProperty("modifier")
            .map(modify_at).toProperty("modify_at")
            .map(datasource_type).toProperty("datasource_type")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.792+08:00", comments="Source Table: data_name")
    default int insertSelective(DataName record) {
        return MyBatis3Utils.insert(this::insert, record, dataName, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(data_name).toPropertyWhenPresent("data_name", record::getData_name)
            .map(display_name).toPropertyWhenPresent("display_name", record::getDisplay_name)
            .map(data_source_id).toPropertyWhenPresent("data_source_id", record::getData_source_id)
            .map(timestamp_field).toPropertyWhenPresent("timestamp_field", record::getTimestamp_field)
            .map(properties).toPropertyWhenPresent("properties", record::getProperties)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
            .map(datasource_type).toPropertyWhenPresent("datasource_type", record::getDatasource_type)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default Optional<DataName> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, dataName, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default List<DataName> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, dataName, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default List<DataName> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, dataName, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default Optional<DataName> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, dataName, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    static UpdateDSL<UpdateModel> updateAllColumns(DataName record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(data_name).equalTo(record::getData_name)
                .set(display_name).equalTo(record::getDisplay_name)
                .set(data_source_id).equalTo(record::getData_source_id)
                .set(timestamp_field).equalTo(record::getTimestamp_field)
                .set(properties).equalTo(record::getProperties)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modifier).equalTo(record::getModifier)
                .set(modify_at).equalTo(record::getModify_at)
                .set(datasource_type).equalTo(record::getDatasource_type);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DataName record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(data_name).equalToWhenPresent(record::getData_name)
                .set(display_name).equalToWhenPresent(record::getDisplay_name)
                .set(data_source_id).equalToWhenPresent(record::getData_source_id)
                .set(timestamp_field).equalToWhenPresent(record::getTimestamp_field)
                .set(properties).equalToWhenPresent(record::getProperties)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modify_at).equalToWhenPresent(record::getModify_at)
                .set(datasource_type).equalToWhenPresent(record::getDatasource_type);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default int updateByPrimaryKey(DataName record) {
        return update(c ->
            c.set(data_name).equalTo(record::getData_name)
            .set(display_name).equalTo(record::getDisplay_name)
            .set(data_source_id).equalTo(record::getData_source_id)
            .set(timestamp_field).equalTo(record::getTimestamp_field)
            .set(properties).equalTo(record::getProperties)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modifier).equalTo(record::getModifier)
            .set(modify_at).equalTo(record::getModify_at)
            .set(datasource_type).equalTo(record::getDatasource_type)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.793+08:00", comments="Source Table: data_name")
    default int updateByPrimaryKeySelective(DataName record) {
        return update(c ->
            c.set(data_name).equalToWhenPresent(record::getData_name)
            .set(display_name).equalToWhenPresent(record::getDisplay_name)
            .set(data_source_id).equalToWhenPresent(record::getData_source_id)
            .set(timestamp_field).equalToWhenPresent(record::getTimestamp_field)
            .set(properties).equalToWhenPresent(record::getProperties)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .set(datasource_type).equalToWhenPresent(record::getDatasource_type)
            .where(id, isEqualTo(record::getId))
        );
    }
}