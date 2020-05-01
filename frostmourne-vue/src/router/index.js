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
    path: '/example',
    component: Layout,
    redirect: '/example/table.view',
    name: 'Example',
    meta: { title: 'Example', icon: 'example' },
    hidden: true,
    children: [
      {
        path: 'table.view',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree.view',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index.view',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    hidden: true,
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1.view',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1.view',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2.view',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1.view',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2.view',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3.view',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2.view',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'menu2' }
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
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
