<template>
  <div>
    <form class="form-signin" @submit.prevent="login">
      <h2>Sign In</h2>
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
        placeholder="username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required
      />
    </form> <br>

    <div class="footer">
      <router-link :to="{ name: 'register' }" class="register" v-if="!this.$route.query.registration">Register</router-link> <br>
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

form {
  background-color: #6b6a66;
  display:flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: flix-start;
  flex-basis: 200px;

  text-align: center;
  line-height: 200%;
  padding: 2em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 12%;
  
  border: 5px solid #b7ec8c80;
  border-top-color: rgba(0, 255, 255, 0.493);
  border-bottom-color: rgba(127, 255, 212, 0.514);
}
h1 {
  align-items: center;
  align-self: auto;
}
h2{
  color: rgba(255, 235, 205, 0.534);
  text-shadow: black;
}
input {
  background: rgba(127, 255, 212, 0.514);
  border: .5px solid rgba(255, 235, 205, 0.534);
  text-align: center; 
  margin-top: 3px;
  max-width: 7em;
  border-radius: 10%;

}
button {
  background: rgba(0, 255, 255, 0.493);
  border: .5px solid rgba(0, 255, 255, 0.493);
  /* border: .5px solid rgba(255, 235, 205, 0.534)  */
  border-radius: 10%;
  max-width: 7em;
}
a.register{
  color: rgba(255, 235, 205, 0.534);
  text-decoration:none;
  font-size: .7em;
}


 /* .window {
  display:flex;
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: light;
  flex-direction: column;
  align-items: stretch;
  position: relative;
  margin: 15%;
  max-width: 15em;
  min-width: 11em;
}  */
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