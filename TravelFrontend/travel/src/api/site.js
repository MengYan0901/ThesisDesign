import axios from './axios'

export default {
    showSiteList() {
        return axios.get('/site/list')
    },
}