package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.DepartmentInfo;

public interface IDepartmentInfoRepository {

    boolean insert(DepartmentInfo departmentInfo);

    boolean delete(Long departmentId);

    boolean update(DepartmentInfo departmentInfo);

    PagerContract<DepartmentInfo> findPage(int pageIndex, int pageSize, Long id, String departmentName);

    List<DepartmentInfo> find();

    Optional<DepartmentInfo> findByDepartmentName(String departmentName);
}
