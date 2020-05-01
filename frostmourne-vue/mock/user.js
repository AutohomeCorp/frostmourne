export default [
  {
    url: '/user/login',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: 'ok',
        result: 'token'
      }
    }
  },

  {
    url: '/user/logout',
    type: 'post',
    response: _ => {
      return {
        returncode: 0,
        message: 'ok'
      }
    }
  },

  {
    url: '/user/info',
    type: 'get',
    response: _ => {
      return {
        returncode: 0,
        message: 'ok',
        result: {}
      }
    }
  },

  {
    url: '/user/teams',
    type: 'get',
    response: _ => {
      return {
        'returncode': 0,
        'message': 'ok',
        'result': [
          {
            'id': null,
            'name': 'tech.second',
            'fullName': '研发二部',
            'department': 'dealer'
          },
          {
            'id': null,
            'name': 'tech.first',
            'fullName': '研发一部',
            'department': 'dealer'
          }
        ]
      }
    }
  }
]
