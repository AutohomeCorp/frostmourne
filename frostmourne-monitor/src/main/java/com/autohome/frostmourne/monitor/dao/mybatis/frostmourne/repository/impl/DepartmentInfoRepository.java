package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DepartmentInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DepartmentInfoDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.DepartmentInfoDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDepartmentInfoRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentInfoRepository implements IDepartmentInfoRepository {

    @Resource
    private DepartmentInfoDynamicMapper departmentInfoDynamicMapper;

    @Override
    public boolean insert(DepartmentInfo departmentInfo) {
        return departmentInfoDynamicMapper.insert(departmentInfo) > 0;
    }

    @Override
    public boolean delete(Long departmentId) {
        return departmentInfoDynamicMapper.deleteByPrimaryKey(departmentId) > 0;
    }

    @Override
    public boolean update(DepartmentInfo departmentInfo) {
        return departmentInfoDynamicMapper.updateByPrimaryKeySelective(departmentInfo) > 0;
    }

    @Override
    public PagerContract<DepartmentInfo> findPage(int pageIndex, int pageSize, Long id, String departmentName) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<DepartmentInfo> list = departmentInfoDynamicMapper.select(query -> {
            query.where().and(DepartmentInfoDynamicSqlSupport.id, isEqualTo(id).when(Objects::nonNull))
                    .and(DepartmentInfoDynamicSqlSupport.departmentName, isLike(departmentName).when(Objects::nonNull).then(s -> s + "%s"));
            return query.orderBy(DepartmentInfoDynamicSqlSupport.id.descending());
        });
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int)page.getTotal());
    }

    @Override
    public List<DepartmentInfo> find() {
        return departmentInfoDynamicMapper.select(QueryExpressionDSL::where);
    }

    @Override
    public Optional<DepartmentInfo> findByDepartmentName(String departmentName) {
        return departmentInfoDynamicMapper.selectOne(query -> query.where().and(DepartmentInfoDynamicSqlSupport.departmentName, isEqualTo(departmentName)));
    }
}
