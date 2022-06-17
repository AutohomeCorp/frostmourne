const accountRoutes = [
  {
    path: 'user-info.view',
    name: 'user-info',
    component: () => import('@/views/account/user-info.vue'),
    meta: { title: 'Account' }
  },
  {
    path: 'team-info.view',
    name: 'team-info',
    component: () => import('@/views/account/team-info.vue'),
    meta: { title: 'Team' }
  },
  {
    path: 'department-info.view',
    name: 'department-info',
    component: () => import('@/views/account/department-info.vue'),
    meta: { title: 'Department' }
  }
]
export default accountRoutes
