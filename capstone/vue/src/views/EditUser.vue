<template>
  <div>
    <form class="form-register" @submit.prevent="register">
      <h2 class="h3 mb-3 font-weight-normal">Edit User... <br> but not really</h2>
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
        type="password"
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="confirm Password"
        v-model="user.confirmPassword"
        required />
    <div class="footer">
        <button class="btn btn-lg btn-primary btn-block" type="submit" @submit.prevent="register">Submit</button>
    </div>
    </form>


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
            console.log(error);
            const response = error.response;
            console.log(response);
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
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
form {
  background-color: #3a2e34;
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
  margin-bottom: 1em;
  
  border-radius: 12%;
  /* border: 5px solid #b7ec8c80;
  border-top-color: rgba(0, 255, 255, 0.493);
  border-bottom-color: rgba(127, 255, 212, 0.514); */
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
  background: #51434a;
  border: .5px solid #51434a;
  text-align: center; 
  margin-top: 3px;
  max-width: 10em;
  border-radius: 10%;
  color: rgba(255, 235, 205, 0.534);
}
button {
  background: #5d7065;
  border: .5px solid #5d7065;
  /* border: .5px solid rgba(255, 235, 205, 0.534)  */
  border-radius: 10%;
  max-width: 10em;
}
a.login{
  color: rgba(255, 235, 205, 0.534);
  text-decoration:none;
  font-size: .7em;
}

/* .form-register {
  grid-area: register;
  display: flex;
  background-color: rgb(184, 119, 184);
  align-items: center;
  flex-direction: column;
  margin-right: 25%;
  margin-left: 25%;
  border-radius: 25px;
  opacity: .90;
  padding-bottom: 25px;
}
.btn {
  grid-area: submit;
  margin-top: 15px;
} */
</style>
