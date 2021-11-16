<template>
    <div>
        <v-row align="center"
               justify="center"
               class="my-2">
            <v-card elevation="4"
                    outlined
                    class="cardStyle"
                    height="500"
                    width="600"
                    :style="styleObject">
                <form ref="form"
                      lazy-validation>
                    <v-row align="center"
                           justify="center">
                        <v-col cols="12"
                               md="1"
                               align-self="center">
                            <span>
                                <v-icon>mdi-airplane-takeoff</v-icon>
                            </span>
                        </v-col>
                        <v-col cols="12"
                               md="3"
                               align-self="center">
                            <v-text-field v-model="originLocation"
                                          :rules="[v => !!v || 'Location is required!']"
                                          label="Leaving From..."
                                          required></v-text-field>
                        </v-col>
                        <v-col cols="12"
                               md="2"
                               align-self="center">
                            <v-btn text
                                   @click="change">
                                <v-icon color="blue"
                                        x-large>mdi-swap-horizontal</v-icon>
                            </v-btn>
                        </v-col>
                        <v-col cols="12"
                               md="1"
                               align-self="center">
                            <span>
                                <v-icon>mdi-airplane-landing</v-icon>
                            </span>
                        </v-col>
                        <v-col cols="12"
                               md="3"
                               align-self="center">
                            <v-text-field v-model="destinationLocation"
                                          :rules="[v => !!v || 'Location is required!']"
                                          label="Going To..."
                                          required></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row align="center"
                           justify="center">
                        <v-col cols="12"
                               sm="6"
                               md="4">
                            <v-menu ref="menu1"
                                    v-model="menu1"
                                    :close-on-content-click="false"
                                    :return-value.sync="departureDateTimeRange.date"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="auto">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-text-field v-model="departureDateTimeRange.date"
                                                  label="Departure Date"
                                                  prepend-icon="mdi-calendar"
                                                  :rules="[v => !!v || 'Departure Date is required!']"
                                                  readonly
                                                  required
                                                  v-bind="attrs"
                                                  v-on="on"></v-text-field>
                                </template>
                                <v-date-picker v-model="departureDateTimeRange.date"
                                               no-title
                                               scrollable>
                                    <v-spacer></v-spacer>
                                    <v-btn text
                                           color="primary"
                                           @click="menu1 = false">
                                        Cancel
                                    </v-btn>
                                    <v-btn text
                                           color="primary"
                                           @click="$refs.menu1.save(departureDateTimeRange.date)">
                                        OK
                                    </v-btn>
                                </v-date-picker>
                            </v-menu>
                        </v-col>
                        <v-col cols="12"
                               sm="6"
                               md="4">
                            <v-menu ref="menu"
                                    v-model="menu2"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    :return-value.sync="departureDateTimeRange.time"
                                    transition="scale-transition"
                                    offset-y
                                    max-width="290px"
                                    min-width="290px">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-text-field v-model="departureDateTimeRange.time"
                                                  label="Departure Time"
                                                  prepend-icon="mdi-clock-time-four-outline"
                                                  readonly
                                                  v-bind="attrs"
                                                  v-on="on"></v-text-field>
                                </template>
                                <v-time-picker v-if="menu2"
                                               v-model="departureDateTimeRange.time"
                                               full-width
                                               @click:minute="$refs.menu.save(departureDateTimeRange.time)"></v-time-picker>
                            </v-menu>
                        </v-col>
                    </v-row>
                    <v-row align="center"
                           justify="center">
                        <v-col class="d-flex"
                               cols="12"
                               sm="6">
                            <v-select :items="travelerType"
                                      v-model="traveler"
                                      :rules="[v => !!v || 'Traveler Type is required!']"
                                      required
                                      label="Traveler Type"></v-select>
                        </v-col>
                    </v-row>
                    <v-row align="center"
                           justify="center">
                        <v-col class="d-flex"
                               cols="12"
                               sm="6">
                            <v-btn block
                                   class="mr-4"
                                   @click="searchTicket"> Search Ticket </v-btn>
                        </v-col>
                    </v-row>
                </form>
            </v-card>
        </v-row>
    </div>
</template>

<script>
import axios from '../api/axios';
import { required } from 'vuelidate/lib/validators'

export default {
    name: "Ticket.vue",
    validations: {
        originLocation: { required },
        destinationLocation: { required },
        travel: { required }
    },
    data: function () {
        return {
            styleObject: { border: '3px solid orange' },
            menu1: false,
            menu2: false,
            travelerType: ['Adult', 'Child'],
            originLocation: "",
            location: "",
            destinationLocation: "",
            departureDateTimeRange: {
                date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
                time: null
            },
            traveler: "",
            ticketData: {
                originDestinations: [
                    { id: "1", originLocationCode: "", destinationLocationCode: "", departureDateTimeRange: {}, }],
                travelers: [{ id: "1", travelerType: "" }],
                sources: "GDS",
            },
        };
    },
    computed: {
        emptyErrors() {
            const errors = [];
            if (!this.$v.originLocation.required && errors.push("Name is required."))
                return errors;
        },
    },
    created: function () {
    },
    methods: {
        change() {
            this.location = this.originLocation;
            this.originLocation = this.destinationLocation;
            this.destinationLocation = this.location;
        },
        searchTicket() {
            this.ticketData.originDestinations.originLocationCode = this.originLocation;
            this.ticketData.originDestinations.destinationLocationCode = this.destinationLocation;
            this.ticketData.originDestinations.departureDateTimeRange = this.departureDateTimeRange;
            this.ticketData.travelers.travelerType = this.traveler;
            console.log(this.ticketData);
            this.$api.ticket.showTicketList(this.ticketData).then((res) => {
                console.log(res.data);
                this.$router.push({ name: 'ShowTicket' });
            });
        }
    }
};
</script>

<style scoped>
</style>