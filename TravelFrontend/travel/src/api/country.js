import axios from './axios'

export default {
    showCountryList() {
        return axios.get('/country/list')
    },
    showCountryLike(countryName) {
        return axios.get('/country/getcountrylike/' + countryName)
    },
    showCountryNameList() {
        return axios.get('/country/list/countryname')
    },
    showCountryIdByCountryName(countryName) {
        return axios.get('/country/getcountryid/' + countryName)
    },
    showCountryNameByCountryId(countryId) {
        return axios.get('/country/getcountryname/' + countryId)
    },
}