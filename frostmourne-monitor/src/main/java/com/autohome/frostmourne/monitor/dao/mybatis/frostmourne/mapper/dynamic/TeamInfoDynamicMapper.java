package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.TeamInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
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
public interface TeamInfoDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    BasicColumn[] selectList = BasicColumn.columnList(id, team_name, full_name, department_id, creator, create_at, modify_at, modifier);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TeamInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TeamInfo> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TeamInfoResult")
    Optional<TeamInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TeamInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="team_name", property="team_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="full_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="department_id", property="department_id", jdbcType=JdbcType.BIGINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    List<TeamInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, teamInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, teamInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    default int insert(TeamInfo record) {
        return MyBatis3Utils.insert(this::insert, record, teamInfo, c ->
            c.map(id).toProperty("id")
            .map(team_name).toProperty("team_name")
            .map(full_name).toProperty("full_name")
            .map(department_id).toProperty("department_id")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modify_at).toProperty("modify_at")
            .map(modifier).toProperty("modifier")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.811+08:00", comments="Source Table: team_info")
    default int insertMultiple(Collection<TeamInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, teamInfo, c ->
            c.map(id).toProperty("id")
            .map(team_name).toProperty("team_name")
            .map(full_name).toProperty("full_name")
            .map(department_id).toProperty("department_id")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modify_at).toProperty("modify_at")
            .map(modifier).toProperty("modifier")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default int insertSelective(TeamInfo record) {
        return MyBatis3Utils.insert(this::insert, record, teamInfo, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(team_name).toPropertyWhenPresent("team_name", record::getTeam_name)
            .map(full_name).toPropertyWhenPresent("full_name", record::getFull_name)
            .map(department_id).toPropertyWhenPresent("department_id", record::getDepartment_id)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default Optional<TeamInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, teamInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default List<TeamInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, teamInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default List<TeamInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, teamInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default Optional<TeamInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, teamInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    static UpdateDSL<UpdateModel> updateAllColumns(TeamInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(team_name).equalTo(record::getTeam_name)
                .set(full_name).equalTo(record::getFull_name)
                .set(department_id).equalTo(record::getDepartment_id)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modify_at).equalTo(record::getModify_at)
                .set(modifier).equalTo(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TeamInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(team_name).equalToWhenPresent(record::getTeam_name)
                .set(full_name).equalToWhenPresent(record::getFull_name)
                .set(department_id).equalToWhenPresent(record::getDepartment_id)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modify_at).equalToWhenPresent(record::getModify_at)
                .set(modifier).equalToWhenPresent(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default int updateByPrimaryKey(TeamInfo record) {
        return update(c ->
            c.set(team_name).equalTo(record::getTeam_name)
            .set(full_name).equalTo(record::getFull_name)
            .set(department_id).equalTo(record::getDepartment_id)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modify_at).equalTo(record::getModify_at)
            .set(modifier).equalTo(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.812+08:00", comments="Source Table: team_info")
    default int updateByPrimaryKeySelective(TeamInfo record) {
        return update(c ->
            c.set(team_name).equalToWhenPresent(record::getTeam_name)
            .set(full_name).equalToWhenPresent(record::getFull_name)
            .set(department_id).equalToWhenPresent(record::getDepartment_id)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }
}