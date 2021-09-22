import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8081/travel';
axios.defaults.timeout = 10000;


export default axios;