const alarms = {
  '22': {
    returncode: 0,
    message: 'ok',
    'result': {
      'id': 22,
      'alarm_name': 'elasticsearch.health',
      'alarm_type': 'http',
      'description': 'elasticsearch集群健康状态监控',
      'owner_key': 'es',
      'status': 'OPEN',
      'execute_result': 'SUCCESS',
      'execute_at': '2020-01-14T09:18:05.000+0800',
      'job_id': 10,
      'cron': '5 0/2 * * * ?',
      'operator': null,
      'team_name': 'tech.first',
      'metricContract': {
        'metric_type': 'object',
        'aggregation_type': '',
        'aggregation_field': '',
        'alarm_id': 22,
        'rule_id': null,
        'data_source_id': 0,
        'data_name_id': 0,
        'data_name': 'http',
        'properties': {},
        'query_string': 'http://127.0.0.1:9200/_cluster/health?pretty',
        'post_data': '',
        'dataSourceContract': null,
        'dataNameContract': null
      },
      'ruleContract': {
        'rule_type': 'expression',
        'alarm_id': 22,
        'alert_template': 'elasticsearch集群状态异常。status: ${status}， number_of_nodes: ${number_of_nodes}',
        'settings': {
          'EXPRESSION': 'status == "yellow" || status == "red" || number_of_nodes != 10'
        }
      },
      'alertContract': {
        'alarm_id': 22,
        'ways': [
          'email'
        ],
        'silence': 60,
        'creator': null,
        'create_at': '2020-01-12T18:34:39.000+0800',
        'allow_sms_from': null,
        'allow_sms_to': null,
        'ding_robot_hook': null,
        'recipients': [
          'kechangqing'
        ]
      }
    }
  },

  '20': {
    'returncode': 0,
    'message': 'ok',
    'result': {
      'id': 20,
      'alarm_name': 'dealer.arch.project.error',
      'alarm_type': 'dealer.program.log',
      'description': '项目错误日志',
      'owner_key': 'application1',
      'status': 'CLOSE',
      'execute_result': 'SUCCESS',
      'execute_at': '2020-01-13T11:14:30.000+0800',
      'job_id': 9,
      'cron': '35 0/5 * * * ?',
      'operator': null,
      'team_name': 'tech.second',
      'metricContract': {
        'metric_type': 'numeric',
        'aggregation_type': 'count',
        'aggregation_field': null,
        'alarm_id': 20,
        'rule_id': null,
        'data_source_id': 1,
        'data_name_id': 1,
        'data_name': 'dealer.program.log',
        'properties': {},
        'query_string': 'Team: dealer.arch AND Level: ERROR AND Project: application1',
        'post_data': null,
        'dataSourceContract': {
          'id': 1,
          'datasource_name': 'ES集群1',
          'datasource_type': 'elasticsearch',
          'service_address': '127.0.0.1:9200'
        },
        'dataNameContract': {
          'id': null,
          'data_name': 'dealer.program.log',
          'display_name': '程序日志',
          'data_source_id': 1,
          'datasource_type': 'elasticsearch',
          'timestamp_field': 'LogAt',
          'settings': {
            'indexPrefix': 'applog-index-',
            'timePattern': 'yyyy.MM.dd'
          },
          'creator': 'kechangqing',
          'create_at': null,
          'modifier': null,
          'modify_at': null
        }
      },
      'ruleContract': {
        'rule_type': 'numeric',
        'alarm_id': 20,
        'alert_template': '${Project}最近${TIME_WINDOW}分钟内有异常日志${NUMBER}条。最近一条异常信息:\n服务器IP: ${ServerIP}\n异常类型: ${ExceptionType}\n自定义信息: ${CustomMessage}\n异常信息: ${ExceptionMessage}',
        'settings': {
          'THRESHOLD': '1',
          'TIME_WINDOW': '5',
          'OPERATOR': 'GTE'
        }
      },
      'alertContract': {
        'alarm_id': 20,
        'ways': [
          'dingding',
          'email'
        ],
        'silence': 60,
        'creator': null,
        'create_at': '2020-01-12T16:26:08.000+0800',
        'allow_sms_from': null,
        'allow_sms_to': null,
        'ding_robot_hook': 'https://oapi.dingtalk.com/robot/send?access_token=509aa0a0ad587a5493fa1445548694d7a03c43a3cb6c307a0928eghghhhhg',
        'recipients': [
          'kechangqing'
        ]
      }
    }
  },
  '19': {
    'returncode': 0,
    'message': 'ok',
    'result': {
      'id': 19,
      'alarm_name': 'dealer.arch.slow',
      'alarm_type': 'dealer.code.slow',
      'description': '慢日志监控',
      'owner_key': 'application2',
      'status': 'OPEN',
      'execute_result': 'SUCCESS',
      'execute_at': '2020-01-14T09:30:35.000+0800',
      'job_id': 8,
      'cron': '35 0/5 * * * ?',
      'operator': null,
      'team_name': 'tech.first',
      'metricContract': {
        'metric_type': 'numeric',
        'aggregation_type': 'count',
        'aggregation_field': null,
        'alarm_id': 19,
        'rule_id': null,
        'data_source_id': 1,
        'data_name_id': 3,
        'data_name': 'dealer.code.slow',
        'properties': {},
        'query_string': 'Team: dealer.arch AND Project: application2 AND Cost: [500 TO *}',
        'post_data': null,
        'dataSourceContract': {
          'id': 1,
          'datasource_name': 'ES集群',
          'datasource_type': 'elasticsearch',
          'service_address': '127.0.0.1:9200'
        },
        'dataNameContract': {
          'id': null,
          'data_name': 'dealer.code.slow',
          'display_name': '程序慢日志',
          'data_source_id': 1,
          'datasource_type': 'elasticsearch',
          'timestamp_field': 'LogAt',
          'settings': {
            'indexPrefix': 'code_slow_log-',
            'timePattern': 'yyyy.MM.dd'
          },
          'creator': 'kechangqing',
          'create_at': null,
          'modifier': null,
          'modify_at': null
        }
      },
      'ruleContract': {
        'rule_type': 'numeric',
        'alarm_id': 19,
        'alert_template': '${Project}最近${TIME_WINDOW}分钟内有超时日志${NUMBER}条。最近一条异常信息:\n服务器IP: ${ServerIP}\n慢类: ${SlowClass}\n慢方法: ${SlowMethod}',
        'settings': {
          'THRESHOLD': '1',
          'TIME_WINDOW': '5',
          'OPERATOR': 'GTE'
        }
      },
      'alertContract': {
        'alarm_id': 19,
        'ways': [
          'dingding',
          'email'
        ],
        'silence': 1,
        'creator': null,
        'create_at': '2020-01-11T19:11:13.000+0800',
        'allow_sms_from': null,
        'allow_sms_to': null,
        'ding_robot_hook': 'https://oapi.dingtalk.com/robot/send?access_token=509aa0a0ad587a5493fa1445548694d7a03c43a3cb6c307a0928eb02772a4JHJJHJ',
        'recipients': [
          'kechangqing'
        ]
      }
    }
  }
}

export default [
  {
    url: '/admin/list',
    type: 'get',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': {
          'rowcount': 3,
          'pagecount': 1,
          'pageindex': 1,
          'list': [
            {
              'id': 22,
              'alarm_name': 'elasticsearch.health',
              'alarm_type': 'http',
              'description': 'ES集群健康状态监控',
              'owner_key': 'es',
              'status': 'OPEN',
              'execute_result': 'SUCCESS',
              'execute_at': '2020-01-12T18:18:05.000+0800',
              'job_id': 10,
              'cron': '5 0/2 * * * ?',
              'creator': 'kechangqing',
              'create_at': '2020-01-11T19:09:22.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2020-01-11T19:23:13.000+0800',
              'team_name': 'usercenter.info'
            },
            {
              'id': 20,
              'alarm_name': 'project.error',
              'alarm_type': 'dealer.program.log',
              'description': '错误日志监控',
              'owner_key': 'application1',
              'status': 'OPEN',
              'execute_result': 'SUCCESS',
              'execute_at': '2020-01-12T18:15:35.000+0800',
              'job_id': 9,
              'cron': '35 0/5 * * * ?',
              'creator': 'kechangqing',
              'create_at': '2019-12-09T20:01:53.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2020-01-12T16:26:08.000+0800',
              'team_name': 'usercenter.info'
            },
            {
              'id': 19,
              'alarm_name': 'slow.log',
              'alarm_type': 'dealer.code.slow',
              'description': '慢日志监控',
              'owner_key': 'application2',
              'status': 'OPEN',
              'execute_result': 'SUCCESS',
              'execute_at': '2020-01-12T18:15:35.000+0800',
              'job_id': 8,
              'cron': '35 0/5 * * * ?',
              'creator': 'kechangqing',
              'create_at': '2019-12-08T19:14:34.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2020-01-11T19:11:13.000+0800',
              'team_name': 'usercenter.info'
            }
          ]
        }
      }
    }
  },

  {
    url: '/admin/findById',
    type: 'get',
    response: config => {
      return alarms[config.query.alarmId]
    }
  },

  {
    url: '/admin/save',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '保存成功',
        result: true
      }
    }
  },

  {
    url: '/admin/saveAnother',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '另存成功',
        result: true
      }
    }
  },

  {
    url: '/admin/delete',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '删除成功',
        result: true
      }
    }
  },

  {
    url: '/admin/open',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '开启成功',
        result: true
      }
    }
  },

  {
    url: '/admin/close',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '关闭成功',
        result: true
      }
    }
  }
]
