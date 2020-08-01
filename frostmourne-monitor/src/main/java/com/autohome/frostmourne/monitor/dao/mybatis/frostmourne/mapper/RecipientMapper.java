package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Recipient;

public interface RecipientMapper {

    Recipient selectByPrimaryKey(Long id);

}