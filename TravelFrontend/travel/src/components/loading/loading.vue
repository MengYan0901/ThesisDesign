<template>
    <div class="text-center">
        <v-progress-circular indeterminate
                             color="primary"></v-progress-circular>

        <v-progress-circular indeterminate
                             color="red"></v-progress-circular>

        <v-progress-circular indeterminate
                             color="amber"></v-progress-circular>

        <v-progress-circular indeterminate
                             color="green"></v-progress-circular>

        <v-progress-circular indeterminate
                             color="purple"></v-progress-circular>
    </div>
</template>

<script>
export default {
    name: 'loading',
    data() {
        return {
        }
    },
    created: function () {
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    }
}
</script>

<style scoped>
.v-progress-circular {
    margin: 1rem;
}
</style>