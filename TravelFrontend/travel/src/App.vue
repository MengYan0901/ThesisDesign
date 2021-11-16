<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="align-center">
        <v-img max-height="65" max-width="65" :src="logo" />
      </div>

      <router-link to="/">
        <v-btn text>
          <span class="title">Fun Travel</span>
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
      

      <v-spacer></v-spacer>

      <div length="150px">
        <v-text-field
          label="Search Country/Site"
          hide-details
          prepend-inner-icon="mdi-magnify"
          single-line
          @keyup.enter="searchEnter"
        ></v-text-field>
      </div>

      <router-link to="/signin">
        <v-btn text>
          <span class="mr-2">Sign In</span>
          <v-icon>mdi-open-in-new</v-icon>
        </v-btn>
      </router-link>

      <router-link to="/signup">
        <v-btn text>
          <span class="mr-2">Sign Up</span>
          <v-icon>mdi-open-in-new</v-icon>
        </v-btn>
      </router-link>
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

  data: () => ({
    logo: require("./assets/images/travel.png"),
    search: "",
  }),
  methods: {
    searchEnter: function (e) {
      console.log("回车搜索",e.target.value);
      this.$api.search.search(e.target.value).then((res)=>{console.log(res.data);
      this.$cookies.set("search",res.data);
      this.$router.push({ name: 'Search'});
      });
    },
  },
};
</script>

<style scoped>
.title {
  size: 60;
}
</style>
