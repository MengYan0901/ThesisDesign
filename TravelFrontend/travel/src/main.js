import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import api from "./api/index"
import VueCookies from 'vue-cookies'
import * as VueGoogleMaps from 'vue2-google-maps'


import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

Vue.config.productionTip = false
Vue.prototype.$api = api

Vue.use(VueCookies)
Vue.$cookies.config('7d')
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyDD9VLdCCwDcNmHJ35To-r083_UpJF1314',
    libraries: 'places,drawing,visualization',
  }
});

new Vue({
  router,
  vuetify,
  store,
  
  render: h => h(App)
}).$mount('#app')
