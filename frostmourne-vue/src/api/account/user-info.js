import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/userinfo'

const userInfoApi = {

  create (model) {
    return query.json(baseUrl + '/create', model)
  },

  update (model) {
    return query.json(baseUrl + '/update', model)
  },

  delete (id) {
    return query.post(baseUrl + '/delete', { id: id })
  },

  findPage (condition) {
    return query.get(baseUrl + '/findPage', condition)
  }
}

export default userInfoApi
