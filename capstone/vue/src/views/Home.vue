<template>
  <div>
    <div id="nav">
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
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link>
      </div>
    </div>
    <div>
      <h1>Fridgrr</h1>
      <group-list />
    </div>
  </div>
</template>

<script>
import GroupList from "../components/GroupList.vue";
export default {
  components: { GroupList },
  name: "home",
};
</script>

<style>
#filter {
  display: flex;
}
</style>
