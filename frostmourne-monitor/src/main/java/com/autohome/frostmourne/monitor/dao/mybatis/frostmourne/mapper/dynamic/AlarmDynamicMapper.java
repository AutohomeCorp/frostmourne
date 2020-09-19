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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.962+08:00", comments="Source Table: alarm")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmName, alarmType, description, ownerKey, status, executeResult, executeAt, jobId, cron, creator, createAt, modifier, modifyAt, teamName, serviceId, riskLevel);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.949+08:00", comments="Source Table: alarm")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.95+08:00", comments="Source Table: alarm")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.951+08:00", comments="Source Table: alarm")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Alarm> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.954+08:00", comments="Source Table: alarm")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlarmResult")
    Optional<Alarm> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.955+08:00", comments="Source Table: alarm")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlarmResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_name", property="alarmName", jdbcType=JdbcType.VARCHAR),
        @Result(column="alarm_type", property="alarmType", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner_key", property="ownerKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="execute_result", property="executeResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="execute_at", property="executeAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="job_id", property="jobId", jdbcType=JdbcType.BIGINT),
        @Result(column="cron", property="cron", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_at", property="modifyAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="team_name", property="teamName", jdbcType=JdbcType.VARCHAR),
        @Result(column="service_id", property="serviceId", jdbcType=JdbcType.BIGINT),
        @Result(column="risk_level", property="riskLevel", jdbcType=JdbcType.VARCHAR)
    })
    List<Alarm> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.956+08:00", comments="Source Table: alarm")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.957+08:00", comments="Source Table: alarm")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.957+08:00", comments="Source Table: alarm")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.958+08:00", comments="Source Table: alarm")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.959+08:00", comments="Source Table: alarm")
    default int insert(Alarm record) {
        return MyBatis3Utils.insert(this::insert, record, alarm, c ->
            c.map(alarmName).toProperty("alarmName")
            .map(alarmType).toProperty("alarmType")
            .map(description).toProperty("description")
            .map(ownerKey).toProperty("ownerKey")
            .map(status).toProperty("status")
            .map(executeResult).toProperty("executeResult")
            .map(executeAt).toProperty("executeAt")
            .map(jobId).toProperty("jobId")
            .map(cron).toProperty("cron")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(modifier).toProperty("modifier")
            .map(modifyAt).toProperty("modifyAt")
            .map(teamName).toProperty("teamName")
            .map(serviceId).toProperty("serviceId")
            .map(riskLevel).toProperty("riskLevel")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.961+08:00", comments="Source Table: alarm")
    default int insertSelective(Alarm record) {
        return MyBatis3Utils.insert(this::insert, record, alarm, c ->
            c.map(alarmName).toPropertyWhenPresent("alarmName", record::getAlarmName)
            .map(alarmType).toPropertyWhenPresent("alarmType", record::getAlarmType)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
            .map(ownerKey).toPropertyWhenPresent("ownerKey", record::getOwnerKey)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(executeResult).toPropertyWhenPresent("executeResult", record::getExecuteResult)
            .map(executeAt).toPropertyWhenPresent("executeAt", record::getExecuteAt)
            .map(jobId).toPropertyWhenPresent("jobId", record::getJobId)
            .map(cron).toPropertyWhenPresent("cron", record::getCron)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
            .map(modifyAt).toPropertyWhenPresent("modifyAt", record::getModifyAt)
            .map(teamName).toPropertyWhenPresent("teamName", record::getTeamName)
            .map(serviceId).toPropertyWhenPresent("serviceId", record::getServiceId)
            .map(riskLevel).toPropertyWhenPresent("riskLevel", record::getRiskLevel)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.963+08:00", comments="Source Table: alarm")
    default Optional<Alarm> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.963+08:00", comments="Source Table: alarm")
    default List<Alarm> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.964+08:00", comments="Source Table: alarm")
    default List<Alarm> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.964+08:00", comments="Source Table: alarm")
    default Optional<Alarm> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.964+08:00", comments="Source Table: alarm")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alarm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.965+08:00", comments="Source Table: alarm")
    static UpdateDSL<UpdateModel> updateAllColumns(Alarm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmName).equalTo(record::getAlarmName)
                .set(alarmType).equalTo(record::getAlarmType)
                .set(description).equalTo(record::getDescription)
                .set(ownerKey).equalTo(record::getOwnerKey)
                .set(status).equalTo(record::getStatus)
                .set(executeResult).equalTo(record::getExecuteResult)
                .set(executeAt).equalTo(record::getExecuteAt)
                .set(jobId).equalTo(record::getJobId)
                .set(cron).equalTo(record::getCron)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(modifier).equalTo(record::getModifier)
                .set(modifyAt).equalTo(record::getModifyAt)
                .set(teamName).equalTo(record::getTeamName)
                .set(serviceId).equalTo(record::getServiceId)
                .set(riskLevel).equalTo(record::getRiskLevel);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.965+08:00", comments="Source Table: alarm")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Alarm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmName).equalToWhenPresent(record::getAlarmName)
                .set(alarmType).equalToWhenPresent(record::getAlarmType)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(ownerKey).equalToWhenPresent(record::getOwnerKey)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(executeResult).equalToWhenPresent(record::getExecuteResult)
                .set(executeAt).equalToWhenPresent(record::getExecuteAt)
                .set(jobId).equalToWhenPresent(record::getJobId)
                .set(cron).equalToWhenPresent(record::getCron)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modifyAt).equalToWhenPresent(record::getModifyAt)
                .set(teamName).equalToWhenPresent(record::getTeamName)
                .set(serviceId).equalToWhenPresent(record::getServiceId)
                .set(riskLevel).equalToWhenPresent(record::getRiskLevel);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.966+08:00", comments="Source Table: alarm")
    default int updateByPrimaryKey(Alarm record) {
        return update(c ->
            c.set(alarmName).equalTo(record::getAlarmName)
            .set(alarmType).equalTo(record::getAlarmType)
            .set(description).equalTo(record::getDescription)
            .set(ownerKey).equalTo(record::getOwnerKey)
            .set(status).equalTo(record::getStatus)
            .set(executeResult).equalTo(record::getExecuteResult)
            .set(executeAt).equalTo(record::getExecuteAt)
            .set(jobId).equalTo(record::getJobId)
            .set(cron).equalTo(record::getCron)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(modifier).equalTo(record::getModifier)
            .set(modifyAt).equalTo(record::getModifyAt)
            .set(teamName).equalTo(record::getTeamName)
            .set(serviceId).equalTo(record::getServiceId)
            .set(riskLevel).equalTo(record::getRiskLevel)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.967+08:00", comments="Source Table: alarm")
    default int updateByPrimaryKeySelective(Alarm record) {
        return update(c ->
            c.set(alarmName).equalToWhenPresent(record::getAlarmName)
            .set(alarmType).equalToWhenPresent(record::getAlarmType)
            .set(description).equalToWhenPresent(record::getDescription)
            .set(ownerKey).equalToWhenPresent(record::getOwnerKey)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(executeResult).equalToWhenPresent(record::getExecuteResult)
            .set(executeAt).equalToWhenPresent(record::getExecuteAt)
            .set(jobId).equalToWhenPresent(record::getJobId)
            .set(cron).equalToWhenPresent(record::getCron)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .set(modifyAt).equalToWhenPresent(record::getModifyAt)
            .set(teamName).equalToWhenPresent(record::getTeamName)
            .set(serviceId).equalToWhenPresent(record::getServiceId)
            .set(riskLevel).equalToWhenPresent(record::getRiskLevel)
            .where(id, isEqualTo(record::getId))
        );
    }
}