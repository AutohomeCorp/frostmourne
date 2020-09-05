package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServiceInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServiceInfo;
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
public interface ServiceInfoDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.2+08:00", comments="Source Table: service_info")
    BasicColumn[] selectList = BasicColumn.columnList(id, serviceName, remark, owner, creator, createAt, modifier, modifyAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.19+08:00", comments="Source Table: service_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.191+08:00", comments="Source Table: service_info")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.191+08:00", comments="Source Table: service_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<ServiceInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.194+08:00", comments="Source Table: service_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ServiceInfoResult")
    Optional<ServiceInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.195+08:00", comments="Source Table: service_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ServiceInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="service_name", property="serviceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ServiceInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.196+08:00", comments="Source Table: service_info")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.196+08:00", comments="Source Table: service_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, serviceInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.197+08:00", comments="Source Table: service_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, serviceInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.197+08:00", comments="Source Table: service_info")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.197+08:00", comments="Source Table: service_info")
    default int insert(ServiceInfo record) {
        return MyBatis3Utils.insert(this::insert, record, serviceInfo, c ->
            c.map(serviceName).toProperty("serviceName")
            .map(remark).toProperty("remark")
            .map(owner).toProperty("owner")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifier).toProperty("modifier")
            .map(modifyAt).toProperty("modifyAt")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.199+08:00", comments="Source Table: service_info")
    default int insertSelective(ServiceInfo record) {
        return MyBatis3Utils.insert(this::insert, record, serviceInfo, c ->
            c.map(serviceName).toPropertyWhenPresent("serviceName", record::getServiceName)
            .map(remark).toPropertyWhenPresent("remark", record::getRemark)
            .map(owner).toPropertyWhenPresent("owner", record::getOwner)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.201+08:00", comments="Source Table: service_info")
    default Optional<ServiceInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, serviceInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.201+08:00", comments="Source Table: service_info")
    default List<ServiceInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, serviceInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.202+08:00", comments="Source Table: service_info")
    default List<ServiceInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, serviceInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.202+08:00", comments="Source Table: service_info")
    default Optional<ServiceInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.203+08:00", comments="Source Table: service_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, serviceInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.203+08:00", comments="Source Table: service_info")
    static UpdateDSL<UpdateModel> updateAllColumns(ServiceInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(serviceName).equalTo(record::getServiceName)
                .set(remark).equalTo(record::getRemark)
                .set(owner).equalTo(record::getOwner)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifier).equalTo(record::getModifier)
                .set(modifyAt).equalTo(record::getModifyAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.204+08:00", comments="Source Table: service_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ServiceInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(serviceName).equalToWhenPresent(record::getServiceName)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(owner).equalToWhenPresent(record::getOwner)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.204+08:00", comments="Source Table: service_info")
    default int updateByPrimaryKey(ServiceInfo record) {
        return update(c ->
            c.set(serviceName).equalTo(record::getServiceName)
            .set(remark).equalTo(record::getRemark)
            .set(owner).equalTo(record::getOwner)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifier).equalTo(record::getModifier)
            .set(modifyAt).equalTo(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-28T22:27:22.205+08:00", comments="Source Table: service_info")
    default int updateByPrimaryKeySelective(ServiceInfo record) {
        return update(c ->
            c.set(serviceName).equalToWhenPresent(record::getServiceName)
            .set(remark).equalToWhenPresent(record::getRemark)
            .set(owner).equalToWhenPresent(record::getOwner)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}