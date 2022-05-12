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
                        <v-rating v-model="tip.tipMark"
                                  color="yellow darken-3"
                                  background-color="grey darken-1"
                                  empty-icon="$ratingFull"
                                  half-increments
                                  hover
                                  size="32"></v-rating>
                        <v-btn>Save Rate</v-btn>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn color="deep-purple accent-4"
                               outlined
                               rounded
                               text
                               @click="showLike(tip.tipId)">
                            <v-icon>mdi-heart</v-icon>
                            <span>Like</span>
                        </v-btn>

                        <v-spacer></v-spacer>
                        <v-btn icon
                               @click="showComment(tip.tipId)">
                            <v-icon>{{ 'mdi-chevron-down'}}</v-icon>
                        </v-btn>
                    </v-card-actions>
                </v-card>
                <v-dialog v-model="dialog"
                          width="500">
                    <v-card>
                        <v-card-title class="text-h5 grey lighten-2">
                            Like the tip: {{likenumber}} users
                        </v-card-title>

                        <v-card-text>
                            {{text}}
                        </v-card-text>

                        <v-divider></v-divider>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary"
                                   text
                                   @click="updateLike()">
                                Yes
                            </v-btn>
                            <v-btn color="primary"
                                   text
                                   @click="dialog = false">
                                No
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
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
            likenumber: 0,
            tipComment: [],
            imagebase: axios.defaults.baseURL + '/images',
            dialog: false,
            error: 0,
            like: {
                tipId: 0, cityId: 1, likeTip: 0, likeCity: 1, likeSite: 1, siteId: 1, userId: this.$cookies.get("userId"),
            },
            text: '',
            size: 0,
        };
    },
    created: function () {
        this.resetTips();
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        }
        console.log(this.$cookies.get("token"));
    },
    methods: {
        async addTipByUser(userId) {
            let response = await this.$api.tip.searchTipByUserId(userId);
            this.tipList = this.tipList.concat(response.data);
        },
        async addTipBySite(siteId) {
            let response = await this.$api.tip.searchTipBySiteId(siteId);
            this.tipList = this.tipList.concat(response.data);

        },
        async addTipByCountry(countryId) {
            let response = await this.$api.tip.searchTipByCountryId(countryId);
            this.tipList = this.tipList.concat(response.data);

        },
        async showTipsByUsers(users) {
            console.log("show tips by users");
            this.tipList = [];
            for (let user of users) {
                await this.addTipByUser(user.userId);
                this.code = user.code;
            }
        },
        async showTipsBySites(sites) {
            console.log("show tips by sites");
            this.tipList = [];
            for (let site of sites) {
                await this.addTipBySite(site.siteId);
                this.code = site.code;
            }
        },
        async showTipsByCountries(countries) {
            console.log("show tips by country");
            this.tipList = [];
            for (let country of countries) {
                await this.addTipByCountry(country.countryId);
                this.code = country.code;
            }
        },
        resetTips: function () {
            this.$api.tip.showTipList().then(res => {
                console.log(res);
                this.tipList = res.data;
            });
        },
        showLike: function (tipId) {
            if (this.$cookies.get("token") == null) {
                window.alert("You have to log in first!");
            } else {
                this.$api.like.searchLikeByTipId(tipId).then(res => {
                    this.like.tipId = tipId;
                    console.log(this.like.tipId);
                    this.size = res.data.length - 1;
                    console.log(res.data);
                    this.likenumber = res.data[this.size].likeNumber;
                    this.dialog = true;
                    this.$api.like.checkLike(tipId, this.$cookies.get("userId")).then(res => {
                        console.log(res.data);
                        if (res.data == 1) {
                            this.text = 'You have liked the tip. Would you want to cancle?';
                            this.like.likeTip = 0;
                        } else {
                            this.text = 'Would you want to like the tip?';
                            this.like.likeTip = 1;
                        }
                        console.log(this.like)
                    });
                });
            }

        },
        showComment: function (tipId) {
            this.$router.push({ path: '/comment/' + tipId });
        },
        updateLike: function () {
            console.log(this.like);
            this.$api.like.updateLike(this.like).then(res => {
                console.log(res.data);
                if (res.data == 1) {
                    window.alert("You cancled the like successfully!");
                    this.dialog = false;
                } else {
                    window.alert("You liked the tip successfully!");
                    this.dialog = false;
                }

            });
        },
    }
};
</script>

<style scoped>
</style>