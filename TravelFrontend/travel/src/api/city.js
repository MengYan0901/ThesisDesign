import axios from './axios'

export default {
    showCityNameList() {
        return axios.get('/city/list/cityname')
    },
    showCityIdByCityName(cityName) {
        return axios.get('/city/getcityid/' + cityName)
    },
    showCityNameByCityId(cityId) {
        return axios.get('/city/getcityname/' + cityId)
    }
}