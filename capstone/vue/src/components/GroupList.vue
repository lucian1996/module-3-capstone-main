<template>
<body>
  <v-app>
      <div id ="container">
        <create-group-card/>
        <group-card
          v-for="group in groups"
          v-bind:key="group.groupId"
          v-bind:group="group" />
      </div>
  </v-app>
</body>
  
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
#container {
  display: grid;
  align-items: center;
  grid-template-columns: repeat(5,1fr) 60px;
  grid-gap: 3%;
  box-sizing: border-box;
  white-space: nowrap;
  text-overflow: ellipsis;
  height: 150px;
  max-width: 400px;
  /* display:flex; */
  font-size: 200%;
  font-family: 'Courier New', Courier, monospace;
  font-weight: bolder;
  text-decoration-line: none;
  /* height: 35%; */
  padding: 2%; 

  /* max-width: 1200px;
  min-width: 700px; */
}
.v-application--wrap{
  background-color: rgba(240, 128, 128, 0.667);
  min-height: 100vh;
  text-align: center;
}
</style>
