const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'user-token'
  }
}

const users = {
  'admin-token': {
    account: 'admin',
    fullName: '管理员',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    teamId: 1,
    teamName: 'default',
    mobile: '150****0501',
    email: 'admin@frostmourne.com',
    wxid: 'wxid-admin',
    departmentId: 1,
    roles: ['admin']
  },
  'user-token': {
    account: 'user',
    fullName: '普通用户',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    teamId: 1,
    teamName: 'default',
    mobile: '150****0501',
    email: 'user@frostmourne.com',
    wxid: 'wxid-user',
    departmentId: 1,
    roles: ['user']
  }
}

export default [
  {
    url: '/user/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      const token = tokens[username]

      // mock error
      if (!token) {
        return { returncode: 500, message: 'Account and password are incorrect.' }
      }

      return { returncode: 0, message: 'ok', result: token }
    }
  },

  {
    url: '/user/logout',
    type: 'post',
    response: _ => {
      return { returncode: 0, message: 'ok' }
    }
  },

  {
    url: '/user/info',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return { returncode: 50008, message: 'Login failed, unable to get user details.' }
      }

      return { returncode: 0, message: 'ok', result: info }
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
