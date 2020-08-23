import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/serverinfo'

const serverinfoApi = {

  saveServerInfo (form) {
    return query.json(baseUrl + '/save', form)
  },

  getServerInfo (id) {
    return query.get(baseUrl + '/get', { id: id })
  },

  findServerInfo (condition) {
    return query.post(baseUrl + '/find', condition)
  }

}

export default serverinfoApi
