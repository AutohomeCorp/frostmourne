package com.autohome.frostmourne.monitor.dao.http;

import com.autohome.frostmourne.monitor.dao.http.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface IXxlJob {

    @RequestLine("POST /jobinfo/add")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ReturnT<String> addJob(XxlJobInfo xxlJobInfo);

    @RequestLine("POST /jobinfo/update")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ReturnT<String> updateJob(XxlJobInfo xxlJobInfo);

    @RequestLine("POST /jobinfo/remove")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ReturnT<String> remove(@Param("id") int id);

    @RequestLine("POST /jobinfo/start")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ReturnT<String> start(@Param("id") int id);

    @RequestLine("POST /jobinfo/stop")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ReturnT<String> stop(@Param("id") int id);

}
