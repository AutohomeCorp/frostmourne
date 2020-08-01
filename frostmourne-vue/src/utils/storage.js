const cacheKeyPre = '_frostmourne_'

class StorageCache {
  constructor (session = true) {
    if (session === true) {
      this.provider = window.sessionStorage
    } else {
      this.provider = window.localStorage
    }
  }
  /**
   * 创建缓存key
   * @param {缓存key} key
   * @param {参数} params
   */
  createKey (key, params) {
    if (!params) {
      params = []
    }
    if (typeof params === 'object') {
      const paramsArray = []
      for (const item in params) {
        paramsArray.push(item + '=' + params[item])
      }
      params = paramsArray.join('&')
    }
    return cacheKeyPre + '#' + key + '?' + params
  }

  /**
   * 获得缓存数据
   * @param {键值} key
   */
  get (key) {
    const cacheKey = this.createKey(key)
    const dataString = this.provider.getItem(cacheKey)
    if (!dataString) {
      return null
    }
    const data = JSON.parse(dataString)
    if (!data.expressTime) {
      return data.data
    }
    const now = new Date().getTime()
    if (now < data.expressTime) {
      return data.data
    }
    return null
  }

  /**
   * 设置缓存k
   * @param {缓存键值} key
   * @param {缓存的数据} data
   * @param {过期时间，以秒为单位，默认不过期} timeout
   */
  set (key, data, timeout) {
    var storeData = { data: data }
    if (timeout) {
      const millSecond = new Date().getTime() + timeout * 1000
      storeData.expressTime = millSecond
    }
    const cacheKey = this.createKey(key)
    this.provider.setItem(cacheKey, JSON.stringify(storeData))
  }

  /**
   * 移除缓存
   * @param {缓存键值} key
   */
  remove (key) {
    this.provider.removeItem(key)
  }
}
const sessionStorage = new StorageCache(true)
const localStorage = new StorageCache(false)
export { StorageCache, sessionStorage, localStorage }
