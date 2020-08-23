package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.create_at;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.creator;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.id;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.modifier;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.modify_at;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.remark;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.serverInfo;
import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServerInfoDynamicSqlSupport.server_name;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServerInfo;
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
public interface ServerInfoDynamicMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.006+08:00", comments = "Source Table: server_info")
    BasicColumn[] selectList = BasicColumn.columnList(id, server_name, remark, creator, create_at, modifier, modify_at);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:20.994+08:00", comments = "Source Table: server_info")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:20.996+08:00", comments = "Source Table: server_info")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:20.996+08:00", comments = "Source Table: server_info")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
    int insert(InsertStatementProvider<ServerInfo> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:20.999+08:00", comments = "Source Table: server_info")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ServerInfoResult")
    Optional<ServerInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21+08:00", comments = "Source Table: server_info")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ServerInfoResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "server_name", property = "server_name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_at", property = "create_at", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modifier", property = "modifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "modify_at", property = "modify_at", jdbcType = JdbcType.TIMESTAMP)
    })
    List<ServerInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.001+08:00", comments = "Source Table: server_info")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.002+08:00", comments = "Source Table: server_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, serverInfo, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.002+08:00", comments = "Source Table: server_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, serverInfo, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.003+08:00", comments = "Source Table: server_info")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.003+08:00", comments = "Source Table: server_info")
    default int insert(ServerInfo record) {
        return MyBatis3Utils.insert(this::insert, record, serverInfo, c ->
                c.map(server_name).toProperty("server_name")
                        .map(remark).toProperty("remark")
                        .map(creator).toProperty("creator")
                        .map(create_at).toProperty("create_at")
                        .map(modifier).toProperty("modifier")
                        .map(modify_at).toProperty("modify_at")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.005+08:00", comments = "Source Table: server_info")
    default int insertSelective(ServerInfo record) {
        return MyBatis3Utils.insert(this::insert, record, serverInfo, c ->
                c.map(server_name).toPropertyWhenPresent("server_name", record::getServer_name)
                        .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                        .map(creator).toPropertyWhenPresent("creator", record::getCreator)
                        .map(create_at).toPropertyWhenPresent("create_at", record::getCreate_at)
                        .map(modifier).toPropertyWhenPresent("modifier", record::getModifier)
                        .map(modify_at).toPropertyWhenPresent("modify_at", record::getModify_at)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.007+08:00", comments = "Source Table: server_info")
    default Optional<ServerInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, serverInfo, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.008+08:00", comments = "Source Table: server_info")
    default List<ServerInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, serverInfo, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.008+08:00", comments = "Source Table: server_info")
    default List<ServerInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, serverInfo, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.008+08:00", comments = "Source Table: server_info")
    default Optional<ServerInfo> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.009+08:00", comments = "Source Table: server_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, serverInfo, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.009+08:00", comments = "Source Table: server_info")
    static UpdateDSL<UpdateModel> updateAllColumns(ServerInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(server_name).equalTo(record::getServer_name)
                .set(remark).equalTo(record::getRemark)
                .set(creator).equalTo(record::getCreator)
                .set(create_at).equalTo(record::getCreate_at)
                .set(modifier).equalTo(record::getModifier)
                .set(modify_at).equalTo(record::getModify_at);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.01+08:00", comments = "Source Table: server_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ServerInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(server_name).equalToWhenPresent(record::getServer_name)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(creator).equalToWhenPresent(record::getCreator)
                .set(create_at).equalToWhenPresent(record::getCreate_at)
                .set(modifier).equalToWhenPresent(record::getModifier)
                .set(modify_at).equalToWhenPresent(record::getModify_at);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.011+08:00", comments = "Source Table: server_info")
    default int updateByPrimaryKey(ServerInfo record) {
        return update(c ->
                c.set(server_name).equalTo(record::getServer_name)
                        .set(remark).equalTo(record::getRemark)
                        .set(creator).equalTo(record::getCreator)
                        .set(create_at).equalTo(record::getCreate_at)
                        .set(modifier).equalTo(record::getModifier)
                        .set(modify_at).equalTo(record::getModify_at)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2020-08-22T23:05:21.011+08:00", comments = "Source Table: server_info")
    default int updateByPrimaryKeySelective(ServerInfo record) {
        return update(c ->
                c.set(server_name).equalToWhenPresent(record::getServer_name)
                        .set(remark).equalToWhenPresent(record::getRemark)
                        .set(creator).equalToWhenPresent(record::getCreator)
                        .set(create_at).equalToWhenPresent(record::getCreate_at)
                        .set(modifier).equalToWhenPresent(record::getModifier)
                        .set(modify_at).equalToWhenPresent(record::getModify_at)
                        .where(id, isEqualTo(record::getId))
        );
    }
}