import axios from './axios'

export default{
    showTicketList(ticketData) {
        console.log(JSON.stringify(ticketData));
        return axios.post('/ticket/list', JSON.stringify(ticketData),{
            headers: {
              'Content-Type': 'application/json',
            },
          })
    },
}