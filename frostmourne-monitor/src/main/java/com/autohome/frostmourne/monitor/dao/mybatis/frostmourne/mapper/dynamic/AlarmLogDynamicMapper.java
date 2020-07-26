package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlarmLog;
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
public interface AlarmLogDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.109+08:00", comments="Source Table: alarm_log")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarm_id, exe_start, exe_end, cost, execute_result, verify_result, create_at, message);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.097+08:00", comments="Source Table: alarm_log")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.099+08:00", comments="Source Table: alarm_log")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.099+08:00", comments="Source Table: alarm_log")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlarmLog> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.103+08:00", comments="Source Table: alarm_log")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlarmLogResult")
    Optional<AlarmLog> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.104+08:00", comments="Source Table: alarm_log")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlarmLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="exe_start", property="exe_start", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="exe_end", property="exe_end", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cost", property="cost", jdbcType=JdbcType.INTEGER),
        @Result(column="execute_result", property="execute_result", jdbcType=JdbcType.VARCHAR),
        @Result(column="verify_result", property="verify_result", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="message", property="message", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<AlarmLog> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.105+08:00", comments="Source Table: alarm_log")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.105+08:00", comments="Source Table: alarm_log")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alarmLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.106+08:00", comments="Source Table: alarm_log")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alarmLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.106+08:00", comments="Source Table: alarm_log")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.106+08:00", comments="Source Table: alarm_log")
    default int insert(AlarmLog record) {
        return MyBatis3Utils.insert(this::insert, record, alarmLog, c ->
            c.map(alarm_id).toProperty("alarm_id")
            .map(exe_start).toProperty("exe_start")
            .map(exe_end).toProperty("exe_end")
            .map(cost).toProperty("cost")
            .map(execute_result).toProperty("execute_result")
            .map(verify_result).toProperty("verify_result")
            .map(create_at).toProperty("create_at")
            .map(message).toProperty("message")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.108+08:00", comments="Source Table: alarm_log")
    default int insertSelective(AlarmLog record) {
        return MyBatis3Utils.insert(this::insert, record, alarmLog, c ->
            c.map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(exe_start).toPropertyWhenPresent("exe_start", record::getExe_start)
            .map(exe_end).toPropertyWhenPresent("exe_end", record::getExe_end)
            .map(cost).toPropertyWhenPresent("cost", record::getCost)
            .map(execute_result).toPropertyWhenPresent("execute_result", record::getExecute_result)
            .map(verify_result).toPropertyWhenPresent("verify_result", record::getVerify_result)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(message).toPropertyWhenPresent("message", record::getMessage)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.109+08:00", comments="Source Table: alarm_log")
    default Optional<AlarmLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alarmLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.11+08:00", comments="Source Table: alarm_log")
    default List<AlarmLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alarmLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.11+08:00", comments="Source Table: alarm_log")
    default List<AlarmLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alarmLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.111+08:00", comments="Source Table: alarm_log")
    default Optional<AlarmLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.111+08:00", comments="Source Table: alarm_log")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alarmLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.111+08:00", comments="Source Table: alarm_log")
    static UpdateDSL<UpdateModel> updateAllColumns(AlarmLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_id).equalTo(record::getAlarm_id)
                .set(exe_start).equalTo(record::getExe_start)
                .set(exe_end).equalTo(record::getExe_end)
                .set(cost).equalTo(record::getCost)
                .set(execute_result).equalTo(record::getExecute_result)
                .set(verify_result).equalTo(record::getVerify_result)
                .set(create_at).equalTo(record::getCreate_at)
                .set(message).equalTo(record::getMessage);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.112+08:00", comments="Source Table: alarm_log")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlarmLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(exe_start).equalToWhenPresent(record::getExe_start)
                .set(exe_end).equalToWhenPresent(record::getExe_end)
                .set(cost).equalToWhenPresent(record::getCost)
                .set(execute_result).equalToWhenPresent(record::getExecute_result)
                .set(verify_result).equalToWhenPresent(record::getVerify_result)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(message).equalToWhenPresent(record::getMessage);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.112+08:00", comments="Source Table: alarm_log")
    default int updateByPrimaryKey(AlarmLog record) {
        return update(c ->
            c.set(alarm_id).equalTo(record::getAlarm_id)
            .set(exe_start).equalTo(record::getExe_start)
            .set(exe_end).equalTo(record::getExe_end)
            .set(cost).equalTo(record::getCost)
            .set(execute_result).equalTo(record::getExecute_result)
            .set(verify_result).equalTo(record::getVerify_result)
            .set(create_at).equalTo(record::getCreate_at)
            .set(message).equalTo(record::getMessage)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-25T22:09:53.113+08:00", comments="Source Table: alarm_log")
    default int updateByPrimaryKeySelective(AlarmLog record) {
        return update(c ->
            c.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(exe_start).equalToWhenPresent(record::getExe_start)
            .set(exe_end).equalToWhenPresent(record::getExe_end)
            .set(cost).equalToWhenPresent(record::getCost)
            .set(execute_result).equalToWhenPresent(record::getExecute_result)
            .set(verify_result).equalToWhenPresent(record::getVerify_result)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(message).equalToWhenPresent(record::getMessage)
            .where(id, isEqualTo(record::getId))
        );
    }
}