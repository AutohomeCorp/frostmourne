/* Layout */
import Layout from '@/layout'
import Vue from 'vue'
import Router from 'vue-router'
import accountRoutes from './routes/account'
import alarmRoutes from './routes/alarm'
import dataRoutes from './routes/data'

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
    children: alarmRoutes
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
    path: 'external-link.view',
    component: Layout,
    children: [
      {
        path: 'https://github.com/AutohomeCorp/frostmourne',
        meta: { title: 'Github', icon: 'link' }
      }
    ]
  },

  { path: '/login', hidden: true, component: () => import('@/views/login/index') },

  { path: '/404.view', hidden: true, component: () => import('@/views/404') },
  // 404 page must be placed at the end !!!
  { path: '*', hidden: true, redirect: '/404' }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/data',
    component: Layout,
    redirect: '/data/source.view',
    name: 'data',
    meta: { title: '数据管理', icon: 'excel', roles: ['admin'] },
    children: dataRoutes
  },
  {
    path: '/account',
    component: Layout,
    redirect: '/account/user-info.view',
    name: 'account',
    meta: { title: '账号管理', icon: 'people', roles: ['admin'] },
    children: accountRoutes
  }
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
