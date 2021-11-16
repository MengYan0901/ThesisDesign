import axios from './axios'

export default {
    showCountryList() {
        return axios.get('/country/list')
    },
    showCountryLike(countryName) {
        return axios.get('/country/getcountrylike/' + countryName)
    }
}