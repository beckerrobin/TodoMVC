import Vue from 'https://unpkg.com/vue'
import App from './App.vue'

document.title += " - Vue"

Vue.config.productionTip = false

new Vue({
  render: function (h) { return h(App) },
}).$mount('#app')
