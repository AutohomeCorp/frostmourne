package com.autohome.frostmourne.monitor.service.account.impl;

import java.util.Date;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.UserInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IUserInfoRepository;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements IUserInfoService {

    @Resource
    private IUserInfoRepository userInfoRepository;

    @Override
    public boolean insert(UserInfo userInfo, String account) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByAccount(userInfo.getAccount());
        if (optionalUserInfo.isPresent()) {
            throw new ProtocolException(5690, "账号已经存在");
        }
        userInfo.setCreator(account);
        userInfo.setModifier(account);
        Date now = new Date();
        userInfo.setCreate_at(now);
        userInfo.setModify_at(now);
        return userInfoRepository.insert(userInfo);
    }

    @Override
    public boolean delete(Long userId) {
        return userInfoRepository.delete(userId);
    }

    @Override
    public boolean update(UserInfo userInfo, String account) {
        userInfo.setModifier(account);
        userInfo.setModify_at(new Date());
        return userInfoRepository.update(userInfo);
    }

    @Override
    public PagerContract<UserInfo> findPage(int pageIndex, int pageSize, Long id, String account, Long teamId) {
        return userInfoRepository.findPage(pageIndex, pageSize, id, account, teamId);
    }

    @Override
    public int deleteByTeam(Long teamId) {
        return userInfoRepository.deleteByTeam(teamId);
    }

    @Override
    public Optional<UserInfo> findByAccount(String account) {
        return userInfoRepository.findByAccount(account);
    }
}
