package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;

public interface AlarmMapper {
    Alarm selectByPrimaryKey(Long id);

    //int total();
}