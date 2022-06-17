const alarmRoutes = [
  {
    path: 'list.view',
    name: 'alarm-list',
    component: () => import('@/views/alarm/list.vue'),
    meta: { title: 'AlarmList' }
  },
  {
    path: 'edit.view',
    name: 'alarm-edit',
    component: () => import('@/views/alarm/edit.vue'),
    meta: { title: 'AlarmEdit' }
  },
  {
    path: 'alarm-log.view',
    name: 'alarm-log',
    component: () => import('@/views/alarm/alarm-log.vue'),
    meta: { title: 'ExecuteLog' }
  },
  {
    path: 'alert-log.view',
    name: 'alert-log',
    component: () => import('@/views/alarm/alert-log.vue'),
    meta: { title: 'MyMessage' }
  },
  {
    path: 'alert-template.view',
    name: 'alert-template',
    component: () => import('@/views/alarm/alert-template.vue'),
    meta: { title: 'MessageTemplate' }
  },
  {
    path: 'service-info.view',
    name: 'service-info',
    component: () => import('@/views/alarm/service-info.vue'),
    meta: { title: 'Service' }
  }
]
export default alarmRoutes
