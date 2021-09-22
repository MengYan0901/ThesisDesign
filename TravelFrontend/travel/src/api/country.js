import axios from './axios'

export default {
    showCountryList() {
        return axios.get('/country/list')
    },
}