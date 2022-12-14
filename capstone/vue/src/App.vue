<template>
  <div>
    <v-toolbar app>
      <span class="hidden-sm-and-up">
        <v-toolbar-side-icon @click="sidebar = !sidebar"> </v-toolbar-side-icon>
      </span>
      <v-toolbar-title>{{ appTitle }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn flat v-for="item in menuItems" :key="item.title" :to="item.path">
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>

    <v-content id="main" class="d-flex align-center justify-center">
      <router-view></router-view>
    </v-content>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      appTitle: "Fridgrr",
      sidebar: false,
      menuItems: [
        { title: "Group", path: "/groups" },
        { title: "Logout", path: "/login" },
      ],
    };
  },
};
</script>

<style scoped>
.form-card {
  padding: 16px;
}
#main {
  margin-left: auto;
  margin-right: auto;
}
.content {
  text-align: center;
  max-height: 20px;
}
div {
  text-align: center;
}
.v-application a {
  color: black;
  text-decoration: none;
}

</style>
