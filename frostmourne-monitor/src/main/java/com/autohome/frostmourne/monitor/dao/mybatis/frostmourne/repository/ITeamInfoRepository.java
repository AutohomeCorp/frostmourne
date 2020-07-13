package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;

public interface ITeamInfoRepository {

    boolean insert(TeamInfo teamInfo);

    boolean delete(Long teamId);

    boolean update(TeamInfo teamInfo);

    PagerContract<TeamInfo> findPage(int pageIndex, int pageSize, Long id, String teamName);

    List<TeamInfo> find(Long departmentId);

    int deleteByDepartment(Long departmentId);

    Optional<TeamInfo> findByName(String teamName);
}
