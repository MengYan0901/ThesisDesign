<template>
    <div>
        <v-container fluid
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
                            <v-avatar color="purple darken-4"
                                      size="48">
                                <span class="white--text headline">{{ admin.initials }}</span>
                            </v-avatar>
                        </v-btn>
                    </template>
                    <v-card>
                        <v-list-item-content class="justify-center">
                            <div class="mx-auto text-center">
                                <v-avatar color="purple darken-4">
                                    <span class="white--text headline">{{ admin.initials }}</span>
                                </v-avatar>
                                <h3>{{ admin.fullName }}</h3>
                                <p class="caption mt-1">
                                    {{ admin.email }}
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
            <v-btn block
                   class="my-3"
                   @click="list"> List The Users </v-btn>
            <table class="table table-bodered table-hover table-striped">
                <thead>
                    <tr>
                        <th>userId</th>
                        <th>userName</th>
                        <th>userEmail</th>
                        <th>userAddress</th>
                        <th>userFavorite</th>
                        <th>userAdmin</th>
                        <th>Operation</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in userList"
                        :key="item.userId">
                        <td>{{ item.userId }}</td>
                        <td>{{ item.userName }}</td>
                        <td>{{ item.userEmail }}</td>
                        <td>{{ item.userAddress }}</td>
                        <td>{{ item.userFavorite }}</td>
                        <td>{{ item.userAdmin }}</td>
                        <td>
                            <v-btn @click="edit(item)">Edit</v-btn>
                            <v-btn @click="deleteUser(item)">Delete</v-btn>
                        </td>
                    </tr>
                </tbody>
            </table>
        </v-container>
        <v-row justify="center">
            <v-dialog v-model="dialog"
                      persistent
                      max-width="600px">
                <v-card>
                    <v-card-title>
                        <span class="headline">User Profile</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field label="UserName*"
                                                  v-model="selectedUser.userName"
                                                  required></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="UserEmail*"
                                                  v-model="selectedUser.userEmail"
                                                  required></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="UserAddress"
                                                  v-model="selectedUser.userAddress"></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="UserFavorite"
                                                  v-model="selectedUser.userFavorite"></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="UserAdmin"
                                                  v-model="selectedUser.userAdmin"></v-text-field>
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
    </div>
</template>

<script>
export default {
    name: "Admin.vue",
    data: () => ({
        admin: {
            initials: "XX",
            fullName: "XX",
            email: "XX"
        },
        userList: [],
        selectedUser: {},
        id: "",
        dialog: false
    }),
    created: function () {
        let adminName = this.$cookies.get("userName");
        let adminEmail = this.$cookies.get("userEmail");
        if (adminName && adminName.length < 2) {
            this.admin.initials = adminName.slice(0, 1);
            this.admin.fullName = adminName;
            this.admin.email = adminEmail;
        } else {
            this.admin.initials = adminName.slice(0, 2);
            this.admin.fullName = adminName;
            this.admin.email = adminEmail;
        };
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    },
    methods: {
        list() {
            this.$api.admin.showUserList().then(res => {
                this.userList = res.data;
            });
        },
        edit(item) {
            this.dialog = !this.dialog;
            this.selectedUser = item;
        },
        save() {
            this.$api.admin.updateUser(this.selectedUser).then(res => {
                console.log(res);
            });
            this.dialog = false;
        },
        deleteUser(item) {
            this.id = item.userId;
            this.$api.admin.deleteUser(this.id).then(res => {
                console.log(res);
                window.alert("Delete the User successfully!");
                this.$api.admin.showUserList().then(res => {
                    this.userList = res.data;
                });
            });
        }
    }
};
</script>

<style scoped>
</style>