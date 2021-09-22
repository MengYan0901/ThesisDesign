import axios from './axios'

export default {
    showUser(userName) {
        return axios.get('/user/list/'+userName)
    },
    updateUser(options = {}) {
        return axios.post('/user/update', options)
    },

}