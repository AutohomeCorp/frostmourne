import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/query'

const dataQueryApi = {
  
  elasticsearchData(condition) {
    return query.get(baseUrl + '/elasticsearchData', condition)
  }

}

export default dataQueryApi