package com.autohome.frostmourne.monitor.service.accesslog;

import org.springframework.stereotype.Service;

@Service
public class AccesslogDataService {
    /**
     * 日志明细查询
     */
    public void detail() {}


    /**
     * 访问日志性能TP相关统计
     */
    public void percentiles() {}

    /**
     * 访问日志耗时区间统计
     */
    public void costRange() {}

    /**
     * 慢流量服务器分组统计
     */
    public void slowServer() {}

    /**
     * 慢流量uri分组统计
     */
    public void slowUri() {}


}
