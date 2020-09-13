package com.autohome.frostmourne.monitor.service.account.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.ITeamInfoRepository;
import com.autohome.frostmourne.monitor.service.account.ITeamInfoService;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class TeamInfoService implements ITeamInfoService {

    @Resource
    private ITeamInfoRepository teamInfoRepository;

    @Resource
    private IUserInfoService userInfoService;

    @Override
    public boolean insert(TeamInfo teamInfo, String account) {
        Optional<TeamInfo> optionalTeamInfo = teamInfoRepository.findByName(teamInfo.getTeamName());
        if (optionalTeamInfo.isPresent()) {
            throw new ProtocolException(567, "团队名已经存在");
        }
        teamInfo.setCreator(account);
        teamInfo.setModifier(account);
        Date now = new Date();
        teamInfo.setCreateAt(now);
        teamInfo.setModifyAt(now);
        return teamInfoRepository.insert(teamInfo);
    }

    @Override
    public boolean delete(Long teamId) {
        userInfoService.deleteByTeam(teamId);
        return teamInfoRepository.delete(teamId);
    }

    @Override
    public boolean update(TeamInfo teamInfo, String account) {
        teamInfo.setModifier(account);
        teamInfo.setModifyAt(new Date());
        return teamInfoRepository.update(teamInfo);
    }

    @Override
    public PagerContract<TeamInfo> findPage(int pageIndex, int pageSize, Long id, String teamName) {
        return teamInfoRepository.findPage(pageIndex, pageSize, id, teamName);
    }

    @Override
    public List<TeamInfo> find(Long departmentId) {
        return teamInfoRepository.find(departmentId);
    }

    @Override
    public void deleteByDepartment(Long departmentId) {
        List<TeamInfo> teamList = find(departmentId);
        for (TeamInfo teamInfo : teamList) {
            delete(teamInfo.getId());
        }
    }

    @Override
    public Optional<TeamInfo> findByName(String teamName) {
        return teamInfoRepository.findByName(teamName);
    }

    @Override
    public Optional<TeamInfo> findById(Long teamId) {
        return teamInfoRepository.findById(teamId);
    }
}
