import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import dataV from '@jiaminghi/data-view'
import './plugins/element.js'
import 'element-ui/lib/theme-chalk/index.css'

// 导入样式初始化
import 'normalize.css'
import axios from "axios";

Vue.config.productionTip = false
Vue.use(dataV)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
