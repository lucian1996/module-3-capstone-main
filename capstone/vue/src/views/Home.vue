<template>
  <div>
    <h1>Welcome, {{ $store.state.user.username }}.</h1>
    <!-- <div id="nav">
      <div>
        <router-link v-bind:to="{ name: 'home' }">Home</router-link> &nbsp;
        <button>Filter</button>
      </div>
      <div>
        <router-link
          v-bind:to="{ name: 'editUser' }"
          v-if="$store.state.token != ''"
          >{{ $store.state.user.username }}</router-link> &nbsp;|&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div> -->
      <login/>
  </div>
</template>

<script>
// import GroupList from "../components/GroupList.vue";
import Login from './Login.vue';
export default {
  components: { Login },
  name: "home",
};
</script>

