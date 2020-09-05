package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Optional;

import com.autohome.frostmourne.monitor.contract.AlertTemplateQueryForm;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.AlertTemplate;
import com.github.pagehelper.PageInfo;

public interface IAlertTemplateRepository {

    void insertSelective(AlertTemplate record);

    void updateByPrimaryKeySelective(AlertTemplate record);

    Optional<AlertTemplate> getById(Long id);

    PageInfo<AlertTemplate> find(AlertTemplateQueryForm form);

}
