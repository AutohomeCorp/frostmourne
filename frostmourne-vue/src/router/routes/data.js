const dataRoutes = [
  {
    path: 'source.view',
    name: 'source-list',
    component: () => import('@/views/data/source.vue'),
    meta: { title: '数据源' }
  },
  {
    path: 'name.view',
    name: 'name-list',
    component: () => import('@/views/data/name.vue'),
    meta: { title: '数据名' }
  }
]
export default dataRoutes
