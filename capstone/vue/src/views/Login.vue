<template>
  <div id="window" class="text-center">
    
    <form class="form-signin" @submit.prevent="login">
      <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" class="register" v-if="!this.$route.query.registration">Register</router-link>
      <button type="submit" class="btn" @click.prevent="login">Sign in</button>
    </form>

    <div>
     
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
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#window {
  display:flex;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: light;
  display: flex;
  height: 80vh;
  flex-direction: column;
  justify-content: first baseline;
  align-items: center;
  padding-top: 5%;
  padding-bottom: 5%;
  margin-bottom:  auto;
}

.form-signin {
  display:flex;
  flex-direction: column;
  text-align: center;
  line-height: 200%;
  background-color: #AAA599;
  border-radius: 18%;
  justify-content: space-around;
  padding: 5%;
  flex-basis: 200px;
}
/* .form-signin {
  grid-area: form;
  display: flex;
  background-color: rgb(184, 119, 184);
  align-items: center;
  flex-direction: column;
  margin-right: 25%;
  margin-left: 25%;
  border-radius: 25px;
  opacity: .90;
  padding-bottom: 20px;
}
.sr-only {
  align-content: center;
}
.register{
  display:flex;
  align-content: flex-end;
}
.submit {
 align-self:center
}
.btn {
  grid-area: login;
  justify-content: center;
  margin-top: 15px;
}
.text-center {
  display:grid;
  grid-template-areas:
  "form form form form form"
  "form form form form form"
  "form form form form form"
  "form form form form form"
  
} */
</style>