import axios from './axios'

export default {
    showSiteList() {
        return axios.get('/site/list')
    },
    showSiteLike(siteName) {
        return axios.get('/site/getsitelike/' + siteName)
    },
    showSiteNameList() {
        return axios.get('/site/list/sitename')
    },
    showSiteIdBySiteName(siteName) {
        return axios.get('/site/getsiteid/' + siteName)
    },
    showSiteNameBySiteId(siteId) {
        return axios.get('/site/getsitename/' + siteId)
    }
}