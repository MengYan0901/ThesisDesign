<template>
    <div>

        <div v-if="code === 0">
            <h1>Sorry, not find!</h1>
        </div>

        <div v-if="code === 1 && countryName !== null">
            <v-card class="mx-auto my-12"
                    max-width="374">
                <template slot="progress">
                    <v-progress-linear color="deep-purple"
                                       height="10"
                                       indeterminate></v-progress-linear>
                </template>

                <v-img height="250"
                       :src="`http://localhost:8081/travel/images${countryPhoto}`"></v-img>

                <v-card-title>{{ countryName }}</v-card-title>

                <v-card-text>
                    <v-row align="center"
                           class="mx-0"> </v-row>
                    <div class="my-4 subtitle-1">{{ countryAddress }}</div>
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

        <div v-if="code === 1 && siteName !== null">
            <v-card class="mx-auto my-12"
                    max-width="374">
                <template slot="progress">
                    <v-progress-linear color="deep-purple"
                                       height="10"
                                       indeterminate></v-progress-linear>
                </template>

                <v-img height="250"
                       :src="`http://localhost:8081/travel/images${sitePhoto}`"></v-img>

                <v-card-title>{{ siteName }}</v-card-title>

                <v-card-text>
                    <v-row align="center"
                           class="mx-0"> </v-row>
                    <div class="my-4 subtitle-1">{{ siteAddress }}</div>
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
    </div>
</template>

<script>
export default {
    name: "Site.vue",
    data: () => ({
        code: $cookies.get("search").code,
        countryAddress: $cookies.get("search").countryAddress,
        countryName: $cookies.get("search").countryName,
        countryPhoto: $cookies.get("search").countryPhoto,
        siteAddress: $cookies.get("search").siteAddress,
        siteName: $cookies.get("search").siteName,
        sitePhoto: $cookies.get("search").sitePhoto,
    }),

    methods: {},
};
</script>

<style scoped>
</style>