const dataRoutes = [
  {
    path: 'source.view',
    name: 'source-list',
    component: () => import('@/views/data/source.vue'),
    meta: { title: 'DataSource' }
  },
  {
    path: 'name.view',
    name: 'name-list',
    component: () => import('@/views/data/name.vue'),
    meta: { title: 'DataName' }
  }
]
export default dataRoutes
