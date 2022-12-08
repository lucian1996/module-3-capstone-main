<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <h1>Shoppers 'R' Us</h1>
    <router-view />
  </div>
</template>
<style>
/* #app {
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
} */
h1 {
  text-align: center;
}
</style>
