import axios from './axios'

export default {
    showCommentList() {
        return axios.get('/comment/list')
    },
    addComment(options = {}) {
        return axios.post('/comment/add', options)
    },
    updateComment(options = {}) {
        return axios.post('/comment/update', options)
    },
    deleteComment(commentId) {
        return axios.delete('/comment/delete/' + commentId)
    },
    searchCommentByUserId(userId) {
        return axios.get('/comment/search/byuserid/' + userId)
    },
    searchCommentByTipId(tipId) {
        return axios.get('comment/search/bytipid/' + tipId)
    },
}