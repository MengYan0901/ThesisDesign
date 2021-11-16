<template>
    <v-row style="margin: 15px">
        <v-col :cols="2">
            <v-row style="margin-right: 5px">
                <v-card elevation="4"
                        outlined
                        class="cardStyle"
                        height="100%">
                    <v-card-title>Search Tips By:</v-card-title>
                    <v-card-text>
                        <div length="150px">
                            <v-text-field label="User"
                                        hide-details
                                        prepend-inner-icon="mdi-magnify"
                                        single-line
                                        @keyup.enter="userSearchEnter"></v-text-field>
                        </div>
                        <div length="150px">
                            <v-text-field label="Country"
                                        hide-details
                                        prepend-inner-icon="mdi-magnify"
                                        single-line
                                        @keyup.enter="countrySearchEnter"></v-text-field>
                        </div>
                        <div length="150px">
                            <v-text-field label="Site"
                                        hide-details
                                        prepend-inner-icon="mdi-magnify"
                                        single-line
                                        @keyup.enter="siteSearchEnter"></v-text-field>
                        </div>
                    </v-card-text>
                </v-card>
            </v-row>
        </v-col>
        <v-col>
            <v-row>
                <show-tip ref="showTip"></show-tip>
            </v-row>
        </v-col>
    </v-row>
</template>

<script>
import axios from '../api/axios';
import showTip from '../components/tip/ShowTip.vue';

export default {
    components: { showTip },
    name: "Tip.vue",

    data: function () {
        return {
        };
    },
    created: function () {
    },
    methods: {
        userSearchEnter: function (e) {
            console.log("回车搜索", e);
            if (e.target.value === "") {
                this.$refs.showTip.resetTips();
            } else {
                this.$api.user.showUserLike(e.target.value).then((res) => {
                    this.$refs.showTip.showTipsByUsers(res.data);
                });
            }
        },
        countrySearchEnter: function (e) {
            if (e.target.value === "") {
                this.$refs.showTip.resetTips();
            } else {
                this.$api.country.showCountryLike(e.target.value).then((res) => {
                    console.log(res.data);
                    this.$refs.showTip.showTipsByCountries(res.data);
                });
            }
        },
        siteSearchEnter: function (e) {
            if (e.target.value === "") {
                this.$refs.showTip.resetTips();
            } else {
                this.$api.site.showSiteLike(e.target.value).then((res) => {
                    console.log(res.data);
                    this.$refs.showTip.showTipsBySites(res.data);
                });
            }
        },
    }
};
</script>

<style scoped>
</style>