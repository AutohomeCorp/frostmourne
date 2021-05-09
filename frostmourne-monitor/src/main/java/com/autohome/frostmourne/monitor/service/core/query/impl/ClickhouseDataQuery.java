package com.autohome.frostmourne.monitor.service.core.query.impl;

import com.autohome.frostmourne.monitor.service.core.query.IClickhouseDataQuery;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class ClickhouseDataQuery extends MysqlDataQuery implements IClickhouseDataQuery {

    protected static final DateTimeFormatter FMT_DATETIME = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected Object formatDateParam(DateTime date) {
        return date.toString(FMT_DATETIME);
    }

}
