package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.PagerContract;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.TeamInfo;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.TeamInfoDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.TeamInfoDynamicSqlSupport;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.ITeamInfoRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;

@Repository
public class TeamInfoRepository implements ITeamInfoRepository {

    @Resource
    private TeamInfoDynamicMapper teamInfoDynamicMapper;

    @Override
    public boolean insert(TeamInfo teamInfo) {
        return teamInfoDynamicMapper.insert(teamInfo) > 0;
    }

    @Override
    public boolean delete(Long teamId) {
        return teamInfoDynamicMapper.deleteByPrimaryKey(teamId) > 0;
    }

    @Override
    public boolean update(TeamInfo teamInfo) {
        return teamInfoDynamicMapper.updateByPrimaryKeySelective(teamInfo) > 0;
    }

    @Override
    public PagerContract<TeamInfo> findPage(int pageIndex, int pageSize, Long id, String teamName) {
        Page page = PageHelper.startPage(pageIndex, pageSize);
        List<TeamInfo> list = teamInfoDynamicMapper.select(c -> {
            c.where().and(TeamInfoDynamicSqlSupport.id, isEqualTo(id).when(Objects::nonNull))
                    .and(TeamInfoDynamicSqlSupport.team_name, isLike(teamName).when(Objects::nonNull).then(s -> s + "%s"));
            return c.orderBy(TeamInfoDynamicSqlSupport.id.descending());
        });
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int) page.getTotal());
    }

    @Override
    public List<TeamInfo> find(Long departmentId) {
        return teamInfoDynamicMapper.select(c -> c.where()
                .and(TeamInfoDynamicSqlSupport.department_id, isEqualTo(departmentId).when(Objects::nonNull)));
    }

    @Override
    public int deleteByDepartment(Long departmentId) {
        return teamInfoDynamicMapper.delete(c -> c.where().and(TeamInfoDynamicSqlSupport.department_id, isEqualTo(departmentId)));
    }

    @Override
    public Optional<TeamInfo> findByName(String teamName) {
        return teamInfoDynamicMapper.selectOne(c -> c.where().and(TeamInfoDynamicSqlSupport.team_name, isEqualTo(teamName)));
    }

    @Override
    public Optional<TeamInfo> findById(Long teamId) {
        return teamInfoDynamicMapper.selectByPrimaryKey(teamId);
    }
}
