export default [
  {
    url: '/log/findAlarmLog',
    type: '',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': {
          'rowcount': 938,
          'pagecount': 94,
          'pageindex': 1,
          'list': [
            {
              'id': 2395,
              'alarm_id': 22,
              'exe_start': '2020-01-13T19:02:05.000+0800',
              'exe_end': '2020-01-13T19:02:05.000+0800',
              'cost': 28,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T19:02:05.000+0800',
              'message': '[2020-01-13 19:02:05] execute start\r\n[2020-01-13 19:02:05] start pull metric\r\n[2020-01-13 19:02:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":27,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\r\n[2020-01-13 19:02:05] execute end'
            },
            {
              'id': 2394,
              'alarm_id': 19,
              'exe_start': '2020-01-13T19:00:38.000+0800',
              'exe_end': '2020-01-13T19:00:38.000+0800',
              'cost': 171,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T19:00:38.000+0800',
              'message': '[2020-01-13 19:00:37] execute start\r\n[2020-01-13 19:00:37] start pull metric\r\n[2020-01-13 19:00:37] env = {"NUMBER":0,"startTime":"2020-01-13T18:55:37.699+08:00","THRESHOLD":"1","endTime":"2020-01-13T19:00:37.699+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\r\n[2020-01-13 19:00:37] execute end'
            },
            {
              'id': 2393,
              'alarm_id': 22,
              'exe_start': '2020-01-13T19:00:05.000+0800',
              'exe_end': '2020-01-13T19:00:05.000+0800',
              'cost': 33,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T19:00:05.000+0800',
              'message': '[2020-01-13 19:00:05] execute start\r\n[2020-01-13 19:00:05] start pull metric\r\n[2020-01-13 19:00:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":30,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\r\n[2020-01-13 19:00:05] execute end'
            },
            {
              'id': 2392,
              'alarm_id': 22,
              'exe_start': '2020-01-13T18:58:05.000+0800',
              'exe_end': '2020-01-13T18:58:05.000+0800',
              'cost': 30,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:58:05.000+0800',
              'message': '[2020-01-13 18:58:05] execute start\r\n[2020-01-13 18:58:05] start pull metric\r\n[2020-01-13 18:58:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":28,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\r\n[2020-01-13 18:58:05] execute end'
            },
            {
              'id': 2391,
              'alarm_id': 22,
              'exe_start': '2020-01-13T18:56:05.000+0800',
              'exe_end': '2020-01-13T18:56:05.000+0800',
              'cost': 44,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:56:05.000+0800',
              'message': '[2020-01-13 18:56:05] execute start\r\n[2020-01-13 18:56:05] start pull metric\r\n[2020-01-13 18:56:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":43,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\r\n[2020-01-13 18:56:05] execute end'
            },
            {
              'id': 2390,
              'alarm_id': 19,
              'exe_start': '2020-01-13T18:55:35.000+0800',
              'exe_end': '2020-01-13T18:55:35.000+0800',
              'cost': 233,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:55:35.000+0800',
              'message': '[2020-01-13 18:55:35] execute start\n[2020-01-13 18:55:35] start pull metric\n[2020-01-13 18:55:35] env = {"NUMBER":0,"startTime":"2020-01-13T18:50:35.020+08:00","THRESHOLD":"1","endTime":"2020-01-13T18:55:35.020+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\n[2020-01-13 18:55:35] execute end'
            },
            {
              'id': 2389,
              'alarm_id': 22,
              'exe_start': '2020-01-13T18:54:05.000+0800',
              'exe_end': '2020-01-13T18:54:05.000+0800',
              'cost': 31,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:54:05.000+0800',
              'message': '[2020-01-13 18:54:05] execute start\r\n[2020-01-13 18:54:05] start pull metric\r\n[2020-01-13 18:54:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":29,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\r\n[2020-01-13 18:54:05] execute end'
            },
            {
              'id': 2388,
              'alarm_id': 22,
              'exe_start': '2020-01-13T18:52:05.000+0800',
              'exe_end': '2020-01-13T18:52:05.000+0800',
              'cost': 39,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:52:05.000+0800',
              'message': '[2020-01-13 18:52:05] execute start\n[2020-01-13 18:52:05] start pull metric\n[2020-01-13 18:52:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":25,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\n[2020-01-13 18:52:05] execute end'
            },
            {
              'id': 2387,
              'alarm_id': 19,
              'exe_start': '2020-01-13T18:51:04.000+0800',
              'exe_end': '2020-01-13T18:51:05.000+0800',
              'cost': 392,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:51:05.000+0800',
              'message': '[2020-01-13 18:51:04] execute start\r\n[2020-01-13 18:51:04] start pull metric\r\n[2020-01-13 18:51:04] env = {"NUMBER":0,"startTime":"2020-01-13T18:46:04.397+08:00","THRESHOLD":"1","endTime":"2020-01-13T18:51:04.397+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\r\n[2020-01-13 18:51:04] execute end'
            },
            {
              'id': 2386,
              'alarm_id': 22,
              'exe_start': '2020-01-13T18:50:05.000+0800',
              'exe_end': '2020-01-13T18:50:05.000+0800',
              'cost': 67,
              'execute_result': 'SUCCESS',
              'verify_result': 'FALSE',
              'create_at': '2020-01-13T18:50:05.000+0800',
              'message': '[2020-01-13 18:50:05] execute start\n[2020-01-13 18:50:05] start pull metric\n[2020-01-13 18:50:05] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7704,"active_primary_shards":3852,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":25,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\n[2020-01-13 18:50:05] execute end'
            }
          ]
        }
      }
    }
  },

  {
    url: '/log/findAlertLog',
    type: '',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': {
          'rowcount': 31,
          'pagecount': 4,
          'pageindex': 1,
          'list': [
            {
              'id': 315,
              'alarm_id': 20,
              'execute_id': 2050,
              'way': 'dingding',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T10:50:36.000+0800',
              'alert_type': 'RECOVER',
              'content': '消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n[2020-01-13 10:45:38]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 316,
              'alarm_id': 20,
              'execute_id': 2050,
              'way': 'email',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T10:50:36.000+0800',
              'alert_type': 'RECOVER',
              'content': '消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n[2020-01-13 10:45:38]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 313,
              'alarm_id': 20,
              'execute_id': 2045,
              'way': 'dingding',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T10:45:39.000+0800',
              'alert_type': 'PROBLEM',
              'content': '[2020-01-13 10:45:38]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 314,
              'alarm_id': 20,
              'execute_id': 2045,
              'way': 'email',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T10:45:39.000+0800',
              'alert_type': 'PROBLEM',
              'content': '[2020-01-13 10:45:38]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 311,
              'alarm_id': 20,
              'execute_id': 1954,
              'way': 'dingding',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T09:10:35.000+0800',
              'alert_type': 'RECOVER',
              'content': '消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n[2020-01-13 09:05:37]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 312,
              'alarm_id': 20,
              'execute_id': 1954,
              'way': 'email',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T09:10:35.000+0800',
              'alert_type': 'RECOVER',
              'content': '消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n[2020-01-13 09:05:37]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 309,
              'alarm_id': 20,
              'execute_id': 1950,
              'way': 'dingding',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T09:05:39.000+0800',
              'alert_type': 'PROBLEM',
              'content': '[2020-01-13 09:05:37]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 310,
              'alarm_id': 20,
              'execute_id': 1950,
              'way': 'email',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T09:05:39.000+0800',
              'alert_type': 'PROBLEM',
              'content': '[2020-01-13 09:05:37]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 307,
              'alarm_id': 20,
              'execute_id': 1832,
              'way': 'dingding',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T06:55:36.000+0800',
              'alert_type': 'RECOVER',
              'content': '消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n[2020-01-13 06:50:35]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            },
            {
              'id': 308,
              'alarm_id': 20,
              'execute_id': 1832,
              'way': 'email',
              'recipient': 'kechangqing',
              'in_silence': 'NO',
              'send_status': 'SUCCESS',
              'create_at': '2020-01-13T06:55:36.000+0800',
              'alert_type': 'RECOVER',
              'content': '消息类型: [恢复] 请自己检查问题是否解决,上次报警内容如下\n[2020-01-13 06:50:35]\napplication1最近5分钟内有异常日志1条。最近一条异常信息:\n服务器IP: 127.0.0.1\n异常类型: redis.clients.jedis.exceptions.JedisConnectionException\n自定义信息: HashCache error \n异常信息: save(key:xxxxxx, seconds:1200)\n\n详细请看: http://iii50.cn/uMiahM'
            }
          ]
        }
      }
    }
  }
]
