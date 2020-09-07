package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alert;
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
public interface AlertDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmId, ways, silence, creator, createAt, allowSmsFrom, allowSmsTo, dingRobotHook, httpPostUrl, wechatRobotHook);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Alert> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertResult")
    Optional<Alert> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="ways", property="ways", jdbcType=JdbcType.VARCHAR),
        @Result(column="silence", property="silence", jdbcType=JdbcType.BIGINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="allow_sms_from", property="allowSmsFrom", jdbcType=JdbcType.INTEGER),
        @Result(column="allow_sms_to", property="allowSmsTo", jdbcType=JdbcType.INTEGER),
        @Result(column="ding_robot_hook", property="dingRobotHook", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_post_url", property="httpPostUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_robot_hook", property="wechatRobotHook", jdbcType=JdbcType.VARCHAR)
    })
    List<Alert> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.975+08:00", comments="Source Table: alert")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default int insert(Alert record) {
        return MyBatis3Utils.insert(this::insert, record, alert, c ->
            c.map(alarmId).toProperty("alarmId")
            .map(ways).toProperty("ways")
            .map(silence).toProperty("silence")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
            .map(allowSmsFrom).toProperty("allowSmsFrom")
            .map(allowSmsTo).toProperty("allowSmsTo")
            .map(dingRobotHook).toProperty("dingRobotHook")
            .map(httpPostUrl).toProperty("httpPostUrl")
            .map(wechatRobotHook).toProperty("wechatRobotHook")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default int insertSelective(Alert record) {
        return MyBatis3Utils.insert(this::insert, record, alert, c ->
            c.map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(ways).toPropertyWhenPresent("ways", record::getWays)
            .map(silence).toPropertyWhenPresent("silence", record::getSilence)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
            .map(allowSmsFrom).toPropertyWhenPresent("allowSmsFrom", record::getAllowSmsFrom)
            .map(allowSmsTo).toPropertyWhenPresent("allowSmsTo", record::getAllowSmsTo)
            .map(dingRobotHook).toPropertyWhenPresent("dingRobotHook", record::getDingRobotHook)
            .map(httpPostUrl).toPropertyWhenPresent("httpPostUrl", record::getHttpPostUrl)
            .map(wechatRobotHook).toPropertyWhenPresent("wechatRobotHook", record::getWechatRobotHook)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default Optional<Alert> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default List<Alert> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default List<Alert> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default Optional<Alert> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    static UpdateDSL<UpdateModel> updateAllColumns(Alert record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalTo(record::getAlarmId)
                .set(ways).equalTo(record::getWays)
                .set(silence).equalTo(record::getSilence)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt)
                .set(allowSmsFrom).equalTo(record::getAllowSmsFrom)
                .set(allowSmsTo).equalTo(record::getAllowSmsTo)
                .set(dingRobotHook).equalTo(record::getDingRobotHook)
                .set(httpPostUrl).equalTo(record::getHttpPostUrl)
                .set(wechatRobotHook).equalTo(record::getWechatRobotHook);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Alert record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(ways).equalToWhenPresent(record::getWays)
                .set(silence).equalToWhenPresent(record::getSilence)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt)
                .set(allowSmsFrom).equalToWhenPresent(record::getAllowSmsFrom)
                .set(allowSmsTo).equalToWhenPresent(record::getAllowSmsTo)
                .set(dingRobotHook).equalToWhenPresent(record::getDingRobotHook)
                .set(httpPostUrl).equalToWhenPresent(record::getHttpPostUrl)
                .set(wechatRobotHook).equalToWhenPresent(record::getWechatRobotHook);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default int updateByPrimaryKey(Alert record) {
        return update(c ->
            c.set(alarmId).equalTo(record::getAlarmId)
            .set(ways).equalTo(record::getWays)
            .set(silence).equalTo(record::getSilence)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .set(allowSmsFrom).equalTo(record::getAllowSmsFrom)
            .set(allowSmsTo).equalTo(record::getAllowSmsTo)
            .set(dingRobotHook).equalTo(record::getDingRobotHook)
            .set(httpPostUrl).equalTo(record::getHttpPostUrl)
            .set(wechatRobotHook).equalTo(record::getWechatRobotHook)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-09-07T18:59:32.976+08:00", comments="Source Table: alert")
    default int updateByPrimaryKeySelective(Alert record) {
        return update(c ->
            c.set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(ways).equalToWhenPresent(record::getWays)
            .set(silence).equalToWhenPresent(record::getSilence)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .set(allowSmsFrom).equalToWhenPresent(record::getAllowSmsFrom)
            .set(allowSmsTo).equalToWhenPresent(record::getAllowSmsTo)
            .set(dingRobotHook).equalToWhenPresent(record::getDingRobotHook)
            .set(httpPostUrl).equalToWhenPresent(record::getHttpPostUrl)
            .set(wechatRobotHook).equalToWhenPresent(record::getWechatRobotHook)
            .where(id, isEqualTo(record::getId))
        );
    }
}