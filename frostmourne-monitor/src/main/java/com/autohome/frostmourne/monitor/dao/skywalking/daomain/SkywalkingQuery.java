package com.autohome.frostmourne.monitor.dao.skywalking.daomain;

import java.util.HashMap;
import java.util.Map;

import com.autohome.frostmourne.common.jackson.JacksonUtil;

/**
 * description
 *
 * @author kechangqing
 * @since 2022/5/8 14:43
 */
public class SkywalkingQuery {

    public static String toLogsQuery(SkywalkingLogQueryCondition condition) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("query",
            "query queryLogs($condition: LogQueryCondition) {\n    queryLogs(condition: $condition) {\n        logs {\n          serviceName\n          serviceId\n          serviceInstanceName\n          serviceInstanceId\n          endpointName\n          endpointId\n          traceId\n          timestamp\n          contentType\n          content\n          tags {\n            key\n            value\n          }\n        }\n        total\n    }}");
        queryMap.put("variables", new HashMap<String, Object>() {
            {
                put("condition", condition);
            }
        });

        return JacksonUtil.serialize(queryMap);
    }

    public static String toAlarmsQuery(SkywalkingAlarmQueryCondition condition) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("query",
            "query queryAlarms($keyword: String, $scope: Scope, $duration:Duration!, $tags:[AlarmTag], $paging: Pagination!) {\n    getAlarm(keyword: $keyword, scope: $scope, duration: $duration, paging: $paging, tags: $tags) {\n      items: msgs {\n        key: id\n        message\n        startTime\n        scope\n        tags {\n          key\n          value\n        }\n        events {\n          uuid\n          source {\n            service serviceInstance endpoint\n          }\n          name\n          type\n          message\n          parameters {\n            key\n            value\n          }\n          startTime\n          endTime\n        }\n      }\n      total\n    }}");
        queryMap.put("variables", condition);
        return JacksonUtil.serialize(queryMap);
    }
}
