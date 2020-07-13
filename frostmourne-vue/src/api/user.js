import request from '@/utils/request'

export function login (data) {
  return request({
    url: '/api/monitor-api/user/login',
    method: 'post',
    data
  })
}

export function getInfo (token) {
  return request({
    url: '/api/monitor-api/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout () {
  return request({
    url: '/api/monitor-api/user/logout',
    method: 'post'
  })
}

export function teams () {
  return request({
    url: '/api/monitor-api/user/teams',
    method: 'get'
  })
}

export function search (keyword) {
  return request({
    url: '/api/monitor-api/user/search',
    method: 'get',
    params: { keyword: keyword }
  })
}
