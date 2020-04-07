import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'

document.title += " - Vue"

Vue.use(VueRouter)

Vue.config.productionTip = false

new Vue({
  render: function (h) { return h(App) },
}).$mount('#app')
