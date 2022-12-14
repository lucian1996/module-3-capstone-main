<template>
  <div>
    <div> <br> </div>
    <h1>Fridgrr</h1>
    <form class="form-register" @submit.prevent="register">
      <h2 class="h3 mb-3 font-weight-normal">Create Account</h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="username"
        v-model="user.username"
        required
        autofocus />
      <label for="password" class="sr-only"></label>
      <input
        type=""
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required />
      <input
        type=""
        id="confirmPassword"
        class="form-control"
        placeholder="confirm Password"
        v-model="user.confirmPassword"
        required />
    </form>
    <div class="footer">
      <br />
      <router-link :to="{ name: 'login' }" class="login"
        >Have an account?</router-link> <br>
      <button
        class="btn btn-lg btn-primary btn-block"
        type="submit" @click.prevent="register"> Create Account </button>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.use.username.length <= 0) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Username field cannot be blank.";
      } 
       if (this.use.username.length > 36) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Username cannot be more than 36 characters.";
      } 
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password and confirmation must be identical.";
      } 
       if (this.user.password.length <= 0) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password field cannot be blank.";
      } else {
        console.log(
        "In register > register",
        this.user.username,
        this.user.password
      );
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = error.response.data.message;
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

