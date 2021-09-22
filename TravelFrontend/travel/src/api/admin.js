import axios from './axios'

export default {
    showUserList() {
        return axios.get('/admin/list')
    },
    updateUser(options = {}) {
        return axios.post('/admin/update', options)
    },
    deleteUser(userId) {
        return axios.delete('/admin/delete/' + userId)
    }
}