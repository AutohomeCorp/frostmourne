import request from '@/utils/request'

export function getList(params) {
  return request({
    url: process.env.VUE_APP_BASE_API + '/table/list',
    method: 'get',
    params
  })
}
