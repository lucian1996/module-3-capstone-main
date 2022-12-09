<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <h1>Fridgrr</h1>
    <router-view />
  </div>
</template>

<style>
html {
  display: flex;
  align-items: center;
  flex-direction: column;
  background-color: #3A2E34;
  font-family: Georgia, 'Times New Roman', Times, serif;
  height: 100%;
  text-align: center;
}
#nav {
  display: flex;
  flex-direction: row;
  justify-content: baseline;
}
h1 {
  text-align: center;
  color: #F09374;
}
</style>
