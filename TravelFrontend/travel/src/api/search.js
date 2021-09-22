import axios from './axios'

export default{
    search(search){
        return axios.get('/search/' + search)
        },
}