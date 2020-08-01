import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/team'

const teamApi = {

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
  },

  find () {
    return query.get(baseUrl + '/find')
  }
}

export default teamApi
