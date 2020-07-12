package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertLog;
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
public interface AlertLogDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarm_id, execute_id, way, recipient, in_silence, send_status, create_at, alert_type, content);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<AlertLog> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<AlertLog> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertLogResult")
    Optional<AlertLog> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="execute_id", property="execute_id", jdbcType=JdbcType.BIGINT),
        @Result(column="way", property="way", jdbcType=JdbcType.VARCHAR),
        @Result(column="recipient", property="recipient", jdbcType=JdbcType.VARCHAR),
        @Result(column="in_silence", property="in_silence", jdbcType=JdbcType.VARCHAR),
        @Result(column="send_status", property="send_status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="alert_type", property="alert_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<AlertLog> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alertLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alertLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    default int insert(AlertLog record) {
        return MyBatis3Utils.insert(this::insert, record, alertLog, c ->
            c.map(id).toProperty("id")
            .map(alarm_id).toProperty("alarm_id")
            .map(execute_id).toProperty("execute_id")
            .map(way).toProperty("way")
            .map(recipient).toProperty("recipient")
            .map(in_silence).toProperty("in_silence")
            .map(send_status).toProperty("send_status")
            .map(create_at).toProperty("create_at")
            .map(alert_type).toProperty("alert_type")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    default int insertMultiple(Collection<AlertLog> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, alertLog, c ->
            c.map(id).toProperty("id")
            .map(alarm_id).toProperty("alarm_id")
            .map(execute_id).toProperty("execute_id")
            .map(way).toProperty("way")
            .map(recipient).toProperty("recipient")
            .map(in_silence).toProperty("in_silence")
            .map(send_status).toProperty("send_status")
            .map(create_at).toProperty("create_at")
            .map(alert_type).toProperty("alert_type")
            .map(content).toProperty("content")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.788+08:00", comments="Source Table: alert_log")
    default int insertSelective(AlertLog record) {
        return MyBatis3Utils.insert(this::insert, record, alertLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(execute_id).toPropertyWhenPresent("execute_id", record::getExecute_id)
            .map(way).toPropertyWhenPresent("way", record::getWay)
            .map(recipient).toPropertyWhenPresent("recipient", record::getRecipient)
            .map(in_silence).toPropertyWhenPresent("in_silence", record::getIn_silence)
            .map(send_status).toPropertyWhenPresent("send_status", record::getSend_status)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(alert_type).toPropertyWhenPresent("alert_type", record::getAlert_type)
            .map(content).toPropertyWhenPresent("content", record::getContent)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default Optional<AlertLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alertLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default List<AlertLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alertLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default List<AlertLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alertLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default Optional<AlertLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alertLog, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    static UpdateDSL<UpdateModel> updateAllColumns(AlertLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(alarm_id).equalTo(record::getAlarm_id)
                .set(execute_id).equalTo(record::getExecute_id)
                .set(way).equalTo(record::getWay)
                .set(recipient).equalTo(record::getRecipient)
                .set(in_silence).equalTo(record::getIn_silence)
                .set(send_status).equalTo(record::getSend_status)
                .set(create_at).equalTo(record::getCreate_at)
                .set(alert_type).equalTo(record::getAlert_type)
                .set(content).equalTo(record::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlertLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(execute_id).equalToWhenPresent(record::getExecute_id)
                .set(way).equalToWhenPresent(record::getWay)
                .set(recipient).equalToWhenPresent(record::getRecipient)
                .set(in_silence).equalToWhenPresent(record::getIn_silence)
                .set(send_status).equalToWhenPresent(record::getSend_status)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(alert_type).equalToWhenPresent(record::getAlert_type)
                .set(content).equalToWhenPresent(record::getContent);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default int updateByPrimaryKey(AlertLog record) {
        return update(c ->
            c.set(alarm_id).equalTo(record::getAlarm_id)
            .set(execute_id).equalTo(record::getExecute_id)
            .set(way).equalTo(record::getWay)
            .set(recipient).equalTo(record::getRecipient)
            .set(in_silence).equalTo(record::getIn_silence)
            .set(send_status).equalTo(record::getSend_status)
            .set(create_at).equalTo(record::getCreate_at)
            .set(alert_type).equalTo(record::getAlert_type)
            .set(content).equalTo(record::getContent)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.789+08:00", comments="Source Table: alert_log")
    default int updateByPrimaryKeySelective(AlertLog record) {
        return update(c ->
            c.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(execute_id).equalToWhenPresent(record::getExecute_id)
            .set(way).equalToWhenPresent(record::getWay)
            .set(recipient).equalToWhenPresent(record::getRecipient)
            .set(in_silence).equalToWhenPresent(record::getIn_silence)
            .set(send_status).equalToWhenPresent(record::getSend_status)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(alert_type).equalToWhenPresent(record::getAlert_type)
            .set(content).equalToWhenPresent(record::getContent)
            .where(id, isEqualTo(record::getId))
        );
    }
}