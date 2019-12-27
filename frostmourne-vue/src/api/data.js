import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/data'

const dataApi = {
  saveDataSource(model) {
    return query.json(baseUrl + '/saveDataSource', model)
  },

  removeDataSource(id) {
    return query.post(baseUrl + '/removeDataSource', { id: id })
  },

  findDataSource(pageIndex, pageSize, datasourceType) {
    return query.get(baseUrl + '/findDataSource', {
      pageIndex: pageIndex,
      pageSize: pageSize,
      datasourceType: datasourceType
    })
  },

  findDataSourceByType(datasourceType) {
    return query.get(baseUrl + '/findDataSourceByType', {
      datasourceType: datasourceType
    })
  },

  saveDataName(model) {
    return query.json(baseUrl + '/saveDataName', model)
  },

  removeDataName(id) {
    return query.post(baseUrl + '/removeDataName', { id: id })
  },

  findDataName(pageIndex, pageSize, datasourceType, datasourceId) {
    return query.get(baseUrl + '/findDataName', {
      pageIndex: pageIndex,
      pageSize: pageSize,
      datasourceType: datasourceType,
      datasourceId: datasourceId
    })
  },

  findDataNameByType(datasourceType) {
    return query.get(baseUrl + '/findDataNameByType', {
      datasourceType: datasourceType
    })
  },

  dataOptions() {
    return query.get(baseUrl + '/dataOptions')
  }
}

export default dataApi
