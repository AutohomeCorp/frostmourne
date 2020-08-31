const alarmRoutes = [
  {
    path: 'list.view',
    name: 'alarm-list',
    component: () => import('@/views/alarm/list.vue'),
    meta: { title: '监控列表', keepAlive: true }
  },
  {
    path: 'edit.view',
    name: 'alarm-edit',
    component: () => import('@/views/alarm/edit.vue'),
    meta: { title: '监控编辑' }
  },
  {
    path: 'alarm-log.view',
    name: 'alarm-log',
    component: () => import('@/views/alarm/alarm-log.vue'),
    meta: { title: '执行日志' }
  },
  {
    path: 'alert-log.view',
    name: 'alert-log',
    component: () => import('@/views/alarm/alert-log.vue'),
    meta: { title: '我的消息' }
  },
  {
    path: 'alert-template.view',
    name: 'alert-template',
    component: () => import('@/views/alarm/alert-template.vue'),
    meta: { title: '消息模板' }
  }
]
export default alarmRoutes
