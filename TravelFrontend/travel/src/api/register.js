import axios from './axios'

export default{
    username(userName){
        return axios.get('/user/' + userName)
        },
    register(options = {}){
        return axios.post('/register',options)
    }
}