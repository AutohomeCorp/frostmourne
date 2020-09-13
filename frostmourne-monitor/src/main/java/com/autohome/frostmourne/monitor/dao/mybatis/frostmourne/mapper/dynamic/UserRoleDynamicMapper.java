package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.UserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserRole;
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
public interface UserRoleDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    BasicColumn[] selectList = BasicColumn.columnList(id, account, role, creator, createAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UserRole> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserRoleResult")
    Optional<UserRole> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserRole> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default int insert(UserRole record) {
        return MyBatis3Utils.insert(this::insert, record, userRole, c ->
            c.map(account).toProperty("account")
            .map(role).toProperty("role")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default int insertSelective(UserRole record) {
        return MyBatis3Utils.insert(this::insert, record, userRole, c ->
            c.map(account).toPropertyWhenPresent("account", record::getAccount)
            .map(role).toPropertyWhenPresent("role", record::getRole)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default Optional<UserRole> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default List<UserRole> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default List<UserRole> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.015+08:00", comments="Source Table: user_role")
    default Optional<UserRole> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.016+08:00", comments="Source Table: user_role")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userRole, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.016+08:00", comments="Source Table: user_role")
    static UpdateDSL<UpdateModel> updateAllColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(account).equalTo(record::getAccount)
                .set(role).equalTo(record::getRole)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.016+08:00", comments="Source Table: user_role")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(account).equalToWhenPresent(record::getAccount)
                .set(role).equalToWhenPresent(record::getRole)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.016+08:00", comments="Source Table: user_role")
    default int updateByPrimaryKey(UserRole record) {
        return update(c ->
            c.set(account).equalTo(record::getAccount)
            .set(role).equalTo(record::getRole)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:33.016+08:00", comments="Source Table: user_role")
    default int updateByPrimaryKeySelective(UserRole record) {
        return update(c ->
            c.set(account).equalToWhenPresent(record::getAccount)
            .set(role).equalToWhenPresent(record::getRole)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}