package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.TeamInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.TeamInfo;
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
public interface TeamInfoDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, teamName, fullName, departmentId, creator, createAt, modifyAt, modifier);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<TeamInfo> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TeamInfoResult")
    Optional<TeamInfo> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TeamInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="team_name", property="teamName", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="fullName", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="departmentId", jdbcType=JdbcType.BIGINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    List<TeamInfo> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, teamInfo, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, teamInfo, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(TeamInfo record) {
        return MyBatis3Utils.insert(this::insert, record, teamInfo, c ->
            c.map(teamName).toProperty("teamName")
            .map(fullName).toProperty("fullName")
            .map(departmentId).toProperty("departmentId")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifyAt).toProperty("modifyAt")
            .map(modifier).toProperty("modifier")
        );
    }

    default int insertSelective(TeamInfo record) {
        return MyBatis3Utils.insert(this::insert, record, teamInfo, c ->
            c.map(teamName).toPropertyWhenPresent("teamName", record::getTeamName)
            .map(fullName).toPropertyWhenPresent("fullName", record::getFullName)
            .map(departmentId).toPropertyWhenPresent("departmentId", record::getDepartmentId)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
        );
    }

    default Optional<TeamInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, teamInfo, completer);
    }

    default List<TeamInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, teamInfo, completer);
    }

    default List<TeamInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, teamInfo, completer);
    }

    default Optional<TeamInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, teamInfo, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(TeamInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(teamName).equalTo(record::getTeamName)
                .set(fullName).equalTo(record::getFullName)
                .set(departmentId).equalTo(record::getDepartmentId)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifyAt).equalTo(record::getModifyAt)
                .set(modifier).equalTo(record::getModifier);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(TeamInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(teamName).equalToWhenPresent(record::getTeamName)
                .set(fullName).equalToWhenPresent(record::getFullName)
                .set(departmentId).equalToWhenPresent(record::getDepartmentId)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt)
                .set(modifier).equalToWhenPresent(record::getModifier);
    }

    default int updateByPrimaryKey(TeamInfo record) {
        return update(c ->
            c.set(teamName).equalTo(record::getTeamName)
            .set(fullName).equalTo(record::getFullName)
            .set(departmentId).equalTo(record::getDepartmentId)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifyAt).equalTo(record::getModifyAt)
            .set(modifier).equalTo(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(TeamInfo record) {
        return update(c ->
            c.set(teamName).equalToWhenPresent(record::getTeamName)
            .set(fullName).equalToWhenPresent(record::getFullName)
            .set(departmentId).equalToWhenPresent(record::getDepartmentId)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }
}