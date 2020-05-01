import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/statistics'

const statisticsApi = {
  panelData(condition) {
    return query.get(baseUrl + '/panelData', condition)
  },
  alarmAggregation(condition) {
    return query.get(baseUrl + '/aggregation/alarm', condition)
  },
  alertAggregation(condition) {
    return query.get(baseUrl + '/aggregation/alert', condition)
  }
}

export default statisticsApi
