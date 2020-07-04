import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/admin'
const URL = {
  list: baseUrl + '/list',
  findById: baseUrl + '/findById',
  save: baseUrl + '/save',
  saveAnother: baseUrl + '/saveAnother',
  delete: baseUrl + '/delete',
  open: baseUrl + '/open',
  close: baseUrl + '/close'
}
const adminApi = {
  getList (alarmId, name, teamName, status, pageIndex, pageSize) {
    return query.get(URL.list, {
      account: 'admin',
      alarmId: alarmId,
      name: name,
      teamName: teamName,
      status: status,
      pageIndex: pageIndex,
      pageSize: pageSize
    })
  },
  findById (alarmId) {
    return query.get(URL.findById, { account: 'admin', alarmId: alarmId })
  },
  save (alarm) {
    return query.json(URL.save, alarm)
  },
  saveAnother (alarm) {
    return query.json(URL.saveAnother, alarm)
  },
  operation (url, alarmId) {
    return query.post(url, { alarmId: alarmId })
  },
  delete (alarmId) {
    return this.operation(URL.delete, alarmId)
  },
  open (alarmId) {
    return this.operation(URL.open, alarmId)
  },
  close (alarmId) {
    return this.operation(URL.close, alarmId)
  },
  run (alarmId) {
    return
  }
}

export default adminApi
