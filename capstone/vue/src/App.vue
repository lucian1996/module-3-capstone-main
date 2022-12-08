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
  @font-face {
    font-family: "VG5000";
    src: url("./fonts/VG5000-Regular.otf");
  }
  flex-direction: column;
  background-color: #3A2E34;
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
  color: #F09374;
}
</style>
