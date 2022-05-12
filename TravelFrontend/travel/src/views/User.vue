<template>
    <div>
        <v-container fluid
                     style="height: 300px"
                     class="my-2">
            <v-row justify="end">
                <v-menu bottom
                        min-width="150px"
                        rounded
                        offset-y>
                    <template v-slot:activator="{ on }">
                        <v-btn icon
                               x-large
                               v-on="on">
                            <v-avatar color="brown"
                                      size="48">
                                <span class="white--text headline">{{ initials }}</span>
                            </v-avatar>
                        </v-btn>
                    </template>
                    <v-card>
                        <v-list-item-content class="justify-center">
                            <div class="mx-auto text-center">
                                <v-avatar color="brown">
                                    <span class="white--text headline">{{ initials }}</span>
                                </v-avatar>
                                <h3>{{ user.userName }}</h3>
                                <p class="caption mt-1">
                                    {{ user.userEmail }}
                                </p>
                                <v-divider class="my-3"></v-divider>
                                <v-btn depressed
                                       rounded
                                       text> Edit Account </v-btn>
                                <v-divider class="my-3"></v-divider>
                                <v-btn depressed
                                       rounded
                                       text> Disconnect </v-btn>
                            </div>
                        </v-list-item-content>
                    </v-card>
                </v-menu>
            </v-row>
            <h1 align="center">Welcome, {{ user.userName }} !</h1>
            <v-container>
                <v-row>
                    <v-col cols="12">
                        <v-text-field label="UserName*"
                                      v-model="user.userName"
                                      required
                                      disabled></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-text-field label="UserEmail*"
                                      v-model="user.userEmail"
                                      required></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-text-field label="UserAddress"
                                      v-model="user.userAddress"></v-text-field>
                    </v-col>
                    <v-col cols="12">
                        <v-text-field label="UserFavorite"
                                      v-model="user.userFavorite"></v-text-field>
                    </v-col>
                </v-row>
                <small>* indicates required field</small>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1"
                       text
                       @click="updateUser">
                    Update
                </v-btn>
            </v-container>
            <v-container>
                <v-row>
                    <v-col>
                        <v-btn text
                               block
                               class="my-3"
                               @click="list">
                            <v-icon large>mdi-credit-card-outline</v-icon>
                            Show Tips
                        </v-btn>
                    </v-col>
                    <v-col>
                        <v-btn text
                               block
                               class="my-3"
                               @click="add">
                            <v-icon large>mdi-credit-card-plus-outline</v-icon>
                            add Tips
                        </v-btn>
                    </v-col>
                </v-row>
                <table class="table table-bodered table-hover table-striped"
                       v-show=this.tableShow>
                    <thead>
                        <tr>
                            <th>tipId</th>
                            <th>tipName</th>
                            <th>tipContent</th>
                            <th>tipPhoto</th>
                            <th>countryId</th>
                            <th>cityId</th>
                            <th>siteId</th>
                            <th>Operation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in tipList"
                            :key="item.tipId">
                            <td>{{ item.tipId }}</td>
                            <td>{{ item.tipName }}</td>
                            <td>{{ item.tipContent }}</td>
                            <td>{{ item.tipPhoto }}</td>
                            <td>{{ item.countryId}}</td>
                            <td>{{ item.cityId}}</td>
                            <td>{{ item.siteId}}</td>

                            <td>
                                <v-btn text
                                       @click="edit(item)">
                                    <v-icon>mdi-credit-card-edit-outline</v-icon>
                                </v-btn>
                                <v-btn text
                                       @click="deleteTip(item)">
                                    <v-icon>mdi-credit-card-remove-outline</v-icon>
                                </v-btn>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <v-row justify="center">
                    <v-dialog v-model="dialog"
                              persistent
                              max-width="600px">
                        <v-card>
                            <v-card-title>
                                <span v-if="dialogEditMode"
                                      class="headline">Edite Tip</span>
                                <span v-else
                                      class="headline">Add Tip</span>
                            </v-card-title>
                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12">
                                            <v-text-field label="UserId*"
                                                          v-model="user.userId"
                                                          required
                                                          disabled></v-text-field>
                                        </v-col>
                                        <v-col cols="12">
                                            <v-text-field label="TipName*"
                                                          v-model="selectedTip.tipName"
                                                          required></v-text-field>
                                        </v-col>
                                        <v-col cols="12">
                                            <v-text-field label="TipContent*"
                                                          v-model="selectedTip.tipContent"
                                                          required></v-text-field>
                                        </v-col>
                                        <v-col cols="12">
                                            <v-autocomplete v-model="countryName"
                                                            :items="countryItems"
                                                            outlined
                                                            dense
                                                            chips
                                                            small-chips
                                                            label="CountryId*"></v-autocomplete>
                                        </v-col>
                                        <v-col cols="12">
                                            <v-autocomplete v-model="cityName"
                                                            :items="cityItems"
                                                            outlined
                                                            dense
                                                            chips
                                                            small-chips
                                                            label="CityId*"></v-autocomplete>
                                        </v-col>
                                        <v-col cols="12">
                                            <v-autocomplete v-model="siteName"
                                                            :items="siteItems"
                                                            outlined
                                                            dense
                                                            chips
                                                            small-chips
                                                            label="SiteId*"></v-autocomplete>
                                        </v-col>
                                        <v-col cols="12">
                                            <v-text-field label="TipPhoto"
                                                          v-model="selectedTip.tipPhoto"></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-container>
                                <small>* indicates required field</small>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1"
                                       text
                                       @click="close()">
                                    Close
                                </v-btn>
                                <v-btn color="blue darken-1"
                                       text
                                       @click="save()">
                                    Save
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-row>
            </v-container>
        </v-container>
    </div>
</template>

<script>
export default {
    name: "User.vue",
    data: () => ({
        user: {},
        initials: "XX",
        tipList: [],
        selectedTip: {},
        dialog: false,
        dialogEditMode: false,
        tableShow: false,
        countryItems: [],
        cityItems: [],
        siteItems: [],
        countryName: "Hungary",
        cityName: "Budapest",
        siteName: "Hungarian Parliament Building",
        countryId: 1,
        cityId: 1,
        siteId: 1,


    }),
    created: function () {
        this.user.userId = this.$cookies.get("userId");
        let username = this.$cookies.get("userName");
        this.user.userEmail = this.$cookies.get("userEmail");
        this.user.userAddress = this.$cookies.get("userAddress");
        this.user.userFavorite = this.$cookies.get("userFavorite");
        if (username && username.length < 2) {
            this.initials = username.slice(0, 1);
            this.user.userName = username;
        } else {
            this.initials = username.slice(0, 2);
            this.user.userName = username;
        };
        console.log(this.user);
        this.showCountryNameList();
        this.showCityNameList();
        this.showSiteNameList();
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    },
    methods: {
        updateUser() {
            this.$api.user.updateUser(this.user).then(res => {
                console.log(res);
                window.alert("Update the User Successfully!");
            });
        },
        list() {
            this.tableShow = true;
            this.$api.tip.searchTipByUserId(this.user.userId).then(res => {
                console.log(res.data);
                this.tipList = res.data;
            });
        },
        async edit(item) {
            this.dialog = !this.dialog;
            this.dialogEditMode = true;
            this.selectedTip = Object.assign({}, item);
            await this.showCountryName(this.selectedTip.countryId);
            await this.showCityName(this.selectedTip.cityId);
            await this.showSiteName(this.selectedTip.siteId);
        },
        close() {
            this.selectedTip = {};
            this.dialog = false;
        },
        async save() {
            this.selectedTip.userId = this.user.userId;
            if (this.dialogEditMode) {
                await this.showCountryId();
                await this.showCityId();
                await this.showSiteId();
                this.selectedTip.countryId = this.countryId;
                this.selectedTip.cityId = this.cityId;
                this.selectedTip.siteId = this.siteId;
                let response1 = await this.$api.tip.updateTip(this.selectedTip);
                let response2 = await this.$api.tip.searchTipByUserId(this.user.userId);
                this.tipList = response2.data;
            }
            else {
                await this.showCountryId();
                await this.showCityId();
                await this.showSiteId();
                this.selectedTip.countryId = this.countryId;
                this.selectedTip.cityId = this.cityId;
                this.selectedTip.siteId = this.siteId;
                console.log(this.selectedTip);
                await this.$api.tip.addTip(this.selectedTip);
                let response2 = await this.$api.tip.searchTipByUserId(this.user.userId);
                this.tipList = response2.data;
            }
            this.selectedTip = {};
            this.dialog = false;
        },
        deleteTip(item) {
            this.$api.tip.deleteTip(item.tipId).then(res => {
                console.log(res);
                window.alert("Delete the Tip successfully!");
                this.$api.tip.searchTipByUserId(this.user.userId).then(res => {
                    console.log(res.data);
                    this.tipList = res.data;
                });
            });
        },
        add() {
            this.dialog = true;
            this.dialogEditMode = false;
        },
        showCountryNameList() {
            this.$api.country.showCountryNameList().then(res => {
                this.countryItems = res.data;
            })
        },
        showCityNameList() {
            this.$api.city.showCityNameList().then(res => {
                this.cityItems = res.data;
            })
        },
        showSiteNameList() {
            this.$api.site.showSiteNameList().then(res => {
                this.siteItems = res.data;
            })
        },
        async showCountryId() {
            let response = await this.$api.country.showCountryIdByCountryName(this.countryName);
            this.countryId = response.data;
        },
        async showCityId() {
            let response = await this.$api.city.showCityIdByCityName(this.cityName);
            this.cityId = response.data;
        },
        async showSiteId() {
            let response = await this.$api.site.showSiteIdBySiteName(this.siteName);
            this.siteId = response.data;
        },
        async showCountryName(countryId) {
            let response = await this.$api.country.showCountryNameByCountryId(countryId);
            this.countryName = response.data;
        },
        async showCityName(cityId) {
            let response = await this.$api.city.showCityNameByCityId(cityId);
            this.cityName = response.data;
        },
        async showSiteName(siteId) {
            let response = await this.$api.site.showSiteNameBySiteId(siteId);
            this.siteName = response.data;
        },

    }
};
</script>

<style scoped>
</style>