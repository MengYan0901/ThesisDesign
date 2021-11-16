import axios from './axios'

export default {
    showTipList() {
        return axios.get('/tip/list/')
    },
    addTip(options = {}) {
        return axios.post('/tip/add', options)
    },
    updateTip(options = {}) {
        return axios.post('/tip/update', options)
    },
    deleteTip(tipId) {
        return axios.delete('/tip/delete/' + tipId)
    },
    searchTipByUserId(userId) {
        return axios.get('/tip/search/byuserid/' + userId)
    },
    searchTipByCountryId(countryId) {
        return axios.get('tip/search/bycountryid/' + countryId)
    },
    searchTipBySiteId(siteId) {
        return axios.get('tip/search/bysiteid/' + siteId)
    },
}