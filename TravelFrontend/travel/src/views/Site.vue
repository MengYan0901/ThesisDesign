<template>
  <div>
    <v-layout row wrap>
      <v-card class="mx-6 my-12" max-width="350" v-for="site in siteList">
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>

        <v-img
          height="250"
          :src="imagebase+site.sitePhoto"
        ></v-img>

        <v-card-title>{{site.siteName}}</v-card-title>

        <v-card-text>
          <v-row align="center" class="mx-0"> </v-row>
          <div class="my-4 subtitle-1">{{site.siteAddress}}</div>
        </v-card-text>

        <v-card-actions>
          <v-btn color="deep-purple accent-4" outlined rounded text>
            <span>Favorites</span>
            <v-icon>mdi-heart</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-layout>
  </div>
</template>

<script>
import axios from '../api/axios';

export default {
  name: "Site.vue",

  data: function() {
    return { 
      siteList: [],
      imagebase: axios.defaults.baseURL+'/images'
      };
  },
  created: function() {
    this.$api.site.showSiteList().then(res => {
      console.log(res);
      this.siteList = res.data;
    });
  },
  methods: {}
};
</script>

<style scoped>
</style>