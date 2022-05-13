package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlarmLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlarmLog;
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
public interface AlarmLogDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmId, exeStart, exeEnd, cost, executeResult, verifyResult, createAt, alert, message);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlarmLog> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlarmLogResult")
    Optional<AlarmLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlarmLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="exe_start", property="exeStart", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="exe_end", property="exeEnd", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="cost", property="cost", jdbcType=JdbcType.INTEGER),
        @Result(column="execute_result", property="executeResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="verify_result", property="verifyResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="alert", property="alert", jdbcType=JdbcType.TINYINT),
        @Result(column="message", property="message", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<AlarmLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alarmLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alarmLog, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(AlarmLog record) {
        return MyBatis3Utils.insert(this::insert, record, alarmLog, c ->
            c.map(alarmId).toProperty("alarmId")
            .map(exeStart).toProperty("exeStart")
            .map(exeEnd).toProperty("exeEnd")
            .map(cost).toProperty("cost")
            .map(executeResult).toProperty("executeResult")
            .map(verifyResult).toProperty("verifyResult")
            .map(createAt).toProperty("createAt")
            .map(alert).toProperty("alert")
            .map(message).toProperty("message")
        );
    }

    default int insertSelective(AlarmLog record) {
        return MyBatis3Utils.insert(this::insert, record, alarmLog, c ->
            c.map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(exeStart).toPropertyWhenPresent("exeStart", record::getExeStart)
            .map(exeEnd).toPropertyWhenPresent("exeEnd", record::getExeEnd)
            .map(cost).toPropertyWhenPresent("cost", record::getCost)
            .map(executeResult).toPropertyWhenPresent("executeResult", record::getExecuteResult)
            .map(verifyResult).toPropertyWhenPresent("verifyResult", record::getVerifyResult)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(alert).toPropertyWhenPresent("alert", record::getAlert)
            .map(message).toPropertyWhenPresent("message", record::getMessage)
        );
    }

    default Optional<AlarmLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alarmLog, completer);
    }

    default List<AlarmLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alarmLog, completer);
    }

    default List<AlarmLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alarmLog, completer);
    }

    default Optional<AlarmLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alarmLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(AlarmLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalTo(record::getAlarmId)
                .set(exeStart).equalTo(record::getExeStart)
                .set(exeEnd).equalTo(record::getExeEnd)
                .set(cost).equalTo(record::getCost)
                .set(executeResult).equalTo(record::getExecuteResult)
                .set(verifyResult).equalTo(record::getVerifyResult)
                .set(createAt).equalTo(record::getCreateAt)
                .set(alert).equalTo(record::getAlert)
                .set(message).equalTo(record::getMessage);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlarmLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(exeStart).equalToWhenPresent(record::getExeStart)
                .set(exeEnd).equalToWhenPresent(record::getExeEnd)
                .set(cost).equalToWhenPresent(record::getCost)
                .set(executeResult).equalToWhenPresent(record::getExecuteResult)
                .set(verifyResult).equalToWhenPresent(record::getVerifyResult)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(alert).equalToWhenPresent(record::getAlert)
                .set(message).equalToWhenPresent(record::getMessage);
    }

    default int updateByPrimaryKey(AlarmLog record) {
        return update(c ->
            c.set(alarmId).equalTo(record::getAlarmId)
            .set(exeStart).equalTo(record::getExeStart)
            .set(exeEnd).equalTo(record::getExeEnd)
            .set(cost).equalTo(record::getCost)
            .set(executeResult).equalTo(record::getExecuteResult)
            .set(verifyResult).equalTo(record::getVerifyResult)
            .set(createAt).equalTo(record::getCreateAt)
            .set(alert).equalTo(record::getAlert)
            .set(message).equalTo(record::getMessage)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(AlarmLog record) {
        return update(c ->
            c.set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(exeStart).equalToWhenPresent(record::getExeStart)
            .set(exeEnd).equalToWhenPresent(record::getExeEnd)
            .set(cost).equalToWhenPresent(record::getCost)
            .set(executeResult).equalToWhenPresent(record::getExecuteResult)
            .set(verifyResult).equalToWhenPresent(record::getVerifyResult)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(alert).equalToWhenPresent(record::getAlert)
            .set(message).equalToWhenPresent(record::getMessage)
            .where(id, isEqualTo(record::getId))
        );
    }
}