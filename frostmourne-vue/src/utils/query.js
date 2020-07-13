import request from '@/utils/request.js'
import qs from 'qs'

// create an axios instance
const service = {
  MediaType: {
    application_form_urlencoded: { 'Content-Type': 'application/x-www-form-urlencoded' },
    application_json: { 'Content-Type': 'application/json' },
    multipart_form_data: { 'Content-Type': 'multipart/form-data' }
  },
  appId: 'frostmourne',
  query (config) {
    if (!config.params) {
      config.params = {}
    }
    config.params._appId = config.params._appId || this.appId
    config.params._t = new Date().getTime()
    return request(config)
  },
  /**
   * get
   */
  get (url, params) {
    return this.query({ method: 'get', url: url, params: params })
  },
  /**
   * post form
   */
  post (url, params) {
    return this.query({ method: 'post', url: url, data: qs.stringify(params), headers: this.MediaType.application_form_urlencoded })
  },
  /**
   * post json
   */
  json (url, params) {
    return this.query({ method: 'post', url: url, data: params, headers: this.MediaType.application_json })
  }
}

export default service
