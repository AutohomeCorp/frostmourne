package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.UserInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;
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
public interface UserInfoDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    BasicColumn[] selectList = BasicColumn.columnList(id, account, full_name, team_id, mobile, email, wxid, creator, create_at, modify_at, modifier);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source Table: user_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source Table: user_info")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source Table: user_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<UserInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source Table: user_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserInfoResult")
    Optional<UserInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source Table: user_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_name", property="full_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="team_id", property="team_id", jdbcType=JdbcType.BIGINT),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="wxid", property="wxid", jdbcType=JdbcType.VARCHAR),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="create_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_at", property="modify_at", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier", property="modifier", jdbcType=JdbcType.VARCHAR)
    })
    List<UserInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.34+08:00", comments="Source Table: user_info")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int insert(UserInfo record) {
        return MyBatis3Utils.insert(this::insert, record, userInfo, c ->
            c.map(account).toProperty("account")
            .map(full_name).toProperty("full_name")
            .map(team_id).toProperty("team_id")
            .map(mobile).toProperty("mobile")
            .map(email).toProperty("email")
            .map(wxid).toProperty("wxid")
            .map(creator).toProperty("creator")
            .map(create_at).toProperty("create_at")
            .map(modify_at).toProperty("modify_at")
            .map(modifier).toProperty("modifier")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int insertSelective(UserInfo record) {
        return MyBatis3Utils.insert(this::insert, record, userInfo, c ->
            c.map(account).toPropertyWhenPresent("account", record::getAccount)
            .map(full_name).toPropertyWhenPresent("full_name", record::getFull_name)
            .map(team_id).toPropertyWhenPresent("team_id", record::getTeam_id)
            .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
            .map(email).toPropertyWhenPresent("email", record::getEmail)
            .map(wxid).toPropertyWhenPresent("wxid", record::getWxid)
            .map(creator).toPropertyWhenPresent("creator", record::getCreator)
            .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
            .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
            .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default Optional<UserInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default List<UserInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default List<UserInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default Optional<UserInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    static UpdateDSL<UpdateModel> updateAllColumns(UserInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(account).equalTo(record::getAccount)
                .set(full_name).equalTo(record::getFull_name)
                .set(team_id).equalTo(record::getTeam_id)
                .set(mobile).equalTo(record::getMobile)
                .set(email).equalTo(record::getEmail)
                .set(wxid).equalTo(record::getWxid)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modify_at).equalTo(record::getModify_at)
                .set(modifier).equalTo(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(account).equalToWhenPresent(record::getAccount)
                .set(full_name).equalToWhenPresent(record::getFull_name)
                .set(team_id).equalToWhenPresent(record::getTeam_id)
                .set(mobile).equalToWhenPresent(record::getMobile)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(wxid).equalToWhenPresent(record::getWxid)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modify_at).equalToWhenPresent(record::getModify_at)
                .set(modifier).equalToWhenPresent(record::getModifier);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int updateByPrimaryKey(UserInfo record) {
        return update(c ->
            c.set(account).equalTo(record::getAccount)
            .set(full_name).equalTo(record::getFull_name)
            .set(team_id).equalTo(record::getTeam_id)
            .set(mobile).equalTo(record::getMobile)
            .set(email).equalTo(record::getEmail)
            .set(wxid).equalTo(record::getWxid)
            .set(creator).equalTo(record::getCreator)
            .set(create_at).equalTo(record::getCreate_at)
            .set(modify_at).equalTo(record::getModify_at)
            .set(modifier).equalTo(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2020-07-29T23:40:25.341+08:00", comments="Source Table: user_info")
    default int updateByPrimaryKeySelective(UserInfo record) {
        return update(c ->
            c.set(account).equalToWhenPresent(record::getAccount)
            .set(full_name).equalToWhenPresent(record::getFull_name)
            .set(team_id).equalToWhenPresent(record::getTeam_id)
            .set(mobile).equalToWhenPresent(record::getMobile)
            .set(email).equalToWhenPresent(record::getEmail)
            .set(wxid).equalToWhenPresent(record::getWxid)
            .set(creator).equalToWhenPresent(record::getCreator)
            .set(create_at).equalToWhenPresent(record::getCreate_at)
            .set(modify_at).equalToWhenPresent(record::getModify_at)
            .set(modifier).equalToWhenPresent(record::getModifier)
            .where(id, isEqualTo(record::getId))
        );
    }
}