import query from '@/utils/query.js'
import request from '@/utils/request'
const baseUrl = '/api/monitor-api/query'

const dataQueryApi = {

  elasticsearchData(condition) {
    return query.get(baseUrl + '/elasticsearchData', condition)
  },

  shortenLink(longUrl) {
    return query.get(baseUrl + '/shortenLink', { longUrl: longUrl })
  },

  downloadData(condition, fileName) {
    return request({
      url: baseUrl + '/downloadData',
      method: 'get',
      params: condition,
      responseType: 'blob'
    })
  }

}

export default dataQueryApi
