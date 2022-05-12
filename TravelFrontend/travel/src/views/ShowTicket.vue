<template>
    <div>
        <v-row align="center"
               justify="center"
               class="my-2">
            <v-simple-table>
                <template v-slot:default>
                    <thead>
                        <tr>
                            <th class="text-left">
                                Id
                            </th>
                            <th class="text-left">
                                Number-Of-Bookable-Seats
                            </th>
                            <th class="text-left">
                                Flight-Number
                            </th>
                            <th class="text-left">
                                Departure-Location
                            </th>
                            <th class="text-left">
                                Departure-Time
                            </th>
                            <th class="text-left">
                                Arrival-Location
                            </th>
                            <th class="text-left">
                                Arrival-Time
                            </th>
                            <th class="text-left">
                                Price
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="ticket in tickets"
                            :key="ticket.id">
                            <td>{{ ticket.id }}</td>
                            <td>{{ ticket.numberOfBookableSeats }}</td>
                            <td>{{ ticket.itineraries[0].duration }}</td>
                            <td>{{ ticket.itineraries[0].segments[0].departure.iataCode }}</td>
                            <td>{{ ticket.itineraries[0].segments[0].departure.at }}</td>
                            <td>{{ ticket.itineraries[0].segments[0].arrival.iataCode }}</td>
                            <td>{{ ticket.itineraries[0].segments[0].arrival.at }}</td>
                            <td>{{ ticket.price.base+" "+ticket.price.currency }}</td>
                        </tr>
                    </tbody>
                </template>
            </v-simple-table>
        </v-row>
    </div>
</template>

<script>
import axios from '../api/axios';

export default {
    name: "ShowTicket.vue",
    props: ['tickets'],
    data: function () {
        return {
        };
    },
    created: function () {
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    },
    methods: {}
};
</script>

<style scope
</style>