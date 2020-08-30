import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/serviceinfo'

const serviceinfoApi = {

  saveServiceInfo (form) {
    return query.json(baseUrl + '/save', form)
  },

  getServiceInfo (id) {
    return query.get(baseUrl + '/get', { id: id })
  },

  findServiceInfo (condition) {
    return query.post(baseUrl + '/find', condition)
  }

}

export default serviceinfoApi
