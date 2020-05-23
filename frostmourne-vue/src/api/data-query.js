import query from '@/utils/query.js'

const baseUrl = '/api/monitor-api/query'

const dataQueryApi = {

  elasticsearchData(condition) {
    return query.get(baseUrl + '/elasticsearchData', condition)
  },

  shortenLink(longUrl) {
    return query.get(baseUrl + '/shortenLink', { longUrl: longUrl })
  }

}

export default dataQueryApi
