import '@/icons' // icon
import '@/permission' // permission control
import '@/styles/index.scss' // global css
import ElementUI from 'element-ui'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
import 'element-ui/lib/theme-chalk/index.css'
import moment from 'moment'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import Vue from 'vue'
import { mockXHR } from '../mock'
import App from './App'
import router from './router'
import store from './store'

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
if (process.env.NODE_ENV === 'mock') {
  mockXHR()
}

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

Object.defineProperty(Vue.prototype, '$moment', { value: moment })

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
