import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/alarm'
const URL = {
  run: baseUrl + '/run',
  test: baseUrl + '/test'
}
const alarmApi = {
  run (alarmId) {
    return query.get(URL.run, {
      alarmId: alarmId
    })
  },
  test (alarmContract) {
    return query.json(URL.test, alarmContract)
  },
  httpTest (metricContract) {
    return query.json(baseUrl + '/httpTest', metricContract)
  }
}

export default alarmApi
