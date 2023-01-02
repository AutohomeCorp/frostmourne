package com.autohome.frostmourne.monitor.service.account;

import java.util.List;
import java.util.Optional;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.TeamInfo;

public interface ITeamInfoService {

    boolean insert(TeamInfo teamInfo, String account);

    boolean delete(Long teamId);

    boolean update(TeamInfo teamId, String account);

    PagerContract<TeamInfo> findPage(int pageIndex, int pageSize, Long id, String teamName);

    List<TeamInfo> find(Long departmentId);

    void deleteByDepartment(Long departmentId);

    Optional<TeamInfo> findByName(String teamName);

    Optional<TeamInfo> findById(Long teamId);

    TeamInfo findFirstTeam();
}
