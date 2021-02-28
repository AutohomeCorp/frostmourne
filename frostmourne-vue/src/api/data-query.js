import query from '@/utils/query.js'
import request from '@/utils/request'
const baseUrl = '/api/monitor-api/query'

const dataQueryApi = {

  elasticsearchData (condition) {
    return query.post(baseUrl + '/elasticsearchData', condition)
  },

  shortenLink (longUrl) {
    return query.get(baseUrl + '/shortenLink', { longUrl: longUrl })
  },

  downloadData (condition, fileName) {
    return query.postBlob(baseUrl + '/downloadData', condition)
  }

}

export default dataQueryApi
