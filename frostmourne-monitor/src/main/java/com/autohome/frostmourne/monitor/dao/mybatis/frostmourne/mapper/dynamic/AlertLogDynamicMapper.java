package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertLog;
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
public interface AlertLogDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmId, executeId, way, recipient, inSilence, sendStatus, alertType, createAt, content);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlertLog> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertLogResult")
    Optional<AlertLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="execute_id", property="executeId", jdbcType=JdbcType.BIGINT),
        @Result(column="way", property="way", jdbcType=JdbcType.VARCHAR),
        @Result(column="recipient", property="recipient", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_silence", property="inSilence", jdbcType=JdbcType.VARCHAR),
        @Result(column="send_status", property="sendStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="alert_type", property="alertType", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<AlertLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alertLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alertLog, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(AlertLog record) {
        return MyBatis3Utils.insert(this::insert, record, alertLog, c ->
            c.map(alarmId).toProperty("alarmId")
            .map(executeId).toProperty("executeId")
            .map(way).toProperty("way")
            .map(recipient).toProperty("recipient")
            .map(inSilence).toProperty("inSilence")
            .map(sendStatus).toProperty("sendStatus")
            .map(alertType).toProperty("alertType")
            .map(createAt).toProperty("createAt")
            .map(content).toProperty("content")
        );
    }

    default int insertSelective(AlertLog record) {
        return MyBatis3Utils.insert(this::insert, record, alertLog, c ->
            c.map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(executeId).toPropertyWhenPresent("executeId", record::getExecuteId)
            .map(way).toPropertyWhenPresent("way", record::getWay)
            .map(recipient).toPropertyWhenPresent("recipient", record::getRecipient)
            .map(inSilence).toPropertyWhenPresent("inSilence", record::getInSilence)
            .map(sendStatus).toPropertyWhenPresent("sendStatus", record::getSendStatus)
            .map(alertType).toPropertyWhenPresent("alertType", record::getAlertType)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    default Optional<AlertLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alertLog, completer);
    }

    default List<AlertLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alertLog, completer);
    }

    default List<AlertLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alertLog, completer);
    }

    default Optional<AlertLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alertLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(AlertLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalTo(record::getAlarmId)
                .set(executeId).equalTo(record::getExecuteId)
                .set(way).equalTo(record::getWay)
                .set(recipient).equalTo(record::getRecipient)
                .set(inSilence).equalTo(record::getInSilence)
                .set(sendStatus).equalTo(record::getSendStatus)
                .set(alertType).equalTo(record::getAlertType)
                .set(createAt).equalTo(record::getCreateAt)
                .set(content).equalTo(record::getContent);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlertLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(executeId).equalToWhenPresent(record::getExecuteId)
                .set(way).equalToWhenPresent(record::getWay)
                .set(recipient).equalToWhenPresent(record::getRecipient)
                .set(inSilence).equalToWhenPresent(record::getInSilence)
                .set(sendStatus).equalToWhenPresent(record::getSendStatus)
                .set(alertType).equalToWhenPresent(record::getAlertType)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(content).equalToWhenPresent(record::getContent);
    }

    default int updateByPrimaryKey(AlertLog record) {
        return update(c ->
            c.set(alarmId).equalTo(record::getAlarmId)
            .set(executeId).equalTo(record::getExecuteId)
            .set(way).equalTo(record::getWay)
            .set(recipient).equalTo(record::getRecipient)
            .set(inSilence).equalTo(record::getInSilence)
            .set(sendStatus).equalTo(record::getSendStatus)
            .set(alertType).equalTo(record::getAlertType)
            .set(createAt).equalTo(record::getCreateAt)
            .set(content).equalTo(record::getContent)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(AlertLog record) {
        return update(c ->
            c.set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(executeId).equalToWhenPresent(record::getExecuteId)
            .set(way).equalToWhenPresent(record::getWay)
            .set(recipient).equalToWhenPresent(record::getRecipient)
            .set(inSilence).equalToWhenPresent(record::getInSilence)
            .set(sendStatus).equalToWhenPresent(record::getSendStatus)
            .set(alertType).equalToWhenPresent(record::getAlertType)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(content).equalToWhenPresent(record::getContent)
            .where(id, isEqualTo(record::getId))
        );
    }
}