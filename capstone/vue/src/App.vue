<template>

  <v-app>
    <v-navigation-drawer v-model="sidebar" app>
      <v-list>
        <v-list-tile
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          <v-list-tile-action>
          </v-list-tile-action>
          <v-list-tile-content>{{ item.title }}</v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>

    <v-toolbar app>
      <span class="hidden-sm-and-up">
        <v-toolbar-side-icon @click="sidebar = !sidebar">
        </v-toolbar-side-icon>
      </span>
      <v-toolbar-title>
          {{ appTitle }}
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn
          flat
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>

export default {
name: "App",
  data(){
    return {
      appTitle: 'Fridgrr',
      sidebar: false,
      menuItems: [
          { title: 'Group', path: '/groups' },
          { title: 'Logout', path: '/login' },
     ]
    }
  }
};
</script>
<style scoped>
.form-card {
  padding: 16px;
}
body {
  margin-left: 10%;
  margin-right: 10%;
  text-align: center;
}
v-card {
  text-align:center;
  align-items: center;
  display: flex;
  justify-content: center;
}
div {
  text-align:center;
}
.v-application a {
    color: black;
    text-decoration: none;
}
</style>
