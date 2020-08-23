package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
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
public interface AlarmDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.025+08:00", comments="Source Table: alarm")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarm_name, alarm_type, description, owner_key, status, execute_result, execute_at, job_id, cron, creator, create_at, modifier, modify_at, team_name, server_id);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.015+08:00", comments="Source Table: alarm")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.016+08:00", comments="Source Table: alarm")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.016+08:00", comments="Source Table: alarm")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Alarm> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.02+08:00", comments="Source Table: alarm")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlarmResult")
    Optional<Alarm> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.02+08:00", comments="Source Table: alarm")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlarmResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_name", property="alarm_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="alarm_type", property="alarm_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_key", property="owner_key", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="execute_result", property="execute_result", jdbcType=JdbcType.VARCHAR),
        @Result(column="execute_at", property="execute_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="job_id", property="job_id", jdbcType=JdbcType.BIGINT),
        @Result(column="cron", property="cron", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="team_name", property="team_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="server_id", property="server_id", jdbcType=JdbcType.BIGINT)
    })
    List<Alarm> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.021+08:00", comments="Source Table: alarm")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.022+08:00", comments="Source Table: alarm")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.022+08:00", comments="Source Table: alarm")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.022+08:00", comments="Source Table: alarm")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.023+08:00", comments="Source Table: alarm")
    default int insert(Alarm record) {
        return MyBatis3Utils.insert(this::insert, record, alarm, c ->
            c.map(alarm_name).toProperty("alarm_name")
            .map(alarm_type).toProperty("alarm_type")
            .map(description).toProperty("description")
            .map(owner_key).toProperty("owner_key")
            .map(status).toProperty("status")
            .map(execute_result).toProperty("execute_result")
            .map(execute_at).toProperty("execute_at")
            .map(job_id).toProperty("job_id")
            .map(cron).toProperty("cron")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modifier).toProperty("modifier")
            .map(modify_at).toProperty("modify_at")
            .map(team_name).toProperty("team_name")
            .map(server_id).toProperty("server_id")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.024+08:00", comments="Source Table: alarm")
    default int insertSelective(Alarm record) {
        return MyBatis3Utils.insert(this::insert, record, alarm, c ->
            c.map(alarm_name).toPropertyWhenPresent("alarm_name", record::getAlarm_name)
            .map(alarm_type).toPropertyWhenPresent("alarm_type", record::getAlarm_type)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
            .map(owner_key).toPropertyWhenPresent("owner_key", record::getOwner_key)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(execute_result).toPropertyWhenPresent("execute_result", record::getExecute_result)
            .map(execute_at).toPropertyWhenPresent("execute_at", record::getExecute_at)
            .map(job_id).toPropertyWhenPresent("job_id", record::getJob_id)
            .map(cron).toPropertyWhenPresent("cron", record::getCron)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
            .map(team_name).toPropertyWhenPresent("team_name", record::getTeam_name)
            .map(server_id).toPropertyWhenPresent("server_id", record::getServer_id)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.026+08:00", comments="Source Table: alarm")
    default Optional<Alarm> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.027+08:00", comments="Source Table: alarm")
    default List<Alarm> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.027+08:00", comments="Source Table: alarm")
    default List<Alarm> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.027+08:00", comments="Source Table: alarm")
    default Optional<Alarm> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.028+08:00", comments="Source Table: alarm")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.028+08:00", comments="Source Table: alarm")
    static UpdateDSL<UpdateModel> updateAllColumns(Alarm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_name).equalTo(record::getAlarm_name)
                .set(alarm_type).equalTo(record::getAlarm_type)
                .set(description).equalTo(record::getDescription)
                .set(owner_key).equalTo(record::getOwner_key)
                .set(status).equalTo(record::getStatus)
                .set(execute_result).equalTo(record::getExecute_result)
                .set(execute_at).equalTo(record::getExecute_at)
                .set(job_id).equalTo(record::getJob_id)
                .set(cron).equalTo(record::getCron)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modifier).equalTo(record::getModifier)
                .set(modify_at).equalTo(record::getModify_at)
                .set(team_name).equalTo(record::getTeam_name)
                .set(server_id).equalTo(record::getServer_id);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.029+08:00", comments="Source Table: alarm")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Alarm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_name).equalToWhenPresent(record::getAlarm_name)
                .set(alarm_type).equalToWhenPresent(record::getAlarm_type)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(owner_key).equalToWhenPresent(record::getOwner_key)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(execute_result).equalToWhenPresent(record::getExecute_result)
                .set(execute_at).equalToWhenPresent(record::getExecute_at)
                .set(job_id).equalToWhenPresent(record::getJob_id)
                .set(cron).equalToWhenPresent(record::getCron)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modify_at).equalToWhenPresent(record::getModify_at)
                .set(team_name).equalToWhenPresent(record::getTeam_name)
                .set(server_id).equalToWhenPresent(record::getServer_id);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.03+08:00", comments="Source Table: alarm")
    default int updateByPrimaryKey(Alarm record) {
        return update(c ->
            c.set(alarm_name).equalTo(record::getAlarm_name)
            .set(alarm_type).equalTo(record::getAlarm_type)
            .set(description).equalTo(record::getDescription)
            .set(owner_key).equalTo(record::getOwner_key)
            .set(status).equalTo(record::getStatus)
            .set(execute_result).equalTo(record::getExecute_result)
            .set(execute_at).equalTo(record::getExecute_at)
            .set(job_id).equalTo(record::getJob_id)
            .set(cron).equalTo(record::getCron)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modifier).equalTo(record::getModifier)
            .set(modify_at).equalTo(record::getModify_at)
            .set(team_name).equalTo(record::getTeam_name)
            .set(server_id).equalTo(record::getServer_id)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-08-23T14:21:43.03+08:00", comments="Source Table: alarm")
    default int updateByPrimaryKeySelective(Alarm record) {
        return update(c ->
            c.set(alarm_name).equalToWhenPresent(record::getAlarm_name)
            .set(alarm_type).equalToWhenPresent(record::getAlarm_type)
            .set(description).equalToWhenPresent(record::getDescription)
            .set(owner_key).equalToWhenPresent(record::getOwner_key)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(execute_result).equalToWhenPresent(record::getExecute_result)
            .set(execute_at).equalToWhenPresent(record::getExecute_at)
            .set(job_id).equalToWhenPresent(record::getJob_id)
            .set(cron).equalToWhenPresent(record::getCron)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .set(team_name).equalToWhenPresent(record::getTeam_name)
            .set(server_id).equalToWhenPresent(record::getServer_id)
            .where(id, isEqualTo(record::getId))
        );
    }
}