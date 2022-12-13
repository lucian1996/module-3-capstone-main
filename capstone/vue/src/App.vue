<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
       <div>
       <router-link v-bind:to="{ name: 'editUser' }" v-if="$store.state.token != ''">{{$store.state.user.username}}</router-link> &nbsp; &nbsp;
       <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div> -->
    <!-- <h1> Fridgrr </h1> -->
    <router-view />
  </div>
</template>

<script>
export default {};
</script>

<style>
html {
  display: flex;
  align-items: center;
  flex-direction: column;
  background-color: #201d1f;
  font-family: Georgia, "Times New Roman", Times, serif;
  height: 100%;
  text-align: center;
  color: rgba(255, 235, 205, 0.534);
}
a {
  color: #6c80a1;
  text-decoration: none;
}
#nav {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
h1 {
  text-align: center;
  color: #f09374;
}
form {
  background-color: #3a2e34;
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
  flex-basis: 200px;

  text-align: center;
  line-height: 200%;
  padding: 2em;
  padding-top: 1em;
  padding-bottom: 1em;
  border-radius: 12%;
}
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
input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus,
input:-webkit-autofill:active {
  -webkit-box-shadow: 0 0 0px 1000px #000 inset;
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.1);
  -webkit-text-fill-color: rgba(255, 235, 205, 0.404);
  transition: background-color 5000s ease-in-out 0s;
}
/* TODO Only working on first element... */
input[type="text"]:focus {
  outline: 2px solid #5d7065;
}
textarea {
  background: #51434a;
  border: 0.5px solid #51434a;
  text-align: center;
  margin-top: 3px;
  max-width: 10em;
  border-radius: 10%;
  color: rgba(255, 235, 205, 0.534);
  font-family: Georgia, "Times New Roman", Times, serif;
}
button {
  background: #5d7065;
  border: 0.5px solid #5d7065;
  border-radius: 10%;
  max-width: 7em;
}
</style>
