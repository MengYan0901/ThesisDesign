import axios from './axios'

export default {
    showSiteList() {
        return axios.get('/site/list')
    },
    showSiteLike(siteName) {
        return axios.get('/site/getsitelike/' + siteName)
    }
}