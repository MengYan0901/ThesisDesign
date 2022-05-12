<template>
    <div>
        <v-card>
            <v-tabs color="deep-purple accent-4"
                    right>
                <v-tab>Northern Europe</v-tab>
                <v-tab>Western Europe</v-tab>
                <v-tab>Central Europe</v-tab>
                <v-tab>Southern Europe</v-tab>
                <v-tab>Eastern Europe</v-tab>

                <v-tab-item v-for="n in 5"
                            :key="n">
                    <v-container fluid>
                        <v-row>
                            <v-col v-for="i in 6"
                                   :key="i"
                                   cols="12"
                                   md="4">
                                <v-img :src="`http://localhost:8081/travel/images/site/${n}.${i+6*(n-1)}.1.png`"
                                       :lazy-src="`http://localhost:8081/travel/images/site/${n}.${i+6*(n-1)}.1.png`"
                                       aspect-ratio="1"></v-img>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-tab-item>
            </v-tabs>
        </v-card>
    </div>
</template>

<script>
export default {
    name: "Home.vue",

    created: function () {
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    }
};
</script>

<style scoped>
.images {
    margin: 0 auto;
    height: 30px;
    width: 70%;
}
</style>
