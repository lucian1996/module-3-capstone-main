<template>
  <v-app>
      <create-group-card/>
        <group-card
      v-for="group in groups"
      v-bind:key="group.groupId"
      v-bind:group="group" />
  </v-app>
</template>

<script>
import GroupService from "../services/GroupService";
import CreateGroupCard from './CreateGroupCard.vue';
import GroupCard from "./GroupCard.vue";

export default {
  components: { GroupCard, CreateGroupCard },
  name: "group-list",
  data() {
    return {
      groups: [],
    };
  },
  created() {
    GroupService.getGroups().then((response) => {
      this.groups = response.data;
    });
  },
};
</script>

<style>

</style>
