import '@/icons' // icon
import '@/permission' // permission control
import '@/styles/index.scss' // global css
import ElementUI from 'element-ui'
import i18n from './lang' // internationalization
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

// set ElementUI 
Vue.use(ElementUI, { i18n: (key, value) => i18n.t(key, value) })

Vue.config.productionTip = false

Object.defineProperty(Vue.prototype, '$moment', { value: moment })

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
