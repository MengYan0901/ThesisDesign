<template>
    <div class="main">
        <form>
            <v-text-field v-model="name"
                          :error-messages="nameErrors"
                          label="Name"
                          required
                          @input="$v.name.$touch()"
                          @blur="$v.name.$touch()"></v-text-field>

            <v-text-field v-model="email"
                          :error-messages="emailErrors"
                          label="E-mail"
                          required
                          @input="$v.email.$touch()"
                          @blur="$v.email.$touch()"></v-text-field>
            <v-text-field v-model="password"
                          :error-messages="passwordErrors"
                          :counter="10"
                          label="Password"
                          required
                          type="password"
                          @input="$v.password.$touch()"
                          @blur="$v.password.$touch()"></v-text-field>
            <v-checkbox v-model="checkbox"
                        :error-messages="checkboxErrors"
                        label="Do you agree?"
                        required
                        @change="$v.checkbox.$touch()"
                        @blur="$v.checkbox.$touch()"></v-checkbox>
            <v-btn block
                   class="mr-4"
                   @click="create"> Create Account </v-btn>
        </form>
    </div>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, maxLength, email } from "vuelidate/lib/validators";

export default {
    name: "SignUp",
    mixins: [validationMixin],

    validations: {
        name: { required },
        email: { required, email },
        password: { required, maxLength: maxLength(10) },
        checkbox: {
            checked(val) {
                return val;
            },
        },
    },

    data: () => ({
        name: "",
        email: "",
        password: "",
        checkbox: false,
    }),
    created: function () {
    },
    computed: {
        nameErrors() {
            const errors = [];
            if (!this.$v.name.$dirty) return errors;
            !this.$v.name.required && errors.push("Name is required.");
            return errors;
        },
        emailErrors() {
            const errors = [];
            if (!this.$v.email.$dirty) return errors;
            !this.$v.email.email && errors.push("Must be valid e-mail");
            !this.$v.email.required && errors.push("E-mail is required");
            return errors;
        },
        passwordErrors() {
            const errors = [];
            if (!this.$v.password.$dirty) return errors;
            !this.$v.password.maxLength &&
                errors.push("Password must be at most 10 characters long");
            !this.$v.password.required && errors.push("Password is required.");
            return errors;
        },
        checkboxErrors() {
            const errors = [];
            if (!this.$v.checkbox.$dirty) return errors;
            !this.$v.checkbox.checked && errors.push("You must agree to continue!");
            return errors;
        },
    },
    methods: {
        async create() {
            this.$v.$touch();
            let response = await this.$api.register.username(this.name);
            if (parseInt(response.data.code) === 1) {
                window.alert("username is used!");
                return;
            } else {
                window.alert("Register Successfully!");
                this.$router.push({ name: 'SignIn' });
            }
            response = await this.$api.register.register({
                userName: this.name,
                userPassword: this.password,
                userEmail: this.email,
            });
        },
    },
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

