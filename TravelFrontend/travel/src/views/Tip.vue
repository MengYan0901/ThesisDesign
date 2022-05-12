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
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    },
    methods: {
        async userSearchEnter(e) {
            if (e.target.value === "") {
                this.$refs.showTip.resetTips();
            } else {
                let response = await this.$api.user.showUserLike(e.target.value);
                await this.$refs.showTip.showTipsByUsers(response.data);
            }
        },
        async countrySearchEnter(e) {
            if (e.target.value === "") {
                this.$refs.showTip.resetTips();
            } else {
                let response = await this.$api.country.showCountryLike(e.target.value);
                console.log(response);
                await this.$refs.showTip.showTipsByCountries(response.data);
            }
        },
        async siteSearchEnter(e) {
            if (e.target.value === "") {
                this.$refs.showTip.resetTips();
            } else {
                let response = await this.$api.site.showSiteLike(e.target.value);
                await this.$refs.showTip.showTipsBySites(response.data);
            }
        },
    }
};
</script>

<style scoped>
</style>