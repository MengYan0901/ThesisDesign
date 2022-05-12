<template>
    <div class="main">
        <form>
            <v-text-field v-model="name"
                          :error-messages="nameErrors"
                          label="Name"
                          required
                          @input="$v.name.$touch()"
                          @blur="$v.name.$touch()"></v-text-field>
            <v-text-field v-model="password"
                          :error-messages="passwordErrors"
                          :counter="10"
                          label="Password"
                          required
                          type="password"
                          @input="$v.password.$touch()"
                          @blur="$v.password.$touch()"></v-text-field>
            <v-btn class="mr-4"
                   @click="submit"> submit </v-btn>
            <v-btn class="mr-5"
                   @click="clear"> clear </v-btn>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
import { validationMixin } from "vuelidate";
import { required, maxLength } from "vuelidate/lib/validators";

export default {
    name: "SignIn",
    mixins: [validationMixin],

    validations: {
        name: { required },
        password: { required, maxLength: maxLength(10) }
    },

    data: () => ({
        name: "",
        password: ""
    }),
    computed: {
        nameErrors() {
            const errors = [];
            if (!this.$v.name.$dirty) return errors;
            !this.$v.name.required && errors.push("Name is required.");
            return errors;
        },
        passwordErrors() {
            const errors = [];
            if (!this.$v.password.$dirty) return errors;
            !this.$v.password.maxLength &&
                errors.push("Password must be at most 10 characters long");
            !this.$v.password.required && errors.push("Password is required.");
            return errors;
        }
    },
    created: function () {
        if (this.$cookies.get("token") != null) {
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
    },
    methods: {
        submit() {
            this.$v.$touch();
            this.$api.login
                .login({ userName: this.name, userPassword: this.password })
                .then(res => {
                    console.log(res);
                    if (res.data == 0) {
                        window.alert("Log In Failed!");
                    } else {
                        console.log(res.data.jwttoken);
                        this.$cookies.set("userId", res.data.userId);
                        this.$cookies.set("token", res.data.jwttoken);
                        this.$cookies.set("userName", this.name);
                        this.$cookies.set("userEmail", res.data.userEmail);
                        this.$cookies.set("userAdmin", res.data.admin);
                        this.$store.commit('setuser', this.$cookies.get("token"));
                        axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
                        console.log(this.$cookies.get("userEmail"));
                        if (res.data.admin == 1) {
                            window.alert("The Admin Log In Successfully!");
                            this.$router.push({ name: "Admin" });
                            console.log(this.$cookies.get("token"));
                        } else {
                            window.alert("The User Log In Successfully!");
                            this.$router.push({ name: "User" });
                            console.log(this.$cookies.get("token"));
                        }
                    }
                });
            this.$api.user.showUser(this.name).then(res => {
                this.$cookies.set("userAddress", res.data.userAddress);
                this.$cookies.set("userFavorite", res.data.userFavorite);

            });
        },
        clear() {
            this.$v.$reset();
            this.name = "";
            this.password = "";
        }
    }
};
</script>

<style scoped>
.main {
    background-color: rgb(255, 255, 255);
    margin: auto;
    position: absolute;
    width: 350px;
    height: 500px;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
</style>