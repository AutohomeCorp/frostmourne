package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataNameDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DataName;
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
public interface DataNameDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, dataName.dataName, displayName, dataSourceId, datasourceType, timestampField, properties, creator, createAt, modifier, modifyAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<DataName> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DataNameResult")
    Optional<DataName> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DataNameResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="data_name", property="dataName", jdbcType=JdbcType.VARCHAR),
        @Result(column="display_name", property="displayName", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="datasource_type", property="datasourceType", jdbcType=JdbcType.VARCHAR),
        @Result(column="timestamp_field", property="timestampField", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DataName> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, dataName, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, dataName, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(DataName record) {
        return MyBatis3Utils.insert(this::insert, record, dataName, c ->
            c.map(dataName.dataName).toProperty("dataName")
            .map(displayName).toProperty("displayName")
            .map(dataSourceId).toProperty("dataSourceId")
            .map(datasourceType).toProperty("datasourceType")
            .map(timestampField).toProperty("timestampField")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifier).toProperty("modifier")
            .map(modifyAt).toProperty("modifyAt")
        );
    }

    default int insertSelective(DataName record) {
        return MyBatis3Utils.insert(this::insert, record, dataName, c ->
            c.map(dataName.dataName).toPropertyWhenPresent("dataName", record::getDataName)
            .map(displayName).toPropertyWhenPresent("displayName", record::getDisplayName)
            .map(dataSourceId).toPropertyWhenPresent("dataSourceId", record::getDataSourceId)
            .map(datasourceType).toPropertyWhenPresent("datasourceType", record::getDatasourceType)
            .map(timestampField).toPropertyWhenPresent("timestampField", record::getTimestampField)
            .map(properties).toPropertyWhenPresent("properties", record::getProperties)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
        );
    }

    default Optional<DataName> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, dataName, completer);
    }

    default List<DataName> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, dataName, completer);
    }

    default List<DataName> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, dataName, completer);
    }

    default Optional<DataName> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, dataName, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(DataName record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dataName.dataName).equalTo(record::getDataName)
                .set(displayName).equalTo(record::getDisplayName)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(datasourceType).equalTo(record::getDatasourceType)
                .set(timestampField).equalTo(record::getTimestampField)
                .set(properties).equalTo(record::getProperties)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifier).equalTo(record::getModifier)
                .set(modifyAt).equalTo(record::getModifyAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(DataName record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dataName.dataName).equalToWhenPresent(record::getDataName)
                .set(displayName).equalToWhenPresent(record::getDisplayName)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(datasourceType).equalToWhenPresent(record::getDatasourceType)
                .set(timestampField).equalToWhenPresent(record::getTimestampField)
                .set(properties).equalToWhenPresent(record::getProperties)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt);
    }

    default int updateByPrimaryKey(DataName record) {
        return update(c ->
            c.set(dataName.dataName).equalTo(record::getDataName)
            .set(displayName).equalTo(record::getDisplayName)
            .set(dataSourceId).equalTo(record::getDataSourceId)
            .set(datasourceType).equalTo(record::getDatasourceType)
            .set(timestampField).equalTo(record::getTimestampField)
            .set(properties).equalTo(record::getProperties)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifier).equalTo(record::getModifier)
            .set(modifyAt).equalTo(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(DataName record) {
        return update(c ->
            c.set(dataName.dataName).equalToWhenPresent(record::getDataName)
            .set(displayName).equalToWhenPresent(record::getDisplayName)
            .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
            .set(datasourceType).equalToWhenPresent(record::getDatasourceType)
            .set(timestampField).equalToWhenPresent(record::getTimestampField)
            .set(properties).equalToWhenPresent(record::getProperties)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}