package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.common.contract.PagerContract;
import com.autohome.frostmourne.common.contract.ProtocolException;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.TeamInfo;
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
        List<TeamInfo> list = teamInfoDynamicMapper.select(query -> {
            query.where().and(TeamInfoDynamicSqlSupport.id, isEqualTo(id).when(Objects::nonNull)).and(TeamInfoDynamicSqlSupport.teamName,
                    isLike(teamName).when(Objects::nonNull).then(s -> s + "%s"));
            return query.orderBy(TeamInfoDynamicSqlSupport.id.descending());
        });
        return new PagerContract<>(list, page.getPageSize(), page.getPageNum(), (int) page.getTotal());
    }

    @Override
    public List<TeamInfo> find(Long departmentId) {
        return teamInfoDynamicMapper.select(query -> query.where().and(TeamInfoDynamicSqlSupport.departmentId, isEqualTo(departmentId).when(Objects::nonNull)));
    }

    @Override
    public int deleteByDepartment(Long departmentId) {
        return teamInfoDynamicMapper.delete(query -> query.where().and(TeamInfoDynamicSqlSupport.departmentId, isEqualTo(departmentId)));
    }

    @Override
    public Optional<TeamInfo> findByName(String teamName) {
        return teamInfoDynamicMapper.selectOne(query -> query.where().and(TeamInfoDynamicSqlSupport.teamName, isEqualTo(teamName)));
    }

    @Override
    public Optional<TeamInfo> findById(Long teamId) {
        return teamInfoDynamicMapper.selectByPrimaryKey(teamId);
    }

    @Override
    public TeamInfo findFirstTeam() {
        Optional<TeamInfo> optionalTeamInfo = teamInfoDynamicMapper.selectOne(query ->
                query.where().orderBy(TeamInfoDynamicSqlSupport.id.descending()).limit(1));
        if (!optionalTeamInfo.isPresent()) {
            throw new ProtocolException(500, "查不到团队信息");
        }
        return optionalTeamInfo.get();
    }
}
