package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RecipientDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.Recipient;
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
public interface RecipientDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmId, alertId, account, createAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Recipient> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RecipientResult")
    Optional<Recipient> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RecipientResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="alert_id", property="alertId", jdbcType=JdbcType.BIGINT),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Recipient> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, recipient, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, recipient, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(Recipient record) {
        return MyBatis3Utils.insert(this::insert, record, recipient, c ->
            c.map(alarmId).toProperty("alarmId")
            .map(alertId).toProperty("alertId")
            .map(account).toProperty("account")
            .map(createAt).toProperty("createAt")
        );
    }

    default int insertSelective(Recipient record) {
        return MyBatis3Utils.insert(this::insert, record, recipient, c ->
            c.map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(alertId).toPropertyWhenPresent("alertId", record::getAlertId)
            .map(account).toPropertyWhenPresent("account", record::getAccount)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
        );
    }

    default Optional<Recipient> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, recipient, completer);
    }

    default List<Recipient> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, recipient, completer);
    }

    default List<Recipient> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, recipient, completer);
    }

    default Optional<Recipient> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, recipient, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(Recipient record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalTo(record::getAlarmId)
                .set(alertId).equalTo(record::getAlertId)
                .set(account).equalTo(record::getAccount)
                .set(createAt).equalTo(record::getCreateAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(Recipient record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(alertId).equalToWhenPresent(record::getAlertId)
                .set(account).equalToWhenPresent(record::getAccount)
                .set(createAt).equalToWhenPresent(record::getCreateAt);
    }

    default int updateByPrimaryKey(Recipient record) {
        return update(c ->
            c.set(alarmId).equalTo(record::getAlarmId)
            .set(alertId).equalTo(record::getAlertId)
            .set(account).equalTo(record::getAccount)
            .set(createAt).equalTo(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(Recipient record) {
        return update(c ->
            c.set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(alertId).equalToWhenPresent(record::getAlertId)
            .set(account).equalToWhenPresent(record::getAccount)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}