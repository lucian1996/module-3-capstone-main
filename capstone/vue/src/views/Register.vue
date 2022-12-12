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
        type="submit" @submit.prevent="register"> Create Account </button>
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
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
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

<style scoped>
input {
  background: #51434a;
  border: 0.5px solid #51434a;
  text-align: center;
  margin-top: 3px;
  max-width: 10em;
  border-radius: 10%;
  color: rgba(255, 235, 205, 0.534);
}
a.login {
  color: rgba(255, 235, 205, 0.534);
  text-decoration: none;
  font-size: 0.7em;
}
button {
  max-width: 10em;
}
</style>
