import axios from './axios'

export default {
    getPlace(query) {
        return axios.post('/route/place', {}, { params: { query } })
    },
    getDirection(q1, q2) {
        return axios.get('/route/direction', { params: { origin: q1, destination: q2 } })
    }
}