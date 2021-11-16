import axios from './axios'

export default{
    showTicketList(ticketData) {
        return axios.post('/ticket/list', ticketData)
    },
}