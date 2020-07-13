package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.AlertDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alert;
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
public interface AlertDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    BasicColumn[] selectList = BasicColumn.columnList(id, alarm_id, ways, silence, creator, create_at, allow_sms_from, allow_sms_to, ding_robot_hook, http_post_url, wechat_robot_hook);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Alert> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Alert> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlertResult")
    Optional<Alert> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlertResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="alarm_id", property="alarm_id", jdbcType=JdbcType.BIGINT),
        @Result(column="ways", property="ways", jdbcType=JdbcType.VARCHAR),
        @Result(column="silence", property="silence", jdbcType=JdbcType.BIGINT),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="allow_sms_from", property="allow_sms_from", jdbcType=JdbcType.INTEGER),
        @Result(column="allow_sms_to", property="allow_sms_to", jdbcType=JdbcType.INTEGER),
        @Result(column="ding_robot_hook", property="ding_robot_hook", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_post_url", property="http_post_url", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_robot_hook", property="wechat_robot_hook", jdbcType=JdbcType.VARCHAR)
    })
    List<Alert> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default int insert(Alert record) {
        return MyBatis3Utils.insert(this::insert, record, alert, c ->
            c.map(id).toProperty("id")
            .map(alarm_id).toProperty("alarm_id")
            .map(ways).toProperty("ways")
            .map(silence).toProperty("silence")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(allow_sms_from).toProperty("allow_sms_from")
            .map(allow_sms_to).toProperty("allow_sms_to")
            .map(ding_robot_hook).toProperty("ding_robot_hook")
            .map(http_post_url).toProperty("http_post_url")
            .map(wechat_robot_hook).toProperty("wechat_robot_hook")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default int insertMultiple(Collection<Alert> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, alert, c ->
            c.map(id).toProperty("id")
            .map(alarm_id).toProperty("alarm_id")
            .map(ways).toProperty("ways")
            .map(silence).toProperty("silence")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(allow_sms_from).toProperty("allow_sms_from")
            .map(allow_sms_to).toProperty("allow_sms_to")
            .map(ding_robot_hook).toProperty("ding_robot_hook")
            .map(http_post_url).toProperty("http_post_url")
            .map(wechat_robot_hook).toProperty("wechat_robot_hook")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default int insertSelective(Alert record) {
        return MyBatis3Utils.insert(this::insert, record, alert, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(alarm_id).toPropertyWhenPresent("alarm_id", record::getAlarm_id)
            .map(ways).toPropertyWhenPresent("ways", record::getWays)
            .map(silence).toPropertyWhenPresent("silence", record::getSilence)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(allow_sms_from).toPropertyWhenPresent("allow_sms_from", record::getAllow_sms_from)
            .map(allow_sms_to).toPropertyWhenPresent("allow_sms_to", record::getAllow_sms_to)
            .map(ding_robot_hook).toPropertyWhenPresent("ding_robot_hook", record::getDing_robot_hook)
            .map(http_post_url).toPropertyWhenPresent("http_post_url", record::getHttp_post_url)
            .map(wechat_robot_hook).toPropertyWhenPresent("wechat_robot_hook", record::getWechat_robot_hook)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default Optional<Alert> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.785+08:00", comments="Source Table: alert")
    default List<Alert> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    default List<Alert> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    default Optional<Alert> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alert, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    static UpdateDSL<UpdateModel> updateAllColumns(Alert record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(alarm_id).equalTo(record::getAlarm_id)
                .set(ways).equalTo(record::getWays)
                .set(silence).equalTo(record::getSilence)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(allow_sms_from).equalTo(record::getAllow_sms_from)
                .set(allow_sms_to).equalTo(record::getAllow_sms_to)
                .set(ding_robot_hook).equalTo(record::getDing_robot_hook)
                .set(http_post_url).equalTo(record::getHttp_post_url)
                .set(wechat_robot_hook).equalTo(record::getWechat_robot_hook);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Alert record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(alarm_id).equalToWhenPresent(record::getAlarm_id)
                .set(ways).equalToWhenPresent(record::getWays)
                .set(silence).equalToWhenPresent(record::getSilence)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(allow_sms_from).equalToWhenPresent(record::getAllow_sms_from)
                .set(allow_sms_to).equalToWhenPresent(record::getAllow_sms_to)
                .set(ding_robot_hook).equalToWhenPresent(record::getDing_robot_hook)
                .set(http_post_url).equalToWhenPresent(record::getHttp_post_url)
                .set(wechat_robot_hook).equalToWhenPresent(record::getWechat_robot_hook);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    default int updateByPrimaryKey(Alert record) {
        return update(c ->
            c.set(alarm_id).equalTo(record::getAlarm_id)
            .set(ways).equalTo(record::getWays)
            .set(silence).equalTo(record::getSilence)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(allow_sms_from).equalTo(record::getAllow_sms_from)
            .set(allow_sms_to).equalTo(record::getAllow_sms_to)
            .set(ding_robot_hook).equalTo(record::getDing_robot_hook)
            .set(http_post_url).equalTo(record::getHttp_post_url)
            .set(wechat_robot_hook).equalTo(record::getWechat_robot_hook)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-11T14:44:51.786+08:00", comments="Source Table: alert")
    default int updateByPrimaryKeySelective(Alert record) {
        return update(c ->
            c.set(alarm_id).equalToWhenPresent(record::getAlarm_id)
            .set(ways).equalToWhenPresent(record::getWays)
            .set(silence).equalToWhenPresent(record::getSilence)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(allow_sms_from).equalToWhenPresent(record::getAllow_sms_from)
            .set(allow_sms_to).equalToWhenPresent(record::getAllow_sms_to)
            .set(ding_robot_hook).equalToWhenPresent(record::getDing_robot_hook)
            .set(http_post_url).equalToWhenPresent(record::getHttp_post_url)
            .set(wechat_robot_hook).equalToWhenPresent(record::getWechat_robot_hook)
            .where(id, isEqualTo(record::getId))
        );
    }
}