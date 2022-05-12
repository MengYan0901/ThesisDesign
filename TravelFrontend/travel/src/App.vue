<template>
    <v-app>
        <v-app-bar app
                   color="primary"
                   dark>
            <div class="align-center">
                <v-img max-height="65"
                       max-width="65"
                       :src="logo" />
            </div>

            <router-link to="/">
                <v-btn text>
                    <span class="title">Fun Travel</span>
                </v-btn>
            </router-link>

            <v-spacer></v-spacer>

            <router-link to="/country">
                <v-btn text>
                    <span class="mr-2">Country</span>
                </v-btn>
            </router-link>
            <router-link to="/site">
                <v-btn text>
                    <span class="mr-2">Site</span>
                </v-btn>
            </router-link>
            <router-link to="/tip">
                <v-btn text>
                    <span class="mr-2">Tip</span>
                </v-btn>
            </router-link>
            <router-link to="/ticket">
                <v-btn text>
                    <span class="mr-2">Ticket</span>
                </v-btn>
            </router-link>
            <router-link to="/route">
                <v-btn text>
                    <span class="mr-2">route</span>
                </v-btn>
            </router-link>

            <v-spacer></v-spacer>

            <div length="150px">
                <v-text-field label="Search Country/Site"
                              hide-details
                              prepend-inner-icon="mdi-magnify"
                              single-line
                              @keyup.enter="searchEnter"></v-text-field>
            </div>

            <router-link to="/signin"
                         v-show="$cookies.get('userName') == null">
                <v-btn text>
                    <span class="mr-2">Sign In</span>
                    <v-icon>mdi-open-in-new</v-icon>
                </v-btn>
            </router-link>

            <router-link to="/signup"
                         v-show="$cookies.get('userName') == null">
                <v-btn text>
                    <span class="mr-2">Sign Up</span>
                    <v-icon>mdi-open-in-new</v-icon>
                </v-btn>
            </router-link>

            <v-btn v-show="$cookies.get('userName') !== null"
                   text
                   @click="dialog = true">
                <span class="mr-2">Sign Out</span>
                <v-icon>mdi-open-in-new</v-icon>
            </v-btn>

            <v-btn text
                   v-show="$cookies.get('userName') !== null"
                   @click="toUserorAdmin()">
                <span class="mr-2">{{$cookies.get('userName')}}</span>
                <v-icon>mdi-open-in-new</v-icon>
            </v-btn>

            <v-dialog v-model="dialog"
                      width="500">
                <v-card>
                    <v-card-title class="text-h5 grey lighten-2">
                        You want to SIGN OUT?
                    </v-card-title>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary"
                               text
                               @click="signOut()">
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
        </v-app-bar>

        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>
export default {
    name: "App",

    components: {},

    data: function () {
        return {
            logo: require("./assets/images/travel.png"),
            search: "",
            dialog: false,
        };
    },
    created: function () {
    },
    methods: {
        searchEnter: function (e) {
            console.log("回车搜索", e.target.value);
            this.$router.push({ path: '/search/' + e.target.value });
        },
        signOut: function () {
            this.$cookies.remove("token");
            this.$cookies.remove("userName");
            this.$cookies.remove("userEmail");
            this.$cookies.remove("userAddress");
            this.$cookies.remove("userFavorite");
            this.$store.commit('setuser', '');
            this.dialog = false;
        },
        toUserorAdmin: function () {
            if (this.$cookies.get("userAdmin") == 1) {
                this.$router.push({ name: "Admin" });
            } else {
                this.$router.push({ name: "User" });
            }
        }
    },
};
</script>

<style scoped>
.title {
    size: 60;
}
</style>
