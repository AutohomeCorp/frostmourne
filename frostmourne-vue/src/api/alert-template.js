import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/alerttemplate'

const alerttemplateApi = {

  saveAlertTemplate (form) {
    return query.json(baseUrl + '/save', form)
  },

  getAlertTemplate (id) {
    return query.get(baseUrl + '/get', { id: id })
  },

  findAlertTemplate (condition) {
    return query.post(baseUrl + '/find', condition)
  },

  listTemplateTypeOptions () {
    return query.get(baseUrl + '/templatetypeoption/list')
  }
}

export default alerttemplateApi
