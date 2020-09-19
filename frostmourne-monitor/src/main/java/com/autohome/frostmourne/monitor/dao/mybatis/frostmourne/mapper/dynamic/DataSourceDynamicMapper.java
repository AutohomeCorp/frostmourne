package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DataSourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DataSource;
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
public interface DataSourceDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    BasicColumn[] selectList = BasicColumn.columnList(id, datasourceName, datasourceType, serviceAddress, properties, creator, createAt, modifier, modifyAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source Table: data_source")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source Table: data_source")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.984+08:00", comments="Source Table: data_source")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<DataSource> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DataSourceResult")
    Optional<DataSource> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DataSourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="datasource_name", property="datasourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="datasource_type", property="datasourceType", jdbcType=JdbcType.VARCHAR),
        @Result(column="service_address", property="serviceAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DataSource> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default int insert(DataSource record) {
        return MyBatis3Utils.insert(this::insert, record, dataSource, c ->
            c.map(datasourceName).toProperty("datasourceName")
            .map(datasourceType).toProperty("datasourceType")
            .map(serviceAddress).toProperty("serviceAddress")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifier).toProperty("modifier")
            .map(modifyAt).toProperty("modifyAt")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default int insertSelective(DataSource record) {
        return MyBatis3Utils.insert(this::insert, record, dataSource, c ->
            c.map(datasourceName).toPropertyWhenPresent("datasourceName", record::getDatasourceName)
            .map(datasourceType).toPropertyWhenPresent("datasourceType", record::getDatasourceType)
            .map(serviceAddress).toPropertyWhenPresent("serviceAddress", record::getServiceAddress)
            .map(properties).toPropertyWhenPresent("properties", record::getProperties)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default Optional<DataSource> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default List<DataSource> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default List<DataSource> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default Optional<DataSource> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.985+08:00", comments="Source Table: data_source")
    static UpdateDSL<UpdateModel> updateAllColumns(DataSource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(datasourceName).equalTo(record::getDatasourceName)
                .set(datasourceType).equalTo(record::getDatasourceType)
                .set(serviceAddress).equalTo(record::getServiceAddress)
                .set(properties).equalTo(record::getProperties)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifier).equalTo(record::getModifier)
                .set(modifyAt).equalTo(record::getModifyAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source Table: data_source")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DataSource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(datasourceName).equalToWhenPresent(record::getDatasourceName)
                .set(datasourceType).equalToWhenPresent(record::getDatasourceType)
                .set(serviceAddress).equalToWhenPresent(record::getServiceAddress)
                .set(properties).equalToWhenPresent(record::getProperties)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source Table: data_source")
    default int updateByPrimaryKey(DataSource record) {
        return update(c ->
            c.set(datasourceName).equalTo(record::getDatasourceName)
            .set(datasourceType).equalTo(record::getDatasourceType)
            .set(serviceAddress).equalTo(record::getServiceAddress)
            .set(properties).equalTo(record::getProperties)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifier).equalTo(record::getModifier)
            .set(modifyAt).equalTo(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.986+08:00", comments="Source Table: data_source")
    default int updateByPrimaryKeySelective(DataSource record) {
        return update(c ->
            c.set(datasourceName).equalToWhenPresent(record::getDatasourceName)
            .set(datasourceType).equalToWhenPresent(record::getDatasourceType)
            .set(serviceAddress).equalToWhenPresent(record::getServiceAddress)
            .set(properties).equalToWhenPresent(record::getProperties)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}