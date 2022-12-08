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
html {
  display: flex;
  flex-direction: column;
  background-color: rgb(102, 94, 89);
  font-family: Georgia, 'Times New Roman', Times, serif;
  font-weight: light;
  height: 100%;
}
#nav {
  display: flex;
  flex-direction: row;
  justify-content: baseline;
}
h1 {
  text-align: center;
}
</style>
