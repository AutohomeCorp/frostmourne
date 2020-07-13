package com.autohome.frostmourne.spi.plugin;

import java.util.List;

import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;

/**
 * 团队信息服务
 */
public interface IOrgPlugin {

    /**
     * 获取部门列表
     *
     * @return
     */
    List<Department> departments();

    /**
     * 获取某个部门下的团队列表
     *
     * @param department 部门
     * @return
     */
    List<Team> teams(String department);
}
