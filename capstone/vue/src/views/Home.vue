<template>

  <div>
  
    <v-toolbar app>
      <v-toolbar-title id="title">
          {{ appTitle }}
      </v-toolbar-title>
    </v-toolbar>
      <!-- <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          flat
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          {{ item.title }}
        </v-btn> 
      </v-toolbar-items> -->

    

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
  data(){
    return {
      appTitle: 'Fridgrr',
      menuItems: [
          { title: 'group', path: '/groups' },
          { title: 'logout', path: '/login' },
     ],
    }
  }
};
</script>

<style>
#filter {
  display: flex;
}
#title{
  font-family:    'Courier New', Courier, monospace;
  font-size:      50px;
  font-weight:    bold;
  color:          whitesmoke;
  text-shadow: 1.5px 1.5px 0px lightcoral;
}
.v-btn__content{
  font-family:    'Courier New', Courier, monospace;
  font-size:      15px;
  font-weight:    bolder;
}
.v-toolbar__content{
  background-color: #0EAD69;
}
</style>
