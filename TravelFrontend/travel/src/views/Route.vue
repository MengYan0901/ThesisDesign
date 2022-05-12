<template>
    <div>
        <v-row align="center"
               justify="center"
               class="my-2">
            <v-col cols="12">
                <v-row align="center"
                       justify="center"
                       class="my-2">
                    <v-col cols="5">
                        <v-autocomplete v-model="startLocation"
                                        :items="items"
                                        outlined
                                        dense
                                        chips
                                        small-chips
                                        label="StartLocation"></v-autocomplete>
                    </v-col>
                    <v-col cols="5">
                        <v-autocomplete v-model="endLocation"
                                        :items="items"
                                        outlined
                                        dense
                                        chips
                                        small-chips
                                        label="EndLocation"></v-autocomplete>
                    </v-col>
                </v-row>
                <v-row align="center"
                       justify="center"
                       class="my-2">
                    <v-btn class="mr-4"
                           @click="submit"> submit </v-btn>
                    <v-btn class="mr-5"
                           @click="clear"> clear </v-btn>
                </v-row>
            </v-col>
        </v-row>
        <v-row align="center"
               justify="center"
               class="my-2">
            <GmapAutocomplete @place_changed='setPlace' />
            <button @click='addMarker'>
                Add
            </button>
            <GmapMap :center='center'
                     :zoom='12'
                     style='width:100%;  height: 400px;'
                     ref="mapRef">
                <GmapMarker :key="index"
                            v-for="(m, index) in markers"
                            :position="m.position"
                            @click="center=m.position" />
            </GmapMap>
        </v-row>
    </div>
</template>

<script>
import axios from '../api/axios';
import { gmapApi } from 'vue2-google-maps';

export default {
    name: "Route.vue",
    data: function () {
        return {
            center: { lat: 45.508, lng: -73.587 },
            currentPlace: null,
            markers: [],
            places: [],
            items: [],
            startLocation: null,
            endLocation: null,
            startLocationId: null,
            endLocationId: null,
            directionsRenderer: null,
            directionsService: null
        };
    },
    computed: {
        google: gmapApi
    },
    created: function () {
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
        this.showCityNameList();
    },
    mounted() {
        this.$refs.mapRef.$mapPromise.then((map) => {
            this.directionsRenderer = new google.maps.DirectionsRenderer();
            this.directionsService = new google.maps.DirectionsService();
            this.directionsRenderer.setMap(map);
        })
        this.geolocate();
    },
    methods: {
        setPlace(place) {
            this.currentPlace = place;
        },
        addMarker() {
            if (this.currentPlace) {
                const marker = {
                    lat: this.currentPlace.geometry.location.lat(),
                    lng: this.currentPlace.geometry.location.lng(),
                };
                this.markers.push({ position: marker });
                this.places.push(this.currentPlace);
                this.center = marker;
                this.currentPlace = null;
            }
        },
        geolocate: function () {
            navigator.geolocation.getCurrentPosition(position => {
                this.center = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
            });
        },
        showCityNameList() {
            this.$api.city.showCityNameList().then(res => {
                this.items = res.data;
            })
        },
        submit() {
            this.$api.route.getPlace(this.startLocation).then(res => {
                this.startLocationId = res.data.results[0].geometry.location.lat + ',' + res.data.results[0].geometry.location.lng;
                console.log(this.startLocationId);
                this.$api.route.getPlace(this.endLocation).then(res => {
                    this.endLocationId = res.data.results[0].geometry.location.lat + ',' + res.data.results[0].geometry.location.lng;
                    console.log(this.endLocationId);
                    this.drawRouteOnMap();
                });
            });

            // 求你了 加上后端 ok 这不你已经加上了，啊哈哈哈啊哈 强制加上 是

            // this.$api.route.getPlace(this.startLocation).then(res => {
            //     this.startLocationId = res.data.results[0].geometry.location.lat + ',' + res.data.results[0].geometry.location.lng;
            //     console.log(this.startLocationId);
            //     this.$api.route.getPlace(this.endLocation).then(res => {
            //         this.endLocationId = res.data.results[0].geometry.location.lat + ',' + res.data.results[0].geometry.location.lng;
            //         console.log(this.endLocationId);
            //         res.data.travelMode = google.maps.TravelMode.DRIVING;
            //         this.$api.route.getDirection(this.startLocationId, this.endLocationId).then(res => {
            //             console.log(res.data);
            //             this.directionsRenderer.setDirections(res.data); 
            //         });
            //     });
            // });
        },
        drawRouteOnMap() {
            this.directionsService
                .route({
                    origin: {
                        query: this.startLocationId, // 这个是啥，不是经纬度？ 这个是名字
                    },
                    destination: {
                        query: this.endLocationId, // 名字也可以搜吧 那就跟后端屁关系没有了 id 是哈 okoko
                    },
                    travelMode: google.maps.TravelMode.DRIVING, // 哈哈哈，笑死，没关系 能用就行不是
                })
                .then((response) => {
                    this.directionsRenderer.setDirections(response);
                })
                .catch((e) => window.alert("Directions request failed due to " + status));
        },
        clear() {
            this.startLocation = [];
            this.endLocation = [];
        },
    },
};
</script>

<style scope
</style>