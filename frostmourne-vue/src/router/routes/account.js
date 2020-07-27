const accountRoutes = [
  {
    path: 'user-info.view',
    name: 'user-info',
    component: () => import('@/views/account/user-info.vue'),
    meta: { title: '账号信息' }
  },
  {
    path: 'team-info.view',
    name: 'team-info',
    component: () => import('@/views/account/team-info.vue'),
    meta: { title: '团队信息' }
  },
  {
    path: 'department-info.view',
    name: 'department-info',
    component: () => import('@/views/account/department-info.vue'),
    meta: { title: '部门信息' }
  }
]
export default accountRoutes
