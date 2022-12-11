<template>
  <div>
    <div> <br> </div>
    <h1>Fridgrr</h1>
    <form class="form-signin" @submit.prevent="login">
      <h2>Sign In</h2>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
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
        type="password"
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required />
      <button type="submit" v-show="false"></button>
    </form>
    <br />

    <div class="footer">
      <router-link
        :to="{ name: 'register' }"
        class="register"
        v-if="!this.$route.query.registration"
        >Register</router-link
      >
      <br />
      <button type="submit" class="btn" @click.prevent="login">Sign in</button>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
input {
  background: #51434a;
  border: 0.5px solid #3a2e34;
  /* border: .5px solid rgba(255, 235, 205, 0.534); */
  text-align: center;
  margin-top: 3px;
  max-width: 7em;
  border-radius: 10%;
  color: rgba(255, 235, 205, 0.404);
}
a.register {
  color: rgba(255, 235, 205, 0.534);
  text-decoration: none;
  font-size: 0.7em;
}
</style>
