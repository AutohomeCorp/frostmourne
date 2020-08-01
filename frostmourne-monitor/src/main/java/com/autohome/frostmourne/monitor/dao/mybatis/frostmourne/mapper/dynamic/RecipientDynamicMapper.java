package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.RecipientDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Recipient;
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
public interface RecipientDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.009+08:00", comments="Source Table: recipient")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarm_id, alert_id, account, create_at);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59+08:00", comments="Source Table: recipient")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.001+08:00", comments="Source Table: recipient")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.002+08:00", comments="Source Table: recipient")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Recipient> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.004+08:00", comments="Source Table: recipient")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RecipientResult")
    Optional<Recipient> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.005+08:00", comments="Source Table: recipient")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RecipientResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="alert_id", property="alert_id", jdbcType=JdbcType.BIGINT),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Recipient> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.006+08:00", comments="Source Table: recipient")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.006+08:00", comments="Source Table: recipient")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, recipient, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.006+08:00", comments="Source Table: recipient")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, recipient, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.007+08:00", comments="Source Table: recipient")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.007+08:00", comments="Source Table: recipient")
    default int insert(Recipient record) {
        return MyBatis3Utils.insert(this::insert, record, recipient, c ->
            c.map(alarm_id).toProperty("alarm_id")
            .map(alert_id).toProperty("alert_id")
            .map(account).toProperty("account")
            .map(create_at).toProperty("create_at")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.008+08:00", comments="Source Table: recipient")
    default int insertSelective(Recipient record) {
        return MyBatis3Utils.insert(this::insert, record, recipient, c ->
            c.map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(alert_id).toPropertyWhenPresent("alert_id", record::getAlert_id)
            .map(account).toPropertyWhenPresent("account", record::getAccount)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.01+08:00", comments="Source Table: recipient")
    default Optional<Recipient> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, recipient, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.01+08:00", comments="Source Table: recipient")
    default List<Recipient> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, recipient, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.011+08:00", comments="Source Table: recipient")
    default List<Recipient> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, recipient, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.011+08:00", comments="Source Table: recipient")
    default Optional<Recipient> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.012+08:00", comments="Source Table: recipient")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, recipient, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.012+08:00", comments="Source Table: recipient")
    static UpdateDSL<UpdateModel> updateAllColumns(Recipient record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_id).equalTo(record::getAlarm_id)
                .set(alert_id).equalTo(record::getAlert_id)
                .set(account).equalTo(record::getAccount)
                .set(create_at).equalTo(record::getCreate_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.012+08:00", comments="Source Table: recipient")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Recipient record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(alert_id).equalToWhenPresent(record::getAlert_id)
                .set(account).equalToWhenPresent(record::getAccount)
                .set(create_at).equalToWhenPresent(record::getCreate_at);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.013+08:00", comments="Source Table: recipient")
    default int updateByPrimaryKey(Recipient record) {
        return update(c ->
            c.set(alarm_id).equalTo(record::getAlarm_id)
            .set(alert_id).equalTo(record::getAlert_id)
            .set(account).equalTo(record::getAccount)
            .set(create_at).equalTo(record::getCreate_at)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:23:59.014+08:00", comments="Source Table: recipient")
    default int updateByPrimaryKeySelective(Recipient record) {
        return update(c ->
            c.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(alert_id).equalToWhenPresent(record::getAlert_id)
            .set(account).equalToWhenPresent(record::getAccount)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .where(id, isEqualTo(record::getId))
        );
    }
}