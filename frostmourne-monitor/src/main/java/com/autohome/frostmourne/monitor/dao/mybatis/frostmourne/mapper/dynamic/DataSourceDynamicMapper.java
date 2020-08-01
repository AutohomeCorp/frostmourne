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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.53+08:00", comments="Source Table: data_source")
    BasicColumn[] selectList = BasicColumn.columnList(id, datasource_name, datasource_type, service_address, properties, creator, create_at, modifier, modify_at);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.521+08:00", comments="Source Table: data_source")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.522+08:00", comments="Source Table: data_source")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.522+08:00", comments="Source Table: data_source")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<DataSource> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.524+08:00", comments="Source Table: data_source")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DataSourceResult")
    Optional<DataSource> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.525+08:00", comments="Source Table: data_source")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DataSourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="datasource_name", property="datasource_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="datasource_type", property="datasource_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="service_address", property="service_address", jdbcType=JdbcType.VARCHAR),
        @Result(column="properties", property="properties", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DataSource> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.526+08:00", comments="Source Table: data_source")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.526+08:00", comments="Source Table: data_source")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.526+08:00", comments="Source Table: data_source")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.527+08:00", comments="Source Table: data_source")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.527+08:00", comments="Source Table: data_source")
    default int insert(DataSource record) {
        return MyBatis3Utils.insert(this::insert, record, dataSource, c ->
            c.map(datasource_name).toProperty("datasource_name")
            .map(datasource_type).toProperty("datasource_type")
            .map(service_address).toProperty("service_address")
            .map(properties).toProperty("properties")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modifier).toProperty("modifier")
            .map(modify_at).toProperty("modify_at")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.529+08:00", comments="Source Table: data_source")
    default int insertSelective(DataSource record) {
        return MyBatis3Utils.insert(this::insert, record, dataSource, c ->
            c.map(datasource_name).toPropertyWhenPresent("datasource_name", record::getDatasource_name)
            .map(datasource_type).toPropertyWhenPresent("datasource_type", record::getDatasource_type)
            .map(service_address).toPropertyWhenPresent("service_address", record::getService_address)
            .map(properties).toPropertyWhenPresent("properties", record::getProperties)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.53+08:00", comments="Source Table: data_source")
    default Optional<DataSource> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.531+08:00", comments="Source Table: data_source")
    default List<DataSource> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.531+08:00", comments="Source Table: data_source")
    default List<DataSource> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.532+08:00", comments="Source Table: data_source")
    default Optional<DataSource> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.532+08:00", comments="Source Table: data_source")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, dataSource, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.532+08:00", comments="Source Table: data_source")
    static UpdateDSL<UpdateModel> updateAllColumns(DataSource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(datasource_name).equalTo(record::getDatasource_name)
                .set(datasource_type).equalTo(record::getDatasource_type)
                .set(service_address).equalTo(record::getService_address)
                .set(properties).equalTo(record::getProperties)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modifier).equalTo(record::getModifier)
                .set(modify_at).equalTo(record::getModify_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.533+08:00", comments="Source Table: data_source")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DataSource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(datasource_name).equalToWhenPresent(record::getDatasource_name)
                .set(datasource_type).equalToWhenPresent(record::getDatasource_type)
                .set(service_address).equalToWhenPresent(record::getService_address)
                .set(properties).equalToWhenPresent(record::getProperties)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modify_at).equalToWhenPresent(record::getModify_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.534+08:00", comments="Source Table: data_source")
    default int updateByPrimaryKey(DataSource record) {
        return update(c ->
            c.set(datasource_name).equalTo(record::getDatasource_name)
            .set(datasource_type).equalTo(record::getDatasource_type)
            .set(service_address).equalTo(record::getService_address)
            .set(properties).equalTo(record::getProperties)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modifier).equalTo(record::getModifier)
            .set(modify_at).equalTo(record::getModify_at)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T19:45:28.534+08:00", comments="Source Table: data_source")
    default int updateByPrimaryKeySelective(DataSource record) {
        return update(c ->
            c.set(datasource_name).equalToWhenPresent(record::getDatasource_name)
            .set(datasource_type).equalToWhenPresent(record::getDatasource_type)
            .set(service_address).equalToWhenPresent(record::getService_address)
            .set(properties).equalToWhenPresent(record::getProperties)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .where(id, isEqualTo(record::getId))
        );
    }
}