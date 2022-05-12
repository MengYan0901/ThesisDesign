<template>
    <div>
        <v-card v-for="data in dataList">
            <div v-if="code === 0">
                <h1>Sorry, not find!</h1>
            </div>

            <div>
                <v-card class="mx-auto my-12"
                        max-width="374"
                        v-if="code === 1 && data.countryName !== null">
                    <template slot="progress">
                        <v-progress-linear color="deep-purple"
                                           height="10"
                                           indeterminate></v-progress-linear>
                    </template>

                    <v-img height="250"
                           :src="`http://localhost:8081/travel/images${data.countryPhoto}`"></v-img>

                    <v-card-title>{{ data.countryName }}</v-card-title>

                    <v-card-text>
                        <v-row align="center"
                               class="mx-0"> </v-row>
                        <div class="my-4 subtitle-1">{{ data.countryAddress }}</div>
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
                <v-card class="mx-auto my-12"
                        max-width="374"
                        v-if="code === 1 && data.siteName !== null">
                    <template slot="progress">
                        <v-progress-linear color="deep-purple"
                                           height="10"
                                           indeterminate></v-progress-linear>
                    </template>

                    <v-img height="250"
                           :src="`http://localhost:8081/travel/images${data.sitePhoto}`"></v-img>

                    <v-card-title>{{ data.siteName }}</v-card-title>

                    <v-card-text>
                        <v-row align="center"
                               class="mx-0"> </v-row>
                        <div class="my-4 subtitle-1">{{ data.siteAddress }}</div>
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
            </div>
        </v-card>
    </div>
</template>

<script>
export default {
    name: "Search.vue",
    props: ['query'],
    data: function () {
        return {
            dataList: [],
            code: 0,
            // countryAddress: this.data.countryAddress,
            // countryName: this.data.countryName,
            // countryPhoto: this.data.countryPhoto,
            // siteAddress: this.data.siteAddress,
            // siteName: this.data.siteName,
            // sitePhoto: this.data.sitePhoto,
        };
    },
    created: function () {
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
        this.search();
    },
    watch: {
        $route(to, from) {
            this.search();
        }
    },
    methods: {
        search: function () {
            this.$api.search.search(this.query).then((res) => {
                console.log(res.data);
                this.dataList = res.data;
                this.code = res.data[0].code;
            });
        }
    },
};
</script>

<style scoped>
</style>