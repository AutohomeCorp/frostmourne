package com.autohome.frostmourne.monitor.service.account.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DepartmentInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDepartmentInfoRepository;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.account.ITeamInfoService;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
import com.autohome.frostmourne.spi.starter.model.Team;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultAccountService implements IAccountService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultAccountService.class);

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private ITeamInfoService teamInfoService;

    @Resource
    private IDepartmentInfoRepository departmentInfoRepository;

    @Override
    public AccountInfo findByAccount(String account) {
        Optional<UserInfo> optionalUserInfo = userInfoService.findByAccount(account);
        return optionalUserInfo.map(DefaultAccountService::transformUser).orElse(null);
    }

    @Override
    public List<Team> teams(String department) {
        Long departmentId = null;
        if (!Strings.isNullOrEmpty(department)) {
            Optional<DepartmentInfo> optionalDepartmentInfo = departmentInfoRepository.findByDepartmentName(department);
            if (!optionalDepartmentInfo.isPresent()) {
                LOGGER.error("not exist department: {}", department);
                return new ArrayList<>();
            }
            departmentId = optionalDepartmentInfo.get().getId();
        }
        List<TeamInfo> teamInfoList = teamInfoService.find(departmentId);
        return teamInfoList.stream().map(DefaultAccountService::transformTeam).collect(Collectors.toList());

    }

    @Override
    public List<AccountInfo> search(String keyword) {
        PagerContract<UserInfo> pagerContract = userInfoService.findPage(1, 10, null, keyword, null);
        return pagerContract.getList().stream().map(DefaultAccountService::transformUser).collect(Collectors.toList());
    }

    static Team transformTeam(TeamInfo teamInfo) {
        Team team = new Team();
        team.setName(teamInfo.getTeam_name());
        team.setFullName(teamInfo.getFull_name());
        team.setId(teamInfo.getId());
        return team;
    }

    static AccountInfo transformUser(UserInfo userInfo) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount(userInfo.getAccount());
        accountInfo.setEmail(userInfo.getEmail());
        accountInfo.setFullName(userInfo.getFull_name());
        accountInfo.setMobile(userInfo.getMobile());
        accountInfo.setTeamId(userInfo.getTeam_id());
        accountInfo.setWxid(userInfo.getWxid());
        return accountInfo;
    }
}
