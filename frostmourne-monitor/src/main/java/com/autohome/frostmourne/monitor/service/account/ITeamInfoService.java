package com.autohome.frostmourne.monitor.service.account;

import java.util.List;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;

public interface ITeamInfoService {

    boolean insert(TeamInfo teamInfo, String account);

    boolean delete(Long teamId);

    boolean update(TeamInfo teamId, String account);

    PagerContract<TeamInfo> findPage(int pageIndex, int pageSize, Long id, String teamName);

    List<TeamInfo> find(Long departmentId);

    void deleteByDepartment(Long departmentId);
}
