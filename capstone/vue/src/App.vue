<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
       <div>
       <router-link v-bind:to="{ name: 'editUser' }" v-if="$store.state.token != ''">this.user</router-link> &nbsp;&nbsp;
       <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>

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
  background-color: #51434a;
  font-family: Georgia, 'Times New Roman', Times, serif;
  height: 100%;
  text-align: center;
  color: rgba(255, 235, 205, 0.534);
}
a{
  color: #6c80a1;
  text-decoration:none;
}
#nav {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
h1 {
  text-align: center;
  color: #F09374;
}
</style>
