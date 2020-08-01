package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DepartmentInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DepartmentInfo;
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
public interface DepartmentInfoDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    BasicColumn[] selectList = BasicColumn.columnList(id, department_name, full_name, creator, create_at, modify_at, modifier);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.347+08:00", comments="Source Table: department_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.347+08:00", comments="Source Table: department_info")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.347+08:00", comments="Source Table: department_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<DepartmentInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DepartmentInfoResult")
    Optional<DepartmentInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DepartmentInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="department_name", property="department_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="full_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    List<DepartmentInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, departmentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, departmentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default int insert(DepartmentInfo record) {
        return MyBatis3Utils.insert(this::insert, record, departmentInfo, c ->
            c.map(department_name).toProperty("department_name")
            .map(full_name).toProperty("full_name")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modify_at).toProperty("modify_at")
            .map(modifier).toProperty("modifier")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default int insertSelective(DepartmentInfo record) {
        return MyBatis3Utils.insert(this::insert, record, departmentInfo, c ->
            c.map(department_name).toPropertyWhenPresent("department_name", record::getDepartment_name)
            .map(full_name).toPropertyWhenPresent("full_name", record::getFull_name)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default Optional<DepartmentInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, departmentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default List<DepartmentInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, departmentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.348+08:00", comments="Source Table: department_info")
    default List<DepartmentInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, departmentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.349+08:00", comments="Source Table: department_info")
    default Optional<DepartmentInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.349+08:00", comments="Source Table: department_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, departmentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.349+08:00", comments="Source Table: department_info")
    static UpdateDSL<UpdateModel> updateAllColumns(DepartmentInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(department_name).equalTo(record::getDepartment_name)
                .set(full_name).equalTo(record::getFull_name)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modify_at).equalTo(record::getModify_at)
                .set(modifier).equalTo(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.349+08:00", comments="Source Table: department_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DepartmentInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(department_name).equalToWhenPresent(record::getDepartment_name)
                .set(full_name).equalToWhenPresent(record::getFull_name)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modify_at).equalToWhenPresent(record::getModify_at)
                .set(modifier).equalToWhenPresent(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.349+08:00", comments="Source Table: department_info")
    default int updateByPrimaryKey(DepartmentInfo record) {
        return update(c ->
            c.set(department_name).equalTo(record::getDepartment_name)
            .set(full_name).equalTo(record::getFull_name)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modify_at).equalTo(record::getModify_at)
            .set(modifier).equalTo(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.349+08:00", comments="Source Table: department_info")
    default int updateByPrimaryKeySelective(DepartmentInfo record) {
        return update(c ->
            c.set(department_name).equalToWhenPresent(record::getDepartment_name)
            .set(full_name).equalToWhenPresent(record::getFull_name)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }
}