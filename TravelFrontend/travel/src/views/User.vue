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
            <v-btn block
                   class="my-3"
                   @click="list"> List Your Tips </v-btn>
            <table class="table table-bodered table-hover table-striped">
                <thead>
                    <tr>
                        <th>tipId</th>
                        <th>tipName</th>
                        <th>tipContent</th>
                        <th>tipMark</th>
                        <th>tipPhoto</th>
                        <th>siteName</th>
                        <th>countryName</th>
                        <th>Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in tipList"
                        :key="item.tipId">
                        <td>{{ item.tipId }}</td>
                        <td>{{ item.tipName }}</td>
                        <td>{{ item.tipContent }}</td>
                        <td>{{ item.tipMark }}</td>
                        <td>{{ item.tipPhoto }}</td>
                        <td>{{ item.siteName }}</td>
                        <td>{{ item.countryName }}</td>
                        <td>
                            <v-btn @click="edit(item)">Edit</v-btn>
                            <v-btn @click="deleteTip(item)">Delete</v-btn>
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
                                        <v-text-field label="TipId*"
                                                      v-model="selectedTip.tipId"
                                                      required></v-text-field>
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
                                   @click="dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1"
                                   text
                                   @click="save">
                                Save
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
            <v-btn block
                   class="my-3"
                   @click="add"> Add Your Tips </v-btn>
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
        id: 1,
        dialog: false,
        dialogEditMode: false,
    }),
    created: function () {
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
        }
    },
    methods: {
        updateUser() {
            this.$api.user.updateUser(this.user).then(res => {
                console.log(res);
                window.alert("Update the User Successfully!");
            });
        },
        list() {
            this.$api.user.showUser(this.user.userName).then(res => {
                console.log(res.data.userId);
                this.user.userId = res.data.userId;
                this.$api.tip.searchTipByUserId(this.user.userId).then(res => {
                    console.log(res.data);
                    this.tipList = res.data;
                });
            })
        },
        edit(item) {
            this.dialog = !this.dialog;
            this.dialogEditMode = true;
            this.selectedTip = Object.assign({}, item);
        },
        close() {
            this.dialog = false;
            this.selectedTip = {};
        },
        save() {
            if (this.dialogEditMode) {
                this.$api.tip.updateTip(this.selectedTip).then(res => {
                    console.log(res);
                    this.$api.tip.searchTipByUserId(this.user.userId).then(res => {
                        console.log(res.data);
                        this.tipList = res.data;
                    });
                });
            }
            else {
                this.selectedTip.userId = this.user.userId;
                this.$api.tip.addTip(this.selectedTip).then(res => {
                    console.log(res);
                });
            }
            this.dialog = false;
            this.selectedTip = {};
        },
        deleteTip(item) {
            this.id = item.tipId;
            this.$api.tip.deleteTip(this.id).then(res => {
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


    }
};
</script>

<style scoped>
</style>