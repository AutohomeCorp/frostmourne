package com.autohome.frostmourne.monitor.service.account.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import com.autohome.frostmourne.monitor.model.account.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.contract.UserContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.account.ITeamInfoService;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;

@Service
public class DefaultAccountService implements IAccountService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultAccountService.class);

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private ITeamInfoService teamInfoService;

    private static Team transformTeam(TeamInfo teamInfo) {
        Team team = new Team();
        team.setName(teamInfo.getTeamName());
        team.setFullName(teamInfo.getFullName());
        team.setId(teamInfo.getId());
        return team;
    }

    private static AccountInfo transformUser(UserContract userInfo) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount(userInfo.getAccount());
        accountInfo.setEmail(userInfo.getEmail());
        accountInfo.setFullName(userInfo.getFullName());
        accountInfo.setMobile(userInfo.getMobile());
        accountInfo.setTeamId(userInfo.getTeamId());
        accountInfo.setWxid(userInfo.getWxid());
        accountInfo.setRoles(userInfo.getRoles());
        return accountInfo;
    }

    @Override
    public Optional<AccountInfo> findByAccount(String account) {
        UserContract userContract = userInfoService.findByAccount(account);
        if (userContract == null) {
            return Optional.empty();
        }
        AccountInfo accountInfo = DefaultAccountService.transformUser(userContract);
        Optional<TeamInfo> optionalTeamInfo = teamInfoService.findById(accountInfo.getTeamId());
        if (!optionalTeamInfo.isPresent()) {
            LOGGER.error("user team not exists. team: {}", accountInfo.getTeamName());
            throw new ProtocolException(5009, "用户所属团队不存在");
        }
        accountInfo.setTeamId(optionalTeamInfo.get().getId());
        accountInfo.setTeamName(optionalTeamInfo.get().getTeamName());
        accountInfo.setDepartmentId(optionalTeamInfo.get().getDepartmentId());
        return Optional.of(accountInfo);
    }

    @Override
    public List<Team> teams(Long departmentId) {
        List<TeamInfo> teamInfoList = teamInfoService.find(departmentId);
        return teamInfoList.stream().map(DefaultAccountService::transformTeam).collect(Collectors.toList());

    }

    @Override
    public List<AccountInfo> search(String keyword) {
        PagerContract<UserContract> pagerContract = userInfoService.findPage(1, 10, null, keyword, null);
        return pagerContract.getList().stream().map(DefaultAccountService::transformUser).collect(Collectors.toList());
    }
}
