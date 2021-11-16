<template>
    <v-card elevation="4"
            outlined
            class="cardStyle"
            style="width: 100%">
        <div v-if="code === 0">
            <h1>Sorry, not find!</h1>
        </div>
        <div v-if="code === 1">
            <v-layout row
                        wrap
                        justify-center>
                <v-card class="mx-4 my-6"
                        max-width="350"
                        v-for="tip in tipList">
                    <template slot="progress">
                        <v-progress-linear color="deep-purple"
                                            height="10"
                                            indeterminate></v-progress-linear>
                    </template>

                    <v-img height="150"
                            :src="imagebase+tip.tipPhoto"></v-img>

                    <v-card-title>{{tip.tipName}}</v-card-title>

                    <v-card-text>
                        <v-row align="center"
                                class="mx-0"> </v-row>
                        <div class="my-4 subtitle-1">{{tip.tipContent}}</div>
                    </v-card-text>

                    <v-card-actions>
                        <v-btn color="deep-purple accent-4"
                                outlined
                                rounded
                                text>
                            <span>Favorites</span>
                            <v-icon>mdi-heart</v-icon>
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-layout>
        </div>
    </v-card>
</template>

<script>
import axios from '../../api/axios.js';

export default {
    name: "ShowTip.vue",
    props: ['user', 'country', 'site'],
    data: function () {
        return {
            code: 1,
            tipList: [],
            imagebase: axios.defaults.baseURL + '/images',
        };
    },
    created: function () {
        this.resetTips();
    },
    methods: {
        addTipByUser: function (userId) {
            this.$api.tip.searchTipByUserId(userId).then((res) => {
                this.tipList = this.tipList.concat(res.data);
            });
        },
        addTipBySite: function (siteId) {
            this.$api.tip.searchTipBySiteId(siteId).then(res => {
                this.tipList = this.tipList.concat(res.data);
            });
        },
        addTipByCountry: function (countryId) {
            this.$api.tip.searchTipByCountryId(countryId).then(res => {
                this.tipList = this.tipList.concat(res.data);
            });
        },
        showTipsByUsers: function (users) {
            console.log("show tips by users");
            this.tipList = [];
            for (let user of users) {
                this.addTipByUser(user.userId);
                this.code = user.code;
            }
        },
        showTipsBySites: function (sites) {
            console.log("show tips by sites");
            this.tipList = [];
            for (let site of sites) {
                this.addTipBySite(site.siteId);
                this.code = site.code;
            }
        },
        showTipsByCountries: function (countries) {
            console.log("show tips by country");
            this.tipList = [];
            for (let country of countries) {
                this.addTipByCountry(country.countryId);
                this.code = country.code;
            }
        },
        resetTips: function () {
            this.$api.tip.showTipList().then(res => {
                console.log(res);
                this.tipList = res.data;
            });
        }
    }
};
</script>

<style scoped>
</style>