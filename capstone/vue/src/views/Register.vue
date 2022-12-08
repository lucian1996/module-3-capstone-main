<template>
  <div id="register" class="text-center">
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit" @submit.prevent="register">
        Create Account
      </button>
    </form>
  <div>
    
  </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            console.log(error)
            const response = error.response;
            console.log(response);
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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

.form-register {
  display:flex;
  flex-direction: column;
  text-align: center;
  line-height: 200%;
  background-color: #AAA599;
  border-radius: 18%;
  justify-content: space-around;
  margin-right:25%;
  margin-left:25%;  
  padding: 5%;
  flex-basis: 200px;
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
