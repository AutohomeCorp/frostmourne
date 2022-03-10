package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic;

import static com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ShortLinkDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ShortLink;
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
public interface ShortLinkDynamicMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.214+08:00", comments="Source Table: short_link")
    BasicColumn[] selectList = BasicColumn.columnList(id, longLink, createAt);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.205+08:00", comments="Source Table: short_link")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.206+08:00", comments="Source Table: short_link")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.207+08:00", comments="Source Table: short_link")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<ShortLink> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.21+08:00", comments="Source Table: short_link")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ShortLinkResult")
    Optional<ShortLink> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.21+08:00", comments="Source Table: short_link")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ShortLinkResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="long_link", property="longLink", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_at", property="createAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ShortLink> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.211+08:00", comments="Source Table: short_link")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.211+08:00", comments="Source Table: short_link")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, shortLink, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.211+08:00", comments="Source Table: short_link")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, shortLink, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.212+08:00", comments="Source Table: short_link")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.212+08:00", comments="Source Table: short_link")
    default int insert(ShortLink record) {
        return MyBatis3Utils.insert(this::insert, record, shortLink, c ->
            c.map(longLink).toProperty("longLink")
            .map(createAt).toProperty("createAt")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.213+08:00", comments="Source Table: short_link")
    default int insertSelective(ShortLink record) {
        return MyBatis3Utils.insert(this::insert, record, shortLink, c ->
            c.map(longLink).toPropertyWhenPresent("longLink", record::getLongLink)
            .map(createAt).toPropertyWhenPresent("createAt", record::getCreateAt)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.215+08:00", comments="Source Table: short_link")
    default Optional<ShortLink> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, shortLink, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.215+08:00", comments="Source Table: short_link")
    default List<ShortLink> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, shortLink, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.216+08:00", comments="Source Table: short_link")
    default List<ShortLink> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, shortLink, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.216+08:00", comments="Source Table: short_link")
    default Optional<ShortLink> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.216+08:00", comments="Source Table: short_link")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, shortLink, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.217+08:00", comments="Source Table: short_link")
    static UpdateDSL<UpdateModel> updateAllColumns(ShortLink record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(longLink).equalTo(record::getLongLink)
                .set(createAt).equalTo(record::getCreateAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.217+08:00", comments="Source Table: short_link")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ShortLink record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(longLink).equalToWhenPresent(record::getLongLink)
                .set(createAt).equalToWhenPresent(record::getCreateAt);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.218+08:00", comments="Source Table: short_link")
    default int updateByPrimaryKey(ShortLink record) {
        return update(c ->
            c.set(longLink).equalTo(record::getLongLink)
            .set(createAt).equalTo(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-09T18:30:03.219+08:00", comments="Source Table: short_link")
    default int updateByPrimaryKeySelective(ShortLink record) {
        return update(c ->
            c.set(longLink).equalToWhenPresent(record::getLongLink)
            .set(createAt).equalToWhenPresent(record::getCreateAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}