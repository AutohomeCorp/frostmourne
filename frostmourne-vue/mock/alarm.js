export default [
  {
    url: '/alarm/run',
    type: 'get',
    response: config => {
      if (config.query.alarmId === 22) {
        return {
          'returncode': 0,
          'message': 'mock',
          'result': '[2020-01-13 10:36:09] execute start\n[2020-01-13 10:36:09] start pull metric\n[2020-01-13 10:36:09] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7604,"active_primary_shards":3802,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":18,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\n[2020-01-13 10:36:09] execute end\n'
        }
      } else if (config.query.alarmId === 20) {
        return {
          'returncode': 0,
          'message': 'mock',
          'result': '[2020-01-13 10:37:43] execute start\n[2020-01-13 10:37:43] start pull metric\n[2020-01-13 10:37:47] env = {"NUMBER":0,"startTime":"2020-01-13T10:32:43.976+08:00","THRESHOLD":"1","endTime":"2020-01-13T10:37:43.976+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\n[2020-01-13 10:37:47] execute end\n'
        }
      } else if (config.query.alarmId === 19) {
        return {
          'returncode': 0,
          'message': 'mock',
          'result': '[2020-01-13 10:38:50] execute start\n[2020-01-13 10:38:50] start pull metric\n[2020-01-13 10:38:51] env = {"NUMBER":0,"startTime":"2020-01-13T10:33:50.445+08:00","THRESHOLD":"1","endTime":"2020-01-13T10:38:50.445+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\n[2020-01-13 10:38:51] execute end\n'
        }
      }
    }
  },

  {
    url: '/alarm/test',
    type: 'post',
    response: (config) => {
      if (config.body.id === 22) {
        return {
          'returncode': 0,
          'message': 'mock',
          'result': '[2020-01-13 10:45:31] execute start\n[2020-01-13 10:45:31] start pull metric\n[2020-01-13 10:45:31] env = {"number_of_pending_tasks":0,"cluster_name":"es1","active_shards":7604,"active_primary_shards":3802,"unassigned_shards":0,"delayed_unassigned_shards":0,"HTTP_STATUS":200,"timed_out":false,"HTTP_COST":27,"relocating_shards":0,"initializing_shards":0,"task_max_waiting_in_queue_millis":0,"number_of_data_nodes":10,"number_of_in_flight_fetch":0,"active_shards_percent_as_number":100.0,"EXPRESSION":"status == \\"yellow\\" || status == \\"red\\" || number_of_nodes != 10","status":"green","number_of_nodes":10}\n[2020-01-13 10:45:31] execute end\n'
        }
      } else if (config.body.id === 20) {
        return {
          'returncode': 0,
          'message': 'mock',
          'result': '[2020-01-13 11:15:23] execute start\n[2020-01-13 11:15:23] start pull metric\n[2020-01-13 11:15:23] env = {"NUMBER":0,"startTime":"2020-01-13T11:10:23.752+08:00","THRESHOLD":"1","endTime":"2020-01-13T11:15:23.752+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\n[2020-01-13 11:15:23] execute end\n'
        }
      } else if (config.body.id === 19) {
        return {
          'returncode': 0,
          'message': 'mock',
          'result': '[2020-01-13 11:16:17] execute start\n[2020-01-13 11:16:17] start pull metric\n[2020-01-13 11:16:18] env = {"NUMBER":0,"startTime":"2020-01-13T11:11:17.282+08:00","THRESHOLD":"1","endTime":"2020-01-13T11:16:17.282+08:00","TIME_WINDOW":"5","OPERATOR":"GTE"}\n[2020-01-13 11:16:18] execute end\n'
        }
      }

      return {
        returncode: 0,
        message: 'mock',
        'result': '测试运行成功'
      }
    }
  },

  {
    url: '/alarm/httpTest',
    type: 'post',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'mock',
        'result': {
          'number_of_pending_tasks': 0,
          'cluster_name': 'es1',
          'active_shards': 7614,
          'active_primary_shards': 3807,
          'unassigned_shards': 0,
          'delayed_unassigned_shards': 0,
          'HTTP_STATUS': 200,
          'timed_out': false,
          'HTTP_COST': 25,
          'relocating_shards': 0,
          'initializing_shards': 0,
          'task_max_waiting_in_queue_millis': 0,
          'number_of_data_nodes': 10,
          'number_of_in_flight_fetch': 0,
          'active_shards_percent_as_number': 100.0,
          'status': 'green',
          'number_of_nodes': 10
        }
      }
    }
  }
]
