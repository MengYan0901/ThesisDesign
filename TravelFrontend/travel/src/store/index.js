import Vue from 'vue'

import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: '',
    },
    mutations: {//这里是set方法
        setuser(state, user) {
            state.user = user;
        }
    },

})