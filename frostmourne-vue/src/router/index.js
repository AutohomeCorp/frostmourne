/* Layout */
import Layout from '@/layout'
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404.view',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard.view',
    children: [
      {
        path: 'dashboard.view',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: 'Dashboard', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/alarm',
    component: Layout,
    redirect: '/alarm/list.view',
    name: 'alarm',
    meta: { title: '监控管理', icon: 'component' },
    children: [
      {
        path: 'list.view',
        name: 'alarm-list',
        component: () => import('@/views/alarm/list.vue'),
        meta: { title: '监控列表', icon: 'documentation' }
      },
      {
        path: 'edit.view',
        name: 'alarm-edit',
        component: () => import('@/views/alarm/edit.vue'),
        meta: { title: '监控编辑', icon: 'documentation' }
      },
      {
        path: 'alarm-log.view',
        name: 'alarm-log',
        component: () => import('@/views/alarm/alarm-log.vue'),
        meta: { title: '执行日志', icon: 'documentation' }
      },
      {
        path: 'alert-log.view',
        name: 'alert-log',
        component: () => import('@/views/alarm/alert-log.vue'),
        meta: { title: '我的消息', icon: 'documentation' }
      }
    ]
  },
  {
    path: '/query',
    component: Layout,
    name: 'query',
    redirect: '/query/elasticsearch.view',
    meta: { title: '数据查询', icon: 'chart' },
    children: [
      {
        path: 'elasticsearch.view',
        name: 'elasticsearch',
        component: () => import('@/views/query/elasticsearch.vue'),
        meta: { title: '数据查询', icon: 'chart' }
      }
    ]
  },
  {
    path: '/data',
    component: Layout,
    redirect: '/data/source.view',
    name: 'data',
    meta: { title: '数据管理', icon: 'excel' },
    children: [
      {
        path: 'source.view',
        name: 'source-list',
        component: () => import('@/views/data/source.vue'),
        meta: { title: '数据源', icon: 'documentation' }
      },
      {
        path: 'name.view',
        name: 'name-list',
        component: () => import('@/views/data/name.vue'),
        meta: { title: '数据名', icon: 'documentation' }
      }
    ]
  },
  {
    path: '/account',
    component: Layout,
    redirect: '/account/user-info.view',
    name: 'account',
    meta: { title: '账号管理', icon: 'people' },
    children: [
      {
        path: 'user-info.view',
        name: 'user-info',
        component: () => import('@/views/account/user-info.vue'),
        meta: { title: '账号信息', icon: 'documentation' }
      },
      {
        path: 'team-info.view',
        name: 'team-info',
        component: () => import('@/views/account/team-info.vue'),
        meta: { title: '团队信息', icon: 'documentation' }
      },
      {
        path: 'department-info.view',
        name: 'department-info',
        component: () => import('@/views/account/department-info.vue'),
        meta: { title: '部门信息', icon: 'documentation' }
      }
    ]
  },

  {
    path: 'external-link.view',
    component: Layout,
    children: [
      {
        path: 'https://github.com/AutohomeCorp/frostmourne',
        meta: { title: 'Github', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () =>
  new Router({
    mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  })

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter () {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
