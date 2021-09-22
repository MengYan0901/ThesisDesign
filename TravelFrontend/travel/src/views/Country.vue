<template>
  <div>
    <v-layout row wrap>
      <v-card class="mx-6 my-12" max-width="350" v-for="country in countryList">
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>

        <v-img
          height="250"
          :src="imagebase+country.countryPhoto"
        ></v-img>

        <v-card-title>{{country.countryName}}</v-card-title>

        <v-card-text>
          <v-row align="center" class="mx-0"> </v-row>
          <div class="my-4 subtitle-1">{{country.countryAddress}}</div>
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
  name: "Country.vue",
  data: function() {
    return { 
      countryList: [],
      imagebase: axios.defaults.baseURL+'/images'
      };
  },
  created: function() {
    this.$api.country.showCountryList().then(res => {
      console.log(res);
      this.countryList = res.data;
    });
  },
  methods: {}
};
</script>

<style scoped>
</style>
