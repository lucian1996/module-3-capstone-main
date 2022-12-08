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
h1 {
  text-align: center;
}
</style>
