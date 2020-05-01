export default [
  {
    url: '/data/saveDataSource',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '保存成功'
      }
    }
  },

  {
    url: '/data/removeDataSource',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: 'ok',
        result: true
      }
    }
  },

  {
    url: '/data/findDataSourceByType',
    type: 'get',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': [
          {
            'id': 3,
            'datasource_name': 'test',
            'datasource_type': 'elasticsearch',
            'service_address': '127.0.0.1:9201',
            'creator': 'kechangqing',
            'create_at': '2019-12-09T20:01:14.000+0800',
            'modifier': 'kechangqing',
            'modify_at': '2019-12-09T20:01:14.000+0800'
          },
          {
            'id': 1,
            'datasource_name': 'ES集群',
            'datasource_type': 'elasticsearch',
            'service_address': '127.0.0.1:9200',
            'creator': 'kechangqing',
            'create_at': '2019-09-21T18:35:38.000+0800',
            'modifier': 'kechangqing',
            'modify_at': '2019-11-19T13:10:26.000+0800'
          }
        ]
      }
    }
  },

  {
    url: '/data/findDataSource',
    type: 'get',
    response: (pageIndex, pageSize, datasourceType) => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': {
          'rowcount': 2,
          'pagecount': 1,
          'pageindex': 1,
          'list': [
            {
              'id': 3,
              'datasource_name': 'test',
              'datasource_type': 'elasticsearch',
              'service_address': '127.0.0.2',
              'creator': 'kechangqing',
              'create_at': '2019-12-09T20:01:14.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2019-12-09T20:01:14.000+0800'
            },
            {
              'id': 1,
              'datasource_name': 'ES集群',
              'datasource_type': 'elasticsearch',
              'service_address': '127.0.0.1:9200',
              'creator': 'kechangqing',
              'create_at': '2019-09-21T18:35:38.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2019-11-19T13:10:26.000+0800'
            }
          ]
        }
      }
    }
  },

  {
    url: '/data/saveDataName',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: '保存成功'
      }
    }
  },

  {
    url: '/data/removeDataName',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: 'ok',
        result: true
      }
    }
  },

  {
    url: '/data/findDataNameByType',
    type: 'get',
    response: config => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': [
          {
            'id': 3,
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
            'create_at': '2019-12-09T20:00:50.000+0800',
            'modifier': 'kechangqing',
            'modify_at': '2019-12-09T20:00:50.000+0800'
          },
          {
            'id': 1,
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
            'create_at': '2019-09-21T18:39:06.000+0800',
            'modifier': 'kechangqing',
            'modify_at': '2019-12-09T09:12:50.000+0800'
          }
        ]
      }
    }
  },

  {
    url: '/data/findDataName',
    type: 'get',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': {
          'rowcount': 2,
          'pagecount': 1,
          'pageindex': 1,
          'list': [
            {
              'id': 3,
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
              'create_at': '2019-12-09T20:00:50.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2019-12-09T20:00:50.000+0800'
            },
            {
              'id': 1,
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
              'create_at': '2019-09-21T18:39:06.000+0800',
              'modifier': 'kechangqing',
              'modify_at': '2019-12-09T09:12:50.000+0800'
            }
          ]
        }
      }
    }
  },

  {
    url: '/data/dataOptions',
    type: 'get',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': [
          {
            'datasourceType': 'elasticsearch',
            'dataSourceOptionList': [
              {
                'dataSource': {
                  'id': 3,
                  'datasource_name': 'test',
                  'datasource_type': 'elasticsearch',
                  'service_address': '127.0.0.2:9200',
                  'creator': 'kechangqing',
                  'create_at': '2019-12-09T20:01:14.000+0800',
                  'modifier': 'kechangqing',
                  'modify_at': '2019-12-09T20:01:14.000+0800'
                },
                'dataNameContractList': []
              },
              {
                'dataSource': {
                  'id': 1,
                  'datasource_name': 'ES集群',
                  'datasource_type': 'elasticsearch',
                  'service_address': '127.0.0.1:9200',
                  'creator': 'kechangqing',
                  'create_at': '2019-09-21T18:35:38.000+0800',
                  'modifier': 'kechangqing',
                  'modify_at': '2019-11-19T13:10:26.000+0800'
                },
                'dataNameContractList': [
                  {
                    'id': 3,
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
                    'create_at': '2019-12-09T20:00:50.000+0800',
                    'modifier': 'kechangqing',
                    'modify_at': '2019-12-09T20:00:50.000+0800'
                  },
                  {
                    'id': 1,
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
                    'create_at': '2019-09-21T18:39:06.000+0800',
                    'modifier': 'kechangqing',
                    'modify_at': '2019-12-09T09:12:50.000+0800'
                  }
                ]
              }
            ]
          }
        ]
      }
    }
  }
]
