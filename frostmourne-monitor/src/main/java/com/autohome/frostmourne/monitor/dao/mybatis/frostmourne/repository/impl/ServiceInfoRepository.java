package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.contract.ServiceInfoQueryForm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ServiceInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServiceInfoDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ServiceInfoDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IServiceInfoRepository;
import com.autohome.frostmourne.monitor.tool.MybatisTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.mybatis.dynamic.sql.SortSpecification;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceInfoRepository implements IServiceInfoRepository {

    @Resource
    private ServiceInfoDynamicMapper serviceInfoDynamicMapper;

    @Override
    public void insertSelective(ServiceInfo record) {
        serviceInfoDynamicMapper.insertSelective(record);
    }

    @Override
    public void updateByPrimaryKeySelective(ServiceInfo record) {
        serviceInfoDynamicMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Optional<ServiceInfo> getById(Long id) {
        return serviceInfoDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ServiceInfo> listByIds(List<Long> ids) {
        return serviceInfoDynamicMapper.select(query -> query.where()
                .and(ServiceInfoDynamicSqlSupport.id, SqlBuilder.isIn(ids)));
    }

    @Override
    public PageInfo<ServiceInfo> find(ServiceInfoQueryForm form) {
        PageHelper.startPage(form.getPageIndex(), form.getPageSize());
        List<ServiceInfo> records = serviceInfoDynamicMapper.select(query -> query.where()
                .and(ServiceInfoDynamicSqlSupport.serviceName, isLike(form.getServiceName()).when(MybatisTool::notNullAndEmpty).then(MybatisTool::twoSideVagueMatch))
                .and(ServiceInfoDynamicSqlSupport.owner, isEqualTo(form.getOwner()).when(MybatisTool::notNullAndEmpty))
                .orderBy(this.parseOrderByColumns(form)));
        return new PageInfo<>(records);
    }

    private SortSpecification[] parseOrderByColumns(ServiceInfoQueryForm form) {
        if ("SERVICE_NAME".equalsIgnoreCase(form.getOrderType())) {
            return new SortSpecification[]{ServiceInfoDynamicSqlSupport.serviceName};
        }
        return new SortSpecification[]{ServiceInfoDynamicSqlSupport.createAt.descending()};
    }

}
