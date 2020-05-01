export default [
  {
    url: '/statistics/panelData',
    type: 'get',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': {
          taskCount: 204,
          executeCount: 81212,
          alarmCount: 9280,
          alertCount: 13600
        }
      }
    }
  },

  {
    url: '/statistics/aggregation/alarm',
    type: 'get',
    response: _=> {
      return {"returncode":0,"message":"ok","result":[{"date":"2019-12-26T00:00:00.000+0800","count":15},{"date":"2019-12-27T00:00:00.000+0800","count":1},{"date":"2020-01-04T00:00:00.000+0800","count":1},{"date":"2020-01-10T00:00:00.000+0800","count":1},{"date":"2020-01-11T00:00:00.000+0800","count":5},{"date":"2020-01-12T00:00:00.000+0800","count":15},{"date":"2020-01-13T00:00:00.000+0800","count":9},{"date":"2020-01-14T00:00:00.000+0800","count":4},{"date":"2020-01-15T00:00:00.000+0800","count":3},{"date":"2020-01-16T00:00:00.000+0800","count":4}]}
    }
  },

  {
    url: '/statistics/aggregation/alert',
    type: 'get',
    response: _=> {
      return {"returncode":0,"message":"ok","result":[{"date":"2019-12-26T00:00:00.000+0800","count":7},{"date":"2019-12-27T00:00:00.000+0800","count":2},{"date":"2020-01-04T00:00:00.000+0800","count":1},{"date":"2020-01-10T00:00:00.000+0800","count":1},{"date":"2020-01-11T00:00:00.000+0800","count":2},{"date":"2020-01-12T00:00:00.000+0800","count":34},{"date":"2020-01-13T00:00:00.000+0800","count":30},{"date":"2020-01-14T00:00:00.000+0800","count":4},{"date":"2020-01-15T00:00:00.000+0800","count":4},{"date":"2020-01-16T00:00:00.000+0800","count":4}]}
    }
  }
]

