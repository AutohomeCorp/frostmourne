package com.autohome.frostmourne.monitor.service.core.query.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.autohome.frostmourne.monitor.contract.MetricContract;
import com.autohome.frostmourne.monitor.dao.jdbc.IJdbcDao;
import com.autohome.frostmourne.monitor.service.core.domain.MetricData;
import com.autohome.frostmourne.monitor.service.core.query.IMysqlDataQuery;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MysqlDataQuery implements IMysqlDataQuery {

    @Autowired
    private IJdbcDao jdbcDao;

    @Override
    public MetricData queryMetricValue(DateTime start,
                                       DateTime end,
                                       MetricContract metricContract) {
        // 准备时间字段，要求查询必须包含where条件: select * from table where 1=1
        String timeField = metricContract.getDataNameContract().getTimestampField();
        if (StringUtils.isEmpty(metricContract.getDataNameContract().getTimestampField())) {
            throw new RuntimeException("数据名时间字段不能为空: " + metricContract.getDataNameContract().getDataName());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(metricContract.getQueryString());
        List<Object> argList = new ArrayList<>();
        if (start != null) {
            sb.append(" and ").append(timeField).append(">=?");
            argList.add(start.toDate());
        }
        if (end != null) {
            sb.append(" and ").append(timeField).append("<?");
            argList.add(end.toDate());
        }
        String sql = sb.toString();
        Object[] args = argList.toArray();

        MetricData result = new MetricData();
        // 汇总
        long collectResult = this.collectResult(metricContract, sql, args);
        result.setMetricValue(collectResult);

        // 查询第一条
        if (collectResult > 0L) {
            Map<String, Object> lastDocument = this.queryLatestDocument(metricContract, sql, args);
            result.setLatestDocument(lastDocument);
        }
        return result;
    }

    private long collectResult(MetricContract metricContract,
                               String sql,
                               Object[] args) {
        String collectSql = "select count(*) cnt from (" + sql + ") t";
        List<Map<String, Object>> collectResult = jdbcDao.query(metricContract.getDataNameContract(),
                metricContract.getDataSourceContract(),
                collectSql,
                args);
        if (collectResult != null && collectResult.size() > 0) {
            return ((Number) collectResult.get(0).get("cnt")).longValue();
        }
        return 0L;
    }

    private Map<String, Object> queryLatestDocument(MetricContract metricContract,
                                                    String sql,
                                                    Object[] args) {
        String querySql = sql + " order by " + metricContract.getDataNameContract().getTimestampField() + " desc limit 1";
        List<Map<String, Object>> collectResult = jdbcDao.query(metricContract.getDataNameContract(),
                metricContract.getDataSourceContract(),
                querySql,
                args);
        if (collectResult != null && collectResult.size() > 0) {
            return collectResult.get(0);
        }
        return null;
    }

}
