<template>
  <div>
    <v-container fluid style="height: 300px" class="my-2">
      <v-row justify="end">
        <v-menu bottom min-width="150px" rounded offset-y>
          <template v-slot:activator="{ on }">
            <v-btn icon x-large v-on="on">
              <v-avatar color="brown" size="48">
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
                <v-btn depressed rounded text> Edit Account </v-btn>
                <v-divider class="my-3"></v-divider>
                <v-btn depressed rounded text> Disconnect </v-btn>
              </div>
            </v-list-item-content>
          </v-card>
        </v-menu>
      </v-row>
      <h1 align="center">Welcome, {{ user.userName }} !</h1>
      <v-container>
        <v-row>
          <v-col cols="12">
            <v-text-field
              label="UserName*"
              v-model="user.userName"
              required
              disabled
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              label="UserEmail*"
              v-model="user.userEmail"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              label="UserAddress"
              v-model="user.userAddress"
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-text-field
              label="UserFavorite"
              v-model="user.userFavorite"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
      <small>* indicates required field</small>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-1" text @click="updateUser">
        Update
      </v-btn>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "User.vue",
  data: () => ({
    user: {
      userName: "XX",
      userEmail: "XX",
      userAddress: "XX",
      userFavorite: "XX"
    },
    initials: "XX"
  }),
  created: function() {
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
    }
  }
};
</script>

<style scoped>
</style>