<template>
  <v-app>
      <div id ="container">
        <create-group-card/>
        <group-card
          v-for="group in groups"
          v-bind:key="group.groupId"
          v-bind:group="group" />
      </div>
      <div v-if="noGroups">
        There are no groups available to join. Maybe you should create one?
      </div>
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
      if (response.data=="") {
        this.noGroups = true;
      }
      this.groups = response.data;
    });
  },
};
</script>

<style scoped>
#container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  /* display:flex; */
  
  
  gap: 20px;
  /* max-width: 1200px;
  min-width: 700px; */
}
</style>
