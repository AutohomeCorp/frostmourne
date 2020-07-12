package com.autohome.frostmourne.monitor.service.account.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.core.contract.ProtocolException;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.DepartmentInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IDepartmentInfoRepository;
import com.autohome.frostmourne.monitor.service.account.IDepartmentInfoService;
import com.autohome.frostmourne.monitor.service.account.ITeamInfoService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentInfoService implements IDepartmentInfoService {

    @Resource
    private IDepartmentInfoRepository departmentInfoRepository;

    @Resource
    private ITeamInfoService teamInfoService;

    @Override
    public boolean insert(DepartmentInfo departmentInfo, String account) {
        Optional<DepartmentInfo> optionalDepartmentInfo = departmentInfoRepository.findByDepartmentName(departmentInfo.getDepartment_name());
        if (optionalDepartmentInfo.isPresent()) {
            throw new ProtocolException(5101, "部门已经存在");
        }
        departmentInfo.setCreator(account);
        departmentInfo.setModifier(account);
        Date now = new Date();
        departmentInfo.setCreate_at(now);
        departmentInfo.setModify_at(now);
        return departmentInfoRepository.insert(departmentInfo);
    }

    @Override
    public boolean delete(Long departmentId) {
        List<TeamInfo> teamInfoList = teamInfoService.find(departmentId);
        if(teamInfoList != null && teamInfoList.size() > 0) {
            throw new ProtocolException(5239, "请先删除部门下的团队");
        }
        return departmentInfoRepository.delete(departmentId);
    }

    @Override
    public boolean update(DepartmentInfo departmentInfo, String account) {
        departmentInfo.setModifier(account);
        departmentInfo.setModify_at(new Date());
        return departmentInfoRepository.update(departmentInfo);
    }

    @Override
    public PagerContract<DepartmentInfo> findPage(int pageIndex, int pageSize, Long id, String departmentName) {
        return departmentInfoRepository.findPage(pageIndex, pageSize, id, departmentName);
    }

    @Override
    public List<DepartmentInfo> find() {
        return departmentInfoRepository.find();
    }

    @Override
    public Optional<DepartmentInfo> findByDepartmentName(String departmentName) {
        return departmentInfoRepository.findByDepartmentName(departmentName);
    }
}
