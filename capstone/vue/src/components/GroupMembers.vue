<template>
  <div>
    <div id="nav">
      <v-toolbar app>
        <v-toolbar-title id="title">
          {{ appTitle }} \
          <!-- {{ group.groupName }} -->
        </v-toolbar-title>

        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn
            flat
            v-for="item in menuItems"
            :key="item.title"
            :to="item.path"
          >
            {{ item.title }}
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>
    </div>
    <div class='members' v-for="(member, index) in $store.state.members" :key="index">
        {{ member.username}}
 </div>
  </div>
</template>

<script>
import GroupDetails from "./GroupDetails.vue";
export default {
  component: { GroupDetails },
  name: "group-members",
  data() {
    return {
      appTitle: "Fridgrr",
      menuItems: [
        { title: "group", path: "/groups" },
        { title: "logout", path: "/login" },
        { title: "members", path: "/members" },
      ],
    };
  },
};
</script>

<style>
#title {
  font-family: "Courier New", Courier, monospace;
  font-size: 50px;
  font-weight: bold;
  color: whitesmoke;
  text-shadow: 1.5px 1.5px 0px lightcoral;
}
.v-btn__content {
  font-family: "Courier New", Courier, monospace;
  font-size: 15px;
  font-weight: bolder;
}
.v-toolbar__content {
  background-color: #0ead69;
}
</style>