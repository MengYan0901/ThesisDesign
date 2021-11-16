import axios from './axios'

export default {
    showUser(userName) {
        return axios.get('/user/getuser/' + userName)
    },
    showUserLike(userName) {
        return axios.get('/user/getuserlike/' + userName)
    },
    updateUser(options = {}) {
        return axios.post('/user/update', options)
    },


}