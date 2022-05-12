import axios from './axios'

export default {
    showLikeList() {
        return axios.get('/like/list')
    },
    updateLike(options = {}) {
        return axios.post('/like/update', options)
    },
    searchLikeByUserId(userId) {
        return axios.get('/like/search/byuserid/' + userId)
    },
    searchLikeByTipId(tipId) {
        return axios.get('/like/search/bytipid/' + tipId)
    },
    searchLikeBySiteId(siteId) {
        return axios.get('/like/search/bysiteid/' + siteId)
    },
    searchLikeByCityId(cityId) {
        return axios.get('/like/search/bycityid/' + cityId)
    },
    checkLike(tipId, userId) {
        return axios.get('/like/check/' + tipId +'/'+ userId)
    }
}