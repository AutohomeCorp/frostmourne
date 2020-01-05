import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/log'

const logApi = {

  findAlarmLog(condition) {
    return query.get(baseUrl + '/findAlarmLog', condition)
  },

  findAlertLog(condition) {
    return query.get(baseUrl + '/findAlertLog', condition)
  }
}

export default logApi
