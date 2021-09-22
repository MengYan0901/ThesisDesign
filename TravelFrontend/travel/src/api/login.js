import axios from './axios'

export default{
    login(options = {}){
        return axios.post('/authenticate',options)
    }
}