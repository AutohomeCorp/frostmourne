package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertUpgradeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.AlertUpgrade;
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
public interface AlertUpgradeDynamicMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, alarmId, status, timesToUpgrade, ways, dingRobotHook, httpPostUrl, wechatRobotHook, feishuRobotHook, creator, createAt);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<AlertUpgrade> insertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertUpgradeResult")
    Optional<AlertUpgrade> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertUpgradeResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarmId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="times_to_upgrade", property="timesToUpgrade", jdbcType=JdbcType.INTEGER),
        @Result(column="ways", property="ways", jdbcType=JdbcType.VARCHAR),
        @Result(column="ding_robot_hook", property="dingRobotHook", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_post_url", property="httpPostUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_robot_hook", property="wechatRobotHook", jdbcType=JdbcType.VARCHAR),
        @Result(column="feishu_robot_hook", property="feishuRobotHook", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AlertUpgrade> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alertUpgrade, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alertUpgrade, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(AlertUpgrade record) {
        return MyBatis3Utils.insert(this::insert, record, alertUpgrade, c ->
            c.map(alarmId).toProperty("alarmId")
            .map(status).toProperty("status")
            .map(timesToUpgrade).toProperty("timesToUpgrade")
            .map(ways).toProperty("ways")
            .map(dingRobotHook).toProperty("dingRobotHook")
            .map(httpPostUrl).toProperty("httpPostUrl")
            .map(wechatRobotHook).toProperty("wechatRobotHook")
            .map(feishuRobotHook).toProperty("feishuRobotHook")
            .map(creator).toProperty("creator")
            .map(createAt).toProperty("createAt")
        );
    }

    default int insertSelective(AlertUpgrade record) {
        return MyBatis3Utils.insert(this::insert, record, alertUpgrade, c ->
            c.map(alarmId).toPropertyWhenPresent("alarmId", record::getAlarmId)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(timesToUpgrade).toPropertyWhenPresent("timesToUpgrade", record::getTimesToUpgrade)
            .map(ways).toPropertyWhenPresent("ways", record::getWays)
            .map(dingRobotHook).toPropertyWhenPresent("dingRobotHook", record::getDingRobotHook)
            .map(httpPostUrl).toPropertyWhenPresent("httpPostUrl", record::getHttpPostUrl)
            .map(wechatRobotHook).toPropertyWhenPresent("wechatRobotHook", record::getWechatRobotHook)
            .map(feishuRobotHook).toPropertyWhenPresent("feishuRobotHook", record::getFeishuRobotHook)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
        );
    }

    default Optional<AlertUpgrade> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alertUpgrade, completer);
    }

    default List<AlertUpgrade> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alertUpgrade, completer);
    }

    default List<AlertUpgrade> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alertUpgrade, completer);
    }

    default Optional<AlertUpgrade> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alertUpgrade, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(AlertUpgrade record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalTo(record::getAlarmId)
                .set(status).equalTo(record::getStatus)
                .set(timesToUpgrade).equalTo(record::getTimesToUpgrade)
                .set(ways).equalTo(record::getWays)
                .set(dingRobotHook).equalTo(record::getDingRobotHook)
                .set(httpPostUrl).equalTo(record::getHttpPostUrl)
                .set(wechatRobotHook).equalTo(record::getWechatRobotHook)
                .set(feishuRobotHook).equalTo(record::getFeishuRobotHook)
                .set(creator).equalTo(record::getCreator)
                .set(createAt).equalTo(record::getCreateAt);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlertUpgrade record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(alarmId).equalToWhenPresent(record::getAlarmId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(timesToUpgrade).equalToWhenPresent(record::getTimesToUpgrade)
                .set(ways).equalToWhenPresent(record::getWays)
                .set(dingRobotHook).equalToWhenPresent(record::getDingRobotHook)
                .set(httpPostUrl).equalToWhenPresent(record::getHttpPostUrl)
                .set(wechatRobotHook).equalToWhenPresent(record::getWechatRobotHook)
                .set(feishuRobotHook).equalToWhenPresent(record::getFeishuRobotHook)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(createAt).equalToWhenPresent(record::getCreateAt);
    }

    default int updateByPrimaryKey(AlertUpgrade record) {
        return update(c ->
            c.set(alarmId).equalTo(record::getAlarmId)
            .set(status).equalTo(record::getStatus)
            .set(timesToUpgrade).equalTo(record::getTimesToUpgrade)
            .set(ways).equalTo(record::getWays)
            .set(dingRobotHook).equalTo(record::getDingRobotHook)
            .set(httpPostUrl).equalTo(record::getHttpPostUrl)
            .set(wechatRobotHook).equalTo(record::getWechatRobotHook)
            .set(feishuRobotHook).equalTo(record::getFeishuRobotHook)
            .set(creator).equalTo(record::getCreator)
            .set(createAt).equalTo(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(AlertUpgrade record) {
        return update(c ->
            c.set(alarmId).equalToWhenPresent(record::getAlarmId)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(timesToUpgrade).equalToWhenPresent(record::getTimesToUpgrade)
            .set(ways).equalToWhenPresent(record::getWays)
            .set(dingRobotHook).equalToWhenPresent(record::getDingRobotHook)
            .set(httpPostUrl).equalToWhenPresent(record::getHttpPostUrl)
            .set(wechatRobotHook).equalToWhenPresent(record::getWechatRobotHook)
            .set(feishuRobotHook).equalToWhenPresent(record::getFeishuRobotHook)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}