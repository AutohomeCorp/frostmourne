package com.autohome.frostmourne.monitor.dao.http;

import com.autohome.frostmourne.monitor.dao.http.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;

public class MockXxlJob implements IXxlJob {
    @Override
    public ReturnT<String> addJob(XxlJobInfo xxlJobInfo) {
        return new ReturnT<>("-1");
    }

    @Override
    public ReturnT<String> updateJob(XxlJobInfo xxlJobInfo) {
        return new ReturnT<>("ok");
    }

    @Override
    public ReturnT<String> remove(int id) {
        return new ReturnT<>("ok");
    }

    @Override
    public ReturnT<String> start(int id) {
        return new ReturnT<>("ok");
    }

    @Override
    public ReturnT<String> stop(int id) {
        return new ReturnT<>("ok");
    }
}
