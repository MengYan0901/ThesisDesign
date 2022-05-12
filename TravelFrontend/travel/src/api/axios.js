import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8081/travel';
axios.defaults.timeout = 1000000;
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.common['Access-Control-Allow-Methods'] = 'GET,PUT,POST,DELETE,PATCH,OPTIONS';

export default axios;