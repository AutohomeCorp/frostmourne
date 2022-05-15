package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertEventDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertEvent;
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
public interface AlertEventDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmId, alertType, inSilence, createAt, upgrade, eventMd5);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlertEvent> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertEventResult")
    Optional<AlertEvent> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertEventResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="alert_type", property="alertType", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_silence", property="inSilence", jdbcType=JdbcType.TINYINT),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="upgrade", property="upgrade", jdbcType=JdbcType.TINYINT),
        @Result(column="event_md5", property="eventMd5", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<AlertEvent> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alertEvent, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alertEvent, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(AlertEvent record) {
        return MyBatis3Utils.insert(this::insert, record, alertEvent, c ->
            c.map(alarmId).toProperty("alarmId")
            .map(alertType).toProperty("alertType")
            .map(inSilence).toProperty("inSilence")
            .map(createAt).toProperty("createAt")
            .map(upgrade).toProperty("upgrade")
            .map(eventMd5).toProperty("eventMd5")
        );
    }

    default int insertSelective(AlertEvent record) {
        return MyBatis3Utils.insert(this::insert, record, alertEvent, c ->
            c.map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(alertType).toPropertyWhenPresent("alertType", record::getAlertType)
            .map(inSilence).toPropertyWhenPresent("inSilence", record::getInSilence)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(upgrade).toPropertyWhenPresent("upgrade", record::getUpgrade)
            .map(eventMd5).toPropertyWhenPresent("eventMd5", record::getEventMd5)
        );
    }

    default Optional<AlertEvent> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alertEvent, completer);
    }

    default List<AlertEvent> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alertEvent, completer);
    }

    default List<AlertEvent> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alertEvent, completer);
    }

    default Optional<AlertEvent> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alertEvent, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(AlertEvent record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalTo(record::getAlarmId)
                .set(alertType).equalTo(record::getAlertType)
                .set(inSilence).equalTo(record::getInSilence)
                .set(createAt).equalTo(record::getCreateAt)
                .set(upgrade).equalTo(record::getUpgrade)
                .set(eventMd5).equalTo(record::getEventMd5);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlertEvent record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(alertType).equalToWhenPresent(record::getAlertType)
                .set(inSilence).equalToWhenPresent(record::getInSilence)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(upgrade).equalToWhenPresent(record::getUpgrade)
                .set(eventMd5).equalToWhenPresent(record::getEventMd5);
    }

    default int updateByPrimaryKey(AlertEvent record) {
        return update(c ->
            c.set(alarmId).equalTo(record::getAlarmId)
            .set(alertType).equalTo(record::getAlertType)
            .set(inSilence).equalTo(record::getInSilence)
            .set(createAt).equalTo(record::getCreateAt)
            .set(upgrade).equalTo(record::getUpgrade)
            .set(eventMd5).equalTo(record::getEventMd5)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(AlertEvent record) {
        return update(c ->
            c.set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(alertType).equalToWhenPresent(record::getAlertType)
            .set(inSilence).equalToWhenPresent(record::getInSilence)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(upgrade).equalToWhenPresent(record::getUpgrade)
            .set(eventMd5).equalToWhenPresent(record::getEventMd5)
            .where(id, isEqualTo(record::getId))
        );
    }
}